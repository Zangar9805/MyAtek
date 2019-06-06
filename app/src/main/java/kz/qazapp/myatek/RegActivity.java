package kz.qazapp.myatek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.qazapp.myatek.fragments.shedulespack.Api;
import kz.qazapp.myatek.fragments.shedulespack.App;
import kz.qazapp.myatek.fragments.shedulespack.Model;
import kz.qazapp.myatek.fragments.shedulespack.ModelsAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegActivity extends AppCompatActivity {

    private Api api;
    Spinner groupName, groupKurs, groupId;
    EditText etName, etLastName;
    Button submitData;
    String userName, userLastName, userGroupName, userGroupKurs, userGroupId, loaded;
    SharedPreferences preferences, spref;
    RecyclerView recyclerView;
    ModelsAdapter adapter;

    private ArrayList listGroupName  = new ArrayList(){};

    @Override
    protected void onStart() {
        super.onStart();
        preferences = getSharedPreferences("Mypref", MODE_PRIVATE);
        loaded = preferences.getString("Loaded", "");

        if(loaded.equals("yes")){
            goToG();
        }
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity_main);

        userGroupName = "ПО";
        userGroupKurs = "1";
        userGroupId = "1";

        adapter = new ModelsAdapter();
        recyclerView = findViewById(R.id.goneRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        groupName = findViewById(R.id.group_name_sp);
        groupKurs = findViewById(R.id.group_kurs_sp);
        groupId = findViewById(R.id.group_id_sp);
        etName = findViewById(R.id.reg_et_name);
        etLastName = findViewById(R.id.reg_et_lastName);

        submitData = findViewById(R.id.reg_submit_btn);
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = etName.getText().toString();
                userLastName = etLastName.getText().toString();
                userGroupName = groupName.getSelectedItem().toString();
                userGroupKurs = groupKurs.getSelectedItem().toString();
                userGroupId = groupId.getSelectedItem().toString();

                api = ((App) getApplication()).getApi();

                loadData(userGroupName, Integer.valueOf(userGroupKurs), Integer.valueOf(userGroupId));
                //sendData(userName, userLastName, userGroupName+" "+userGroupKurs+"-"+userGroupId);
                saveShared(userName, userLastName, userGroupName+" "+userGroupKurs+"-"+userGroupId);
            }
        });
    }

    private void saveShared(String userName, String userLastName, String s) {
        spref = getSharedPreferences("Mypref", MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putString("Username", userName);
        editor.putString("Lastname", userLastName);
        editor.putString("Groupname", s);
        editor.putString("Loaded", "yes");
        editor.apply();
    }

    private void sendData(String userName, String userLastName, String s) {
        api.createUser(userName, userLastName, s).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(RegActivity.this, "Вход выполнен!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData(String s, int k, int g) {
        if (!isOnline(RegActivity.this)){
            Toast.makeText(RegActivity.this,"Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }else {
            if (userName.length() != 0 && userLastName.length() != 0) {

                api.getModels(s, k, g).enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        List<Model> models = response.body();

                        if (models == null) {
                            Toast.makeText(RegActivity.this, "В базе не найдено группы с таким именем!", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "OnNull");
                        } else {

                            adapter.setModels(models);
                            Toast.makeText(RegActivity.this, "Данные должны загружаться", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "OnResponse");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        Log.e("Error", "onFailure", t);
                    }
                });

                goToG();

            } else
                Toast.makeText(this, "Имя пользователя или фамилия не должно быть пустым!", Toast.LENGTH_SHORT).show();
        }

    }

    private void goToG() {
        Intent intent = new Intent(RegActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
