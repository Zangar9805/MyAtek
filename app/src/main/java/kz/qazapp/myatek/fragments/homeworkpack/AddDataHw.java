package kz.qazapp.myatek.fragments.homeworkpack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.db.DBManager;

public class AddDataHw extends Fragment {
    View v;

    private EditText et1, et2, et3, et4, et5;
    private DBManager dbManager;
    FragmentManager fragmentManager;

    public AddDataHw() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_data_hw_add, container, false);
        setHasOptionsMenu(true);

        //tv = (TextView) v.findViewById(R.id.note_date);
        et1 = (EditText) v.findViewById(R.id.TitleHw);
        et2 = (EditText) v.findViewById(R.id.TextHw);
        et3 = (EditText) v.findViewById(R.id.SubHw);
        et4 = (EditText) v.findViewById(R.id.vremHw);
        et5 = (EditText) v.findViewById(R.id.vrem2Hw);


        //DateFormat df = new SimpleDateFormat("HH:mm");
        //date = df.format(Calendar.getInstance().getTime());

        //tv.setText(date);

        dbManager = new DBManager(getContext());
        dbManager.open();

        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });

        et5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    HourDialog dialog = new HourDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
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

            dbManager.insertHw(title, text, sub, vrem, hour);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.dcontent, new view_note_hw()).addToBackStack(null).commit();

        }

        return super.onOptionsItemSelected(item);
    }
}
