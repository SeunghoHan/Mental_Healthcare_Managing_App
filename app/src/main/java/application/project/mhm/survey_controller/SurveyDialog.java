package application.project.mhm.survey_controller;

import android.content.Context;
import android.view.View;

import application.project.mhm.activities.GuideDialog;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-16.
 */

public class SurveyDialog extends GuideDialog implements View.OnClickListener {

    public enum SurveyDialogTypes {
        START, END, RESULT
    }

    private Context context;
    private SurveyDialogTypes dialogType;

    public SurveyDialog(Context context, SurveyDialogTypes type) {
        super(context);
        this.context = context;
        this.dialogType = type;
        if (type == SurveyDialogTypes.END) {
            nextBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.survey_finish));
        }
        this.registerListener();
        this.defaultSetting();
    }

    @Override
    public void registerListener() {
        nextBtn.setOnClickListener(this);
        subBox1Tv.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        subBox1Tv.setText("네!");
        subBox1Tv.setWidth(80);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dig_next_btn:
                this.dismiss();
                break;
            case R.id.dig_sub1_tv:
                this.dialogType = SurveyDialogTypes.RESULT;
                this.startDialog();
                break;
            case R.id.dig_finish_btn:
                this.dismiss();
                break;
        }
    }

    @Override
    public void startDialog() {
        this.dialogSetting();
        this.show();
    }

    @Override
    public void dialogSetting() {
        switch (this.dialogType) {
            case START:
                content.setText("@@님은 지난 2주일동안 다음의 문제들로 인해서 얼마나 자주 방해를 받았나요?");
                this.setVisibleSetting(View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case END:
                content.setText("심리검사를 마치셨습니다! 이제 결과를 확인하러 가볼까요?^^");
                this.setVisibleSetting(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case RESULT:
                //calculateResult();
                content.setText("@@님, 최근 울적한 기분이나 걱정스러운 마음이 며칠간 지속되고 계시진 않았나요? 그렇다면 마실과 함께 꾸준한 마음 연습 뿐 아니라, 학생상담센터에 전화하여 상담전문가로부터 상담을 받아보는 것을 추천해드려요");
                this.setVisibleSetting(View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                break;
        }
    }

    public void setVisibleSetting(int sb1, int next, int finish) {
        subBox1.setVisibility(sb1);
        subBox1Tv.setVisibility(sb1);
        nextBtn.setVisibility(next);
        finishBtn.setVisibility(finish);
    }

    private void defaultSetting() {
        subBox2.setVisibility(View.INVISIBLE);
        subBox2Tv.setVisibility(View.INVISIBLE);
        nameEt.setVisibility(View.INVISIBLE);
        ageEt.setVisibility(View.INVISIBLE);
        maleBtn.setVisibility(View.INVISIBLE);
        femaleBtn.setVisibility(View.INVISIBLE);
        skipBtn.setVisibility(View.INVISIBLE);
        backBtn.setVisibility(View.INVISIBLE);
    }

    private void calculateResult() {
        // calculate result using PHQ-8 or GAD-7
    }
}
