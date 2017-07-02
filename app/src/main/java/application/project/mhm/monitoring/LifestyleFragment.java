package application.project.mhm.monitoring;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-20.
 */

public class LifestyleFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    private static int LS_IV_ID = 43120;
    private static final String INTENT_KEY = "LIFESTYLE";
    public static final int INTENT_CODE = 2222;

    private View view = null;

    private LifestyleObj lifestyleObj = null;
    private DatePickerDialog datePickerDialog = null;
    private RelativeLayout lifestyleLayout = null;
    private ImageView lifestyleAdditionBtn = null;
    private TextView lifestlyeAdditionTv = null;

    private List<ImageView> selectedLSImg = null;
    private List<BubbleSeekBar> seleckedLS_Sb = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifestyleObj = (LifestyleObj) getArguments().getSerializable(INTENT_KEY);
        int[] curDate = getCurrentDate();
        datePickerDialog = new DatePickerDialog(getActivity(), this, curDate[0], curDate[1], curDate[2]);
        //    this.changActivitySize();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.lifestyle_frag, container, false);

        ImageView datePicker = (ImageView) view.findViewById(R.id.lifestyle_date_img);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        lifestyleLayout = (RelativeLayout) view.findViewById(R.id.lifestyle_layout);
        lifestyleLayout.setScaleX(0.9f);

        lifestyleAdditionBtn = (ImageView) view.findViewById(R.id.lifestyle_addition_img);
        lifestyleAdditionBtn.setOnClickListener(this);

        lifestlyeAdditionTv = (TextView) view.findViewById(R.id.lifestyle_addition_tv);
        lifestlyeAdditionTv.setOnClickListener(this);

        setAddtionBtnPosition(true);
        setAddtionTvPosition(true);
        return view;
    }

    public static LifestyleFragment newInstance(LifestyleObj lifestyleObj) {
        LifestyleFragment fragment = new LifestyleFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_KEY, lifestyleObj);
        fragment.setArguments(args);
        return fragment;
    }

    private int[] getCurrentDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new int[]{year, month, day};
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    private void changActivitySize() {
        Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.9);
//        int height = (int) (display.getHeight() * 0.85);

        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.width = width;
//        params.height = height;
        getActivity().getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lifestyle_addition_img || v.getId() == R.id.lifestyle_addition_tv) {
            Intent intent = new Intent(getActivity(), LifestyleAdditionDialog.class);
            startActivityForResult(intent, INTENT_CODE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == INTENT_CODE) {
            List<LifestyleObj.Types> selectedLS = (List<LifestyleObj.Types>) data.getSerializableExtra(INTENT_KEY);
            if (selectedLS != null) {
                selectedLSImg = new ArrayList<>();
                seleckedLS_Sb = new ArrayList<>();
                createSelectedLifestyle(selectedLS);
            }
        }
    }

    public void createSelectedLifestyle(List<LifestyleObj.Types> selectedLS) {
        for (int i = 0; i < selectedLS.size(); i++) {
            LifestyleObj.Types type = selectedLS.get(i);
            createImageView(type, i);
            createSeekbar(type, i);
        }
    }

    public void createSeekbar(LifestyleObj.Types type, int i) {
        BubbleSeekBar bubbleSeekBar = null;
        switch (type) {
            case SLEEP:
                bubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.lifestyle_sleep_sb);
                break;
            case APPETITE:
                bubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.lifestyle_appetite_sb);
                break;
            case WORK:
                bubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.lifestyle_work_sb);
                break;
        }
        bubbleSeekBar.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (i == 0) {
            param.addRule(RelativeLayout.BELOW, R.id.lifestyle_title_tv);
            param.addRule(RelativeLayout.ALIGN_BOTTOM, selectedLSImg.get(i).getId());
            param.addRule(RelativeLayout.RIGHT_OF, selectedLSImg.get(i).getId());
        } else {
            param.addRule(RelativeLayout.BELOW, LS_IV_ID + (i - 1));
            param.addRule(RelativeLayout.ALIGN_BOTTOM, selectedLSImg.get(i).getId());
            param.addRule(RelativeLayout.RIGHT_OF, selectedLSImg.get(i).getId());
        }
        param.setMargins(15, 60, 40, 0);
        bubbleSeekBar.setLayoutParams(param);
        lifestyleLayout.updateViewLayout(bubbleSeekBar, param);
        //  lifestyleLayout.addView(bubbleSeekBar);
        seleckedLS_Sb.add(bubbleSeekBar);
        setSeekbarOptions(i);
    }

    public void createImageView(LifestyleObj.Types type, int idPostfix) {
        ImageView iv = new ImageView(getActivity());
        iv.setId(LS_IV_ID + idPostfix);
        iv.setScaleX(0.55f);
        iv.setScaleY(0.55f);
        switch (type) {
            case SLEEP:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.lifestyle_sleep));
                break;
            case APPETITE:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.lifestyle_appetite));
                break;
            case WORK:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.lifestyle_work));
                break;
        }
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (idPostfix == 0) {
            param.addRule(RelativeLayout.BELOW, R.id.lifestyle_date_img);
            param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lifestyle_date_img);
        } else {
            param.addRule(RelativeLayout.BELOW, LS_IV_ID + (idPostfix - 1));
            param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lifestyle_date_img);
        }
        param.setMargins(60, 0, 0, 0);
        iv.setLayoutParams(param);
        lifestyleLayout.addView(iv);
        selectedLSImg.add(iv);
        setAddtionBtnPosition(false);
        setAddtionTvPosition(false);
    }

    private void setSeekbarOptions(int i) {
        seleckedLS_Sb.get(i).getConfigBuilder()
                .min(0.0f)
                .max(10)
                .progress(2)
                .sectionCount(4)
                .trackColor(ContextCompat.getColor(getContext(), R.color.white_alpha_192))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.white))
                .thumbColor(ContextCompat.getColor(getContext(), R.color.white_alpha_240))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(getContext(), R.color.white))
                .sectionTextSize(18)
                //     .showThumbText()
                //     .thumbTextColor(ContextCompat.getColor(getContext(), R.color.red_a700))
                //     .thumbTextSize(12)
                .bubbleColor(ContextCompat.getColor(getContext(), R.color.white))
                .bubbleTextSize(14)
                .bubbleTextColor(Color.BLACK)
                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BOTTOM_SIDES)
                .build();
    }

    private void setAddtionBtnPosition(boolean isEmpty) {
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (isEmpty == true) {
            param.addRule(RelativeLayout.BELOW, R.id.lifestyle_date_img);
            param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lifestyle_date_img);
        } else {
            param.addRule(RelativeLayout.BELOW, selectedLSImg.get(selectedLSImg.size() - 1).getId());
            param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.lifestyle_date_img);
        }
        param.setMargins(60, 0, 0, 0);
        lifestyleAdditionBtn.setLayoutParams(param);
    }

    private void setAddtionTvPosition(boolean isEmpty) {
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (isEmpty == true) {
            param.addRule(RelativeLayout.BELOW, R.id.lifestyle_title_tv);
            param.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.lifestyle_addition_img);
            param.addRule(RelativeLayout.RIGHT_OF, R.id.lifestyle_addition_img);
        } else {
            param.addRule(RelativeLayout.BELOW, selectedLSImg.get(selectedLSImg.size() - 1).getId());
            param.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.lifestyle_addition_img);
            param.addRule(RelativeLayout.RIGHT_OF, R.id.lifestyle_addition_img);
        }
        param.setMargins(30, 0, 0, 0);
        lifestlyeAdditionTv.setLayoutParams(param);
    }

}
