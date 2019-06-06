package kz.qazapp.myatek.fragments;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

import kz.qazapp.myatek.DB.DBHelper;
import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.shedulespack.Api;
import kz.qazapp.myatek.fragments.shedulespack.App;
import kz.qazapp.myatek.fragments.shedulespack.Model;
import kz.qazapp.myatek.fragments.shedulespack.ModelsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShedFrag extends Fragment {

    private Api api;
    View view;
    RecyclerView recycler;
    ModelsAdapter adapter;
    TextView tv_header_text, tv_change_group;
    DBHelper dbHelper;
    ListView listView;
    Cursor cursor = null;

    final String[] from = {
            DBHelper.ID, DBHelper.DAY, DBHelper.SUB1, DBHelper.SUB1TEACH, DBHelper.SUB1LEC,
            DBHelper.SUB2, DBHelper.SUB2TEACH, DBHelper.SUB2LEC,
            DBHelper.SUB3, DBHelper.SUB3TEACH, DBHelper.SUB3LEC,
            DBHelper.SUB4, DBHelper.SUB4TEACH, DBHelper.SUB4LEC
    };
    final int[] to = {
            R.id.item_id, R.id.item_days_of_week, R.id.item_sub1_name, R.id.item_sub1_teach, R.id.item_sub1_lec,
            R.id.item_sub2_name, R.id.item_sub2_teach, R.id.item_sub2_lec,
            R.id.item_sub3_name, R.id.item_sub3_teach, R.id.item_sub3_lec,
            R.id.item_sub4_name, R.id.item_sub4_teach, R.id.item_sub4_lec
    };

    public ShedFrag() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new DBHelper(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_shed_main, container, false);

        adapter = new ModelsAdapter();
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        listView = view.findViewById(R.id.lwList);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] columns = {
                DBHelper.ID, DBHelper.DAY, DBHelper.SUB1, DBHelper.SUB1TEACH, DBHelper.SUB1LEC,
                DBHelper.SUB2, DBHelper.SUB2TEACH, DBHelper.SUB2LEC,
                DBHelper.SUB3, DBHelper.SUB3TEACH, DBHelper.SUB3LEC,
                DBHelper.SUB4, DBHelper.SUB4TEACH, DBHelper.SUB4LEC
        };
        cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.view_record_shed, cursor, from, to, 0);
        cursorAdapter.notifyDataSetChanged();
        listView.setAdapter(cursorAdapter);

        tv_header_text = view.findViewById(R.id.group_name_zagolovok);
        tv_change_group = view.findViewById(R.id.change_group);
        tv_change_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });


        return view;
    }

    private void loadData(){
        listView.setVisibility(View.GONE);
        recycler.setAdapter(adapter);
        api = ((App) getActivity().getApplication()).getApi();

        api.getModels("ПО", 1, 1).enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Log.d("TAG", "onResponse");

                List<Model> models = response.body();
                adapter.setModels(models);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d("TAG", "onFailure");
            }
        });
    }
}
