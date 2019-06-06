package kz.qazapp.myatek.fragments.homeworkpack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kz.qazapp.myatek.Global;
import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class ModifyDataHw extends Fragment {

    View v;
    EditText et1,et2,et3,et4,et5;
    Button done;
    Long _id;
    DBManager dbManager;
    FragmentManager fragmentManager;

    public ModifyDataHw() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.frag_data_hw_modify, container, false);
        setHasOptionsMenu(true);

        dbManager = new DBManager(getContext());
        dbManager.open();

        done = (Button)v.findViewById(R.id.gotov);
        et1 = (EditText)v.findViewById(R.id.MTitleHw);
        et2 = (EditText)v.findViewById(R.id.MTextHw);
        et3 = (EditText)v.findViewById(R.id.MSubHw);
        et4 = (EditText)v.findViewById(R.id.MvremHw);
        et5 = (EditText)v.findViewById(R.id.Mvrem2Hw);

        String id = Global.Hw_id;
        String title = Global.Hw_title;
        String text = Global.Hw_text;
        String sub = Global.Hw_sub;
        String vrem = Global.Hw_date;
        String hour = Global.Hw_hour;

        _id = Long.parseLong(id);

        et1.setText(title);
        et2.setText(text);
        et3.setText(sub);
        et4.setText(vrem);
        et5.setText(hour);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.delete("hw", _id);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.dcontent, new view_note_hw()).addToBackStack(null).commit();
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.succ_hw, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.doneHw){
            String title = et1.getText().toString();
            String text = et2.getText().toString();
            String sub = et3.getText().toString();
            String vrem = et4.getText().toString();
            String hour = et5.getText().toString();

            dbManager.updateHw(_id, title, text, sub, vrem, hour);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.dcontent, new view_note_hw()).addToBackStack(null).commit();
        }

        return super.onOptionsItemSelected(item);
    }

}
