package application.project.mhm.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import application.project.mhm.monitoring.FeelingsFragment;
import application.project.mhm.monitoring.FeelingsObj;
import application.project.mhm.monitoring.LifestyleFragment;
import application.project.mhm.monitoring.LifestyleObj;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-20.
 */

public class MonitoringActivity extends AppCompatActivity {

    private TabLayout tab = null;
    private ViewPager viewPager = null;
    private MonitoringAdaptor adaptor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitoring);

        this.initResource();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initResource() {
        this.adaptor = new MonitoringAdaptor(getSupportFragmentManager());

        this.viewPager = (ViewPager) findViewById(R.id.monitoring_container);
        this.viewPager.setAdapter(adaptor);

        this.tab = (TabLayout) findViewById(R.id.monitoring_tab);
        this.tab.setupWithViewPager(viewPager);
    }

    public class MonitoringAdaptor extends FragmentPagerAdapter  {

        private static final int PAGE_NUMBER = 2;
        private final String tabTitles[] = new String[] {"기분", "생활"};

        // Loaded from DB
        private FeelingsObj fo = new FeelingsObj("", FeelingsObj.FeelingsConditions.NORMAL);
        private LifestyleObj lo = new LifestyleObj();

        public MonitoringAdaptor(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FeelingsFragment.newInstance(fo);
                case 1:
                    return  LifestyleFragment.newInstance(lo);
                default: return LifestyleFragment.newInstance(lo);
            }
        }

        @Override
        public int getCount() {
            return PAGE_NUMBER;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
