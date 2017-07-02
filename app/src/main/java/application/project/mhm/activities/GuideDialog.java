package application.project.mhm.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import application.project.mhm.tutorial_controller.Tutorial;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-06.
 */

public abstract class GuideDialog extends Dialog {

    protected TextView content = null;
    protected TextView subBox1Tv = null;
    protected TextView subBox2Tv = null;
    protected EditText ageEt = null;
    protected EditText nameEt = null;
    protected ImageView subBox1 = null;
    protected ImageView subBox2 = null;
    protected ImageView nextBtn = null;
    protected ImageView finishBtn = null;
    protected ImageView skipBtn = null;
    protected ImageView backBtn = null;
    protected ImageView maleBtn = null;
    protected ImageView femaleBtn = null;

    private Context context = null;


    public GuideDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tutorial);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.initResource();
        this.context = context;
    }

    /**
     * To manage the resources in this activity
     */
    private void initResource () {
        this.content = (TextView) findViewById(R.id.dig_content_tv);
        this.subBox1Tv = (TextView) findViewById(R.id.dig_sub1_tv);
        this.subBox2Tv = (TextView) findViewById(R.id.dig_sub2_tv);
        this.ageEt = (EditText) findViewById(R.id.dig_age_et);
        this.nameEt = (EditText) findViewById(R.id.dig_name_et);
        this.subBox1 = (ImageView) findViewById(R.id.dig_sub_box1);
        this.subBox2 = (ImageView) findViewById(R.id.dig_sub_box2);
        this.nextBtn = (ImageView) findViewById(R.id.dig_next_btn);
        this.finishBtn = (ImageView) findViewById(R.id.dig_finish_btn);
        this.skipBtn = (ImageView) findViewById(R.id.dig_skip_btn);
        this.backBtn = (ImageView) findViewById(R.id.dig_back_btn);
        this.maleBtn = (ImageView) findViewById(R.id.dig_male_img);
        this.femaleBtn = (ImageView) findViewById(R.id.dig_female_img);
        this.registerListener();
    }

    /**
     * To register the listeners in this activity
     */
    public abstract void registerListener();

    abstract public void startDialog();

    abstract public void dialogSetting();

    public void setVisibleSetting() {

    }
}
