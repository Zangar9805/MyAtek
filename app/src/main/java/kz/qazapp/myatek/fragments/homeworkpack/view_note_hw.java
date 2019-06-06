package kz.qazapp.myatek.fragments.homeworkpack;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import kz.qazapp.myatek.Global;
import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBHelperHw;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class view_note_hw extends Fragment {
    View view;
    FragmentManager fragmentManager;
    FloatingActionButton fab;
    ListView lw;
    DBManager dbManager;

    final String[] from = {DBHelperHw._ID_hw, DBHelperHw.HW_TITLE, DBHelperHw.HW_TEXT, DBHelperHw.HW_SUB, DBHelperHw.HW_DATE, DBHelperHw.HW_DATE2};
    final int[] to = new int[]{R.id.idHw, R.id.hwTitle, R.id.homeText, R.id.hwSub, R.id.hwTime, R.id.hwHour};

    public view_note_hw() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbManager = new DBManager(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view_note_hw, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.HWadd);
        lw = (ListView) view.findViewById(R.id.listHw);

        dbManager.open();
        Cursor cursor = dbManager.fetch("hw");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.view_record_hw, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView hwId = (TextView) view.findViewById(R.id.idHw);
                TextView homeText = (TextView) view.findViewById(R.id.homeText);
                TextView hwTitle = (TextView) view.findViewById(R.id.hwTitle);
                TextView hwDate = (TextView) view.findViewById(R.id.hwTime);
                TextView hwSub = (TextView) view.findViewById(R.id.hwSub);
                TextView hwHour = (TextView) view.findViewById(R.id.hwHour);

                String id = hwId.getText().toString();
                String text = homeText.getText().toString();
                String title = hwTitle.getText().toString();
                String date = hwDate.getText().toString();
                String sub = hwSub.getText().toString();
                String hour = hwHour.getText().toString();

                Global.Hw_id = id;
                Global.Hw_title = title;
                Global.Hw_text = text;
                Global.Hw_date = date;
                Global.Hw_sub = sub;
                Global.Hw_hour = hour;

                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dcontent, new ModifyDataHw()).addToBackStack(null).commit();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dcontent, new AddDataHw()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
