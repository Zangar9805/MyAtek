package kz.qazapp.myatek.fragments.homeworkpack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import kz.qazapp.myatek.Global;
import kz.qazapp.myatek.MainActivity;
import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBHelper;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class zNote extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    View view;
    private Button btn;
    private DBManager dbManager;
    private ListView lv;
    private SimpleCursorAdapter adapter;

    final String[] from = {DBHelper._ID, DBHelper.NOTE_TEXT, DBHelper.NOTE_DATE};
    final int[] to = new int[]{R.id.id, R.id.text, R.id.date};

    public zNote() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbManager = new DBManager(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_hw_note, container, false);

        btn = (Button) view.findViewById(R.id.zametkaAdd);

        dbManager.open();
        Cursor cursor = dbManager.fetch("note");

        lv = (ListView) view.findViewById(R.id.listZametka);

        adapter = new SimpleCursorAdapter(getActivity().getBaseContext(), R.layout.view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idText = (TextView) view.findViewById(R.id.id);
                TextView txText = (TextView) view.findViewById(R.id.text);
                TextView dtText = (TextView) view.findViewById(R.id.date);

                String id = idText.getText().toString();
                String text = txText.getText().toString();
                String date = dtText.getText().toString();

                ModifyData md = new ModifyData();
                Global.var_id = id;
                Global.Tname = text;
                Global.Tdate = date;

                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.zcontent, md).addToBackStack(null).commit();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData add = new AddData();
                //Intent addintent = new Intent(getActivity().getApplicationContext(), AddData.class);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.zcontent, add).addToBackStack(null).commit();
                //startActivity(addintent);
            }
        });

        return view;
    }
}
