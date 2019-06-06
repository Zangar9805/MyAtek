package kz.qazapp.myatek.fragments.homeworkpack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class AddData extends Fragment {
    private Context globalContext = null;
    View v;
    private EditText et;
    private TextView tv;
    String date;
    private DBManager dbManager;
    FragmentManager fragmentManager;

    public AddData() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_data_add, container, false);
        globalContext = this.getActivity();

        tv = (TextView) v.findViewById(R.id.note_date);
        et = (EditText) v.findViewById(R.id.editText);
        et.setCursorVisible(true);
        et.setActivated(true);
        et.setPressed(true);


        DateFormat df = new SimpleDateFormat("HH:mm");
        date = df.format(Calendar.getInstance().getTime());

        tv.setText(date);

        dbManager = new DBManager(getContext());
        dbManager.open();

        BottomNavigationView bnv = (BottomNavigationView) v.findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottomNav1 :
                        et.setFocusable(false);
                        et.setCursorVisible(false);
                        et.setLongClickable(false);
                        et.setBackgroundColor(getResources().getColor(R.color.White));
                        break;
                    case R.id.bottomNav2 :
                        String text = et.getText().toString();

                        dbManager.insert("note",text, date);

                        //Intent main = new Intent(getActivity().getApplicationContext(), new view_note()).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //startActivity(main);

                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.zcontent, new view_note()).addToBackStack(null).commit();
                        break;
                    case R.id.bottomNav3 :
                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.zcontent, new view_note()).addToBackStack(null).commit();
                        break;
                }
                return false;
            }
        });

        return v;
    }
}
