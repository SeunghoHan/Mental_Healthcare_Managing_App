package application.project.mhm.tutorial_controller;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import application.project.mhm.activities.GuideDialog;
import application.project.mhm.activities.SurveyActivity;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-06.
 */

public class TutorialDialog extends GuideDialog implements View.OnClickListener {
    private Context context;
    private Tutorial tutorial = null;
    static InputMethodManager ime = null;



    public TutorialDialog(Context context) {
        super(context);
        this.context = context;
        this.tutorial = new Tutorial();
        this.defaultSetting();
        this.registerListener();
    }

    @Override
    public void registerListener() {
        skipBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        subBox1Tv.setOnClickListener(this);
        subBox2Tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dig_skip_btn:
                this.tutorial = null;
                this.dismiss();
                break;
            case R.id.dig_back_btn:
                if (tutorial.getCurrentStep() == 0) {
                    // first dialog
                } else {
                    this.tutorial.backTutorialStep();
                    this.startDialog();
                }
                break;
            case R.id.dig_next_btn:
                if (tutorial.getCurrentStep() >= tutorial.getStepNum()-1) {
                    // last dialog
                } else {
                    if(tutorial.getCurrentTutorialStep().getQuestionType() == TutorialStep.Types.NAME) {
                        if (nameEt.getText().length() < 1) {
                            Toast toast = Toast.makeText(this.context, "별명을 입력해주세요.", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        this.ime.hideSoftInputFromWindow(nameEt.getWindowToken(), 0);
                    } else if(tutorial.getCurrentTutorialStep().getQuestionType() == TutorialStep.Types.AGE) {
                        if (ageEt.getText().length() < 1) {
                            Toast toast = Toast.makeText(this.context, "나이를 입력해주세요.", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        this.ime.hideSoftInputFromWindow(ageEt.getWindowToken(), 0);
                    }
                    this.tutorial.nextTutorialStep();
                    this.startDialog();
                }
                break;
            case R.id.dig_sub1_tv:
                Intent intent = new Intent(context, SurveyActivity.class);
                this.context.startActivity(intent);
                this.dismiss();
                break;
            case R.id.dig_sub2_tv:
                this.tutorial = null;
                this.dismiss();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.ime.hideSoftInputFromWindow(nameEt.getWindowToken(), 0);
        this.ime.hideSoftInputFromWindow(ageEt.getWindowToken(), 0);
        return true;
    }

    @Override
    public void startDialog() {
        this.dialogSetting();
        this.show();
    }

    @Override
    public void dialogSetting() {
        TutorialStep currentStep = this.tutorial.getCurrentTutorialStep();

        content.setText(currentStep.getContent());
        switch (currentStep.getQuestionType()) {
            case NONE:
                this.setVisibleSetting(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case NAME:
                this.setVisibleSetting(View.VISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case AGE:
                this.setVisibleSetting(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case GENDER:
                this.setVisibleSetting(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.VISIBLE);
                break;
            case YN:
                this.setVisibleSetting(View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
        }
    }

    public void setVisibleSetting(int sb1, int sb2, int name, int age, int male, int female) {
        subBox1.setVisibility(sb1);
        subBox2.setVisibility(sb2);
        subBox1Tv.setVisibility(sb2);
        subBox2Tv.setVisibility(sb2);
        nameEt.setVisibility(name);
        ageEt.setVisibility(age);
        maleBtn.setVisibility(male);
        femaleBtn.setVisibility(female);
    }

    private void defaultSetting() {
        finishBtn.setVisibility(View.INVISIBLE);

        //LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View layout = inflater.inflate(R.layout.tutorial, null);
        ime = (InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
