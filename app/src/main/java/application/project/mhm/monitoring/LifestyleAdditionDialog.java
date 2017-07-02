package application.project.mhm.monitoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-07-01.
 */

public class LifestyleAdditionDialog extends Activity implements View.OnClickListener {

    private static final String INTENT_KEY = "LIFESTYLE";
    public static final int INTENT_CODE = 2222;

    private RelativeLayout sleepLayout = null;

    private List<LifestyleObj.Types> selectedLS = null;
    private Map<LifestyleObj.Types, Boolean> checkingLS = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lifestyle_addition);

        changeActivitySize();

        selectedLS = new ArrayList<>();
        checkingLS = new EnumMap<LifestyleObj.Types, Boolean>(LifestyleObj.Types.class);
        checkingLS.put(LifestyleObj.Types.SLEEP, false);
        checkingLS.put(LifestyleObj.Types.APPETITE, false);
        checkingLS.put(LifestyleObj.Types.WORK, false);

        //sleepLayout = (RelativeLayout) findViewById(R.id.lifestyle_sleep_layout);

        ImageView sleepIv = (ImageView) findViewById(R.id.lifestyle_sleep_img);
        sleepIv.setOnClickListener(this);

        ImageView appetiteIv = (ImageView) findViewById(R.id.lifestyle_appetite_img);
        appetiteIv.setOnClickListener(this);

        ImageView workIv = (ImageView) findViewById(R.id.lifestyle_work_img);
        workIv.setOnClickListener(this);

        TextView sleepTv = (TextView) findViewById(R.id.lifestyle_sleep_tv);
        sleepTv.setOnClickListener(this);

        TextView appetiteTv = (TextView) findViewById(R.id.lifestyle_appetite_tv);
        appetiteTv.setOnClickListener(this);

        TextView workTv = (TextView) findViewById(R.id.lifestyle_work_tv);
        workTv.setOnClickListener(this);

        TextView cancel = (TextView) findViewById(R.id.lifestyle_addition_cancel_btn);
        cancel.setOnClickListener(this);

        TextView ok = (TextView) findViewById(R.id.lifestyle_addition_ok_btn);
        ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lifestyle_addition_cancel_btn) {
            finish();
        } else if (v.getId() == R.id.lifestyle_addition_ok_btn) {
            Intent intent = new Intent();
            intent.putExtra(INTENT_KEY, (Serializable) selectedLS);
            setResult(INTENT_CODE, intent);
            finish();
        } else if (v.getId() == R.id.lifestyle_sleep_img || v.getId() == R.id.lifestyle_sleep_tv) {
            if (checkingLS.get(LifestyleObj.Types.SLEEP) == true) {
                selectedLS.remove(selectedLS.indexOf(LifestyleObj.Types.SLEEP));
                checkingLS.put(LifestyleObj.Types.SLEEP, false);
            } else {
                selectedLS.add(LifestyleObj.Types.SLEEP);
                checkingLS.put(LifestyleObj.Types.SLEEP, true);
            }
        } else if (v.getId() == R.id.lifestyle_appetite_img || v.getId() == R.id.lifestyle_appetite_tv) {
            if (checkingLS.get(LifestyleObj.Types.APPETITE) == true) {
                selectedLS.remove(selectedLS.indexOf(LifestyleObj.Types.APPETITE));
                checkingLS.put(LifestyleObj.Types.APPETITE, false);
            } else {
                selectedLS.add(LifestyleObj.Types.APPETITE);
                checkingLS.put(LifestyleObj.Types.APPETITE, true);
            }
        } else if (v.getId() == R.id.lifestyle_work_img || v.getId() == R.id.lifestyle_work_tv) {
            if (checkingLS.get(LifestyleObj.Types.WORK) == true) {
                selectedLS.remove(selectedLS.indexOf(LifestyleObj.Types.WORK));
                checkingLS.put(LifestyleObj.Types.WORK, false);
            } else {
                selectedLS.add(LifestyleObj.Types.WORK);
                checkingLS.put(LifestyleObj.Types.WORK, true);
            }
        }
    }

    private void changeActivitySize() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int[] size = new int[2];
        size[0] = (int) (display.getWidth() * 0.9);
        size[1] = (int) (display.getHeight() * 0.85);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = size[0];
        params.height = size[1];
        this.getWindow().setAttributes(params);
    }
}
