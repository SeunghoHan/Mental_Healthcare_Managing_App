package application.project.mhm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import application.project.mhm.debug_keygen.MemoryManager;
import application.project.mhm.mental_healthcare_manaing.R;
import application.project.mhm.tutorial_controller.TutorialDialog;

/**
 * Created by Seungho Han on 2017-05-05.
 */

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar = null;
    private DrawerLayout drawer = null;
    private ActionBarDrawerToggle toggle = null;
    private NavigationView navigationView = null;

    private ImageView mainMonitoring = null;
    private ImageView toolbarImg = null;

    private Picasso picasso = null;
    private LruCache picassoLruCache = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        this.initResource();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        picassoLruCache.clear();
    }
    /**
     * To manage the resources in this activity
     */
    private void initResource() {
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.home_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        mainMonitoring = (ImageView) findViewById(R.id.home_main_feeling_monitoring_img);
        toolbarImg = (ImageView) findViewById(R.id.toolbar_img);

        picassoLruCache = new LruCache(this);
        picasso = new Picasso.Builder(this) //
                .memoryCache(picassoLruCache) //
                .build();

        this.registerListener();
    }

    /**
     * To register the listeners in this activity
     */
    private void registerListener() {
        navigationView.setNavigationItemSelectedListener(this);
        mainMonitoring.setOnClickListener(this);
        toolbarImg.setOnClickListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.i1_feeling_monitoring) {
            Intent intent = new Intent(this, MonitoringActivity.class);
            picassoLruCache.clear();
            startActivity(intent);
        }
 //       } else if (id == R.id.item2) {

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.toolbar_img:
                GuideDialog tutorial = new TutorialDialog(this);
                tutorial.setCanceledOnTouchOutside(false);
                tutorial.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                tutorial.startDialog();
                picassoLruCache.clear();
                break;
            case R.id.home_main_feeling_monitoring_img:
                intent = new Intent(this, MonitoringActivity.class);
                picassoLruCache.clear();
                startActivity(intent);
                break;
        }
    }

    public void onTutorialYesClicked() {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.toolbar_title) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
