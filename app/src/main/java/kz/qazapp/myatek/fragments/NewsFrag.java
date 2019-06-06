package kz.qazapp.myatek.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import kz.qazapp.myatek.R;

public class NewsFrag extends Fragment {

    View v;
    private ListView lv;
    private TextView tv;
    ParseText pt;

    public NewsFrag() {
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_news, container, false);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isOnline(getContext())){
            Toast.makeText(getContext(),"Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }else{
            tv = (TextView) v.findViewById(R.id.news_text);
            lv = (ListView) v.findViewById(R.id.listView);
            ParseTitle parseTitle = new ParseTitle();
            parseTitle.execute();

            try {
                final HashMap<String, String> hashMap = parseTitle.get();
                final ArrayList<String> list = new ArrayList<>();
                for (Map.Entry entry : hashMap.entrySet()){
                    list.add(entry.getKey().toString());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pt = new ParseText();
                        pt.execute(hashMap.get(list.get(position)));

                        try {
                            lv.setVisibility(View.GONE);
                            tv.setText(pt.get());
                            tv.setVisibility(View.VISIBLE);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    private class ParseText extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String str = " ";

            try {
                Document document = Jsoup.connect(params[0]).get();
                Element element = document.select(".main-cont").first();
                str = element.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }

    private class ParseTitle extends AsyncTask<Void, Void, HashMap<String, String>>{

        @Override
        protected HashMap<String, String> doInBackground(Void... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            try {
                Document document =  Jsoup.connect("http://atekps.kz/index.php?id=126").get();
                Elements elements = document.select(".class-atek");
                for (Element element : elements){
                    Element element1 = element.select("a[href]").first();
                    hashMap.put(element.text(), element1.attr("abs:href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hashMap;
        }
    }

}
