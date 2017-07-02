package application.project.mhm.activities;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import application.project.mhm.survey_controller.Survey;
import application.project.mhm.survey_controller.SurveyDialog;
import application.project.mhm.survey_controller.SurveyItem;
import application.project.mhm.survey_controller.SurveyListViewAdapter;
import application.project.mhm.mental_healthcare_manaing.R;
import application.project.mhm.survey_controller.SurveyListViewItem;

/**
 * Created by Seungho Han on 2017-05-08.
 */

public class SurveyActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter = null;
    private ViewPager viewPager = null;
    private SurveyDialog surveyDialogStart = null;
    private SurveyDialog surveyDialogEnd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.survey_container);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.beginFakeDrag();

        surveyDialogStart = new SurveyDialog(this, SurveyDialog.SurveyDialogTypes.START);
        surveyDialogStart.setCanceledOnTouchOutside(false);
        surveyDialogStart.startDialog();
        surveyDialogEnd = new SurveyDialog(viewPager.getContext(), SurveyDialog.SurveyDialogTypes.END);
        surveyDialogEnd.setCanceledOnTouchOutside(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((SurveyFragment) viewPager.getAdapter().instantiateItem(viewPager, position)).updatePageImages(position);
                if (position == viewPager.getAdapter().getCount() - 1) {
                    surveyDialogEnd.startDialog();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        ImageView backBtn = (ImageView) findViewById(R.id.survey_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        Button nextBtn = (Button) findViewById(R.id.survey_next_btn);
        nextBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        surveyDialogEnd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finishActivity();
            }
        });
    }

    public void finishActivity() {
        this.finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SurveyFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String SURVEY_PAGE_DATA = "survey_data";
        private LinearLayout llPageImages;

        public SurveyFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SurveyFragment newInstance(int sectionNumber, SurveyItem surveyItem) {
            SurveyFragment fragment = new SurveyFragment();
            Bundle args = new Bundle();
            args.putSerializable(SURVEY_PAGE_DATA, surveyItem);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.survey_content_main, container, false);

            SurveyItem surveyItem = (SurveyItem) getArguments().getSerializable(SURVEY_PAGE_DATA);

            // Set question on survey activity
            TextView question = (TextView) rootView.findViewById(R.id.survey_question_tv);
            question.setText(surveyItem.getQuestion());

            // Set answer candidates on survey activity
            final SurveyListViewAdapter listViewAdapter = new SurveyListViewAdapter(R.layout.survey_item);
            ListView surveyItemLV = (ListView) rootView.findViewById(R.id.survey_item_lv);
            surveyItemLV.setAdapter(listViewAdapter);
            List<String> surveyAnswers = surveyItem.getAnswers();
            for (String answer : surveyAnswers) {
                listViewAdapter.addItem(answer);
            }

            llPageImages = (LinearLayout) rootView.findViewById(R.id.survey_state_imgs);
            for (int i = 0; i < Survey.getInstace().getSurveyList().size(); i++) {
                ImageView iv = new ImageView(rootView.getContext());
                iv.setScaleX(0.5f);
                iv.setScaleY(0.5f);
                if (i == 0)
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.survey_state_filled_img));
                else
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.survey_state_unfilled_img));
                llPageImages.addView(iv);
            }

            return rootView;
        }

        public void updatePageImages(int position) {
            if (llPageImages == null) return;

            final int numPage = position + 1;
            for (int i = 0; i < llPageImages.getChildCount(); i++) {
                ImageView iv = (ImageView) llPageImages.getChildAt(i);
                if (i < numPage)
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.survey_state_filled_img));
                else
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.survey_state_unfilled_img));
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        Survey survey = Survey.getInstace();
        List<SurveyItem> surveyItems = survey.getSurveyList();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return SurveyFragment.newInstance(position + 1, surveyItems.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return surveyItems.size();
        }
    }
}
