package kz.qazapp.myatek.fragments.shedulespack;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import kz.qazapp.myatek.DB.DBHelper;
import kz.qazapp.myatek.R;

public class ModelsAdapter extends RecyclerView.Adapter<ModelsAdapter.ModelViewHolder> {

    List<Model> models = Collections.emptyList();
    DBHelper dbHelper;

    public void setModels(List<Model> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_record_shed, parent,false);
        dbHelper = new DBHelper(parent.getContext());
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ModelViewHolder extends RecyclerView.ViewHolder{

        private TextView daysWeek, sub1_name, sub1_teach, sub1_lec;
        private TextView sub2_name, sub2_teach, sub2_lec;
        private TextView sub3_name, sub3_teach, sub3_lec;
        private TextView sub4_name, sub4_teach, sub4_lec;
        String[] weekDays = new String[]{"", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница"};
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        public ModelViewHolder(View itemView) {
            super(itemView);

            daysWeek = itemView.findViewById(R.id.item_days_of_week);
            sub1_name = itemView.findViewById(R.id.item_sub1_name);
            sub2_name = itemView.findViewById(R.id.item_sub2_name);
            sub3_name = itemView.findViewById(R.id.item_sub3_name);
            sub4_name = itemView.findViewById(R.id.item_sub4_name);

            sub1_teach = itemView.findViewById(R.id.item_sub1_teach);
            sub2_teach = itemView.findViewById(R.id.item_sub2_teach);
            sub3_teach = itemView.findViewById(R.id.item_sub3_teach);
            sub4_teach = itemView.findViewById(R.id.item_sub4_teach);

            sub1_lec = itemView.findViewById(R.id.item_sub1_lec);
            sub2_lec = itemView.findViewById(R.id.item_sub2_lec);
            sub3_lec = itemView.findViewById(R.id.item_sub3_lec);
            sub4_lec = itemView.findViewById(R.id.item_sub4_lec);
        }

        public void bind(Model model){

            contentValues.put(DBHelper.DAY, weekDays[model.getDay()]);
            contentValues.put(DBHelper.SUB1, model.getSub1());
            contentValues.put(DBHelper.SUB1TEACH, model.getSub1Teach());
            contentValues.put(DBHelper.SUB1LEC, model.getSub1Lec());
            contentValues.put(DBHelper.SUB2, model.getSub2());
            contentValues.put(DBHelper.SUB2TEACH, model.getSub2Teach());
            contentValues.put(DBHelper.SUB2LEC, model.getSub2Lec());
            contentValues.put(DBHelper.SUB3, model.getSub3());
            contentValues.put(DBHelper.SUB3TEACH, model.getSub3Teach());
            contentValues.put(DBHelper.SUB3LEC, model.getSub3Lec());
            contentValues.put(DBHelper.SUB4, model.getSub4());
            contentValues.put(DBHelper.SUB4TEACH, model.getSub4Teach());
            contentValues.put(DBHelper.SUB4LEC, model.getSub4Lec());

            sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);

            daysWeek.setText(weekDays[model.getDay()]);
            sub1_name.setText(model.getSub1());
            sub1_teach.setText(model.getSub1Teach());
            sub1_lec.setText(model.getSub1Lec());

            sub2_name.setText(model.getSub2());
            sub2_teach.setText(model.getSub2Teach());
            sub2_lec.setText(model.getSub2Lec());

            sub3_name.setText(model.getSub3());
            sub3_teach.setText(model.getSub3Teach());
            sub3_lec.setText(model.getSub3Lec());

            sub4_name.setText(model.getSub4());
            sub4_teach.setText(model.getSub4Teach());
            sub4_lec.setText(model.getSub4Lec());
        }
    }

}
