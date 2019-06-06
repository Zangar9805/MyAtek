package kz.qazapp.myatek;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import kz.qazapp.myatek.DB.DBHelper;
import kz.qazapp.myatek.fragments.AboutAppFrag;
import kz.qazapp.myatek.fragments.AboutColFrag;
import kz.qazapp.myatek.fragments.HomeWFrag;
import kz.qazapp.myatek.fragments.NewsFrag;
import kz.qazapp.myatek.fragments.SettingsFrag;
import kz.qazapp.myatek.fragments.ShedFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences preferences;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment frAboutCol = new AboutColFrag();
        ft.replace(R.id.container, frAboutCol).commit();

        preferences = getSharedPreferences("Mypref", MODE_PRIVATE);
        String yourName = preferences.getString("Username", "");
        Toast.makeText(this, "Добро пожаловать "+yourName, Toast.LENGTH_SHORT).show();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int idInd = cursor.getColumnIndex(DBHelper.ID);
            int dayInd = cursor.getColumnIndex(DBHelper.DAY);
            int sub1Ind = cursor.getColumnIndex(DBHelper.SUB1);
            int sub1teachInd = cursor.getColumnIndex(DBHelper.SUB1TEACH);
            int sub1lecInd = cursor.getColumnIndex(DBHelper.SUB1LEC);
            int sub2Ind = cursor.getColumnIndex(DBHelper.SUB2);
            int sub2teachInd = cursor.getColumnIndex(DBHelper.SUB2TEACH);
            int sub2lecInd = cursor.getColumnIndex(DBHelper.SUB2LEC);
            int sub3Ind = cursor.getColumnIndex(DBHelper.SUB3);
            int sub3teachInd = cursor.getColumnIndex(DBHelper.SUB3TEACH);
            int sub3lecInd = cursor.getColumnIndex(DBHelper.SUB3LEC);
            int sub4Ind = cursor.getColumnIndex(DBHelper.SUB4);
            int sub4teachInd = cursor.getColumnIndex(DBHelper.SUB4TEACH);
            int sub4lecInd = cursor.getColumnIndex(DBHelper.SUB4LEC);

            do{
                Log.d("DATABASE", "ID = "+cursor.getInt(idInd)
                        +", DAY = "+cursor.getInt(dayInd)
                        +", SUB1 = "+cursor.getString(sub1Ind)
                        +", SUB1TEACH = "+cursor.getString(sub1teachInd)
                        +", SUB1LEC = "+cursor.getString(sub1lecInd)
                        +", SUB2 = "+cursor.getString(sub2Ind)
                        +", SUB2TEACH = "+cursor.getString(sub2teachInd)
                        +", SUB2LEC = "+cursor.getString(sub2lecInd)
                        +", SUB3 = "+cursor.getString(sub3Ind)
                        +", SUB3TEACH = "+cursor.getString(sub3teachInd)
                        +", SUB3LEC = "+cursor.getString(sub3lecInd)
                        +", SUB4 = "+cursor.getString(sub4Ind)
                        +", SUB4TEACH = "+cursor.getString(sub4teachInd)
                        +", SUB4LEC = "+cursor.getString(sub4lecInd));
            }while (cursor.moveToNext());

            cursor.close();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fr = new Fragment();
        int id = item.getItemId();

        if (id == R.id.aboutkolled) {
            fr = new AboutColFrag();
        } else if (id == R.id.rasp) {
            fr = new ShedFrag();
        } else if (id == R.id.dz) {
            fr = new HomeWFrag();
        } else if (id == R.id.news) {
            fr = new NewsFrag();
        } else if (id == R.id.settings) {
            fr = new SettingsFrag();
        } else if (id == R.id.about) {
            fr = new AboutAppFrag();
        }

        ft.replace(R.id.container, fr).addToBackStack(null).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
