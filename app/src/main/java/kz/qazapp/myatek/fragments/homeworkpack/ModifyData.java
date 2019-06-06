package kz.qazapp.myatek.fragments.homeworkpack;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import kz.qazapp.myatek.Global;
import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class ModifyData extends Fragment {

    private View v;
    private EditText et;
    private TextView tv;

    private long _id;
    private DBManager dbManager;

    //zametka zametka = new zametka();
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    zNote zametka = new zNote();

    public ModifyData() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.frag_data_modify, container, false);

        dbManager = new DBManager(getContext());
        dbManager.open();

        et = (EditText) v.findViewById(R.id.editorText);
        tv = (TextView) v.findViewById(R.id.edit_date);

        //Intent intent = getActivity().getIntent();
        String id = Global.var_id;
        String text = Global.Tname;
        String date = Global.Tdate;

        _id = Long.parseLong(id);

        et.setText(text);
        tv.setText(date);

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
                        String dateText = tv.getText().toString();

                        dbManager.update(_id, text, dateText);

                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.zcontent, new view_note()).addToBackStack(null).commit();
                        break;
                    case R.id.bottomNav3 :
                        dbManager.delete("note", _id);


                        //zametka zametka2 = new zametka();
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
