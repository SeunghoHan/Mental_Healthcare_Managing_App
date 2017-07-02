package application.project.mhm.survey_controller;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungho Han on 2017-05-14.
 */

public class Survey {
    private static Survey _instance = null;
    private List<SurveyItem> survey = null;
    private List<Integer> surveryAnswers = null;

    private Survey() {
            this.survey = new ArrayList<>();
            this.surveryAnswers = new ArrayList<>();
            this.initSurvey();

            _instance = this;
    }

    public static Survey getInstace() {
        if(_instance == null)
            new Survey();

        return _instance;
    }

    private void initSurvey() {

        List<String> answers = new ArrayList<>();
        answers.add("전혀 방해받지 않았다.");
        answers.add("며칠 동안 방해 받았다.");
        answers.add("7일 잇아 방해 받았다.");
        answers.add("거의 매일 방해 받았다.");

        this.survey.add(new SurveyItem("일 또는 여가 활동을 하는 데 흥미나 즐거움을 느끼지 못함", answers));
        this.survey.add(new SurveyItem("기분이 가라앉거나, 우울하거나, 희망이 없음", answers));
        this.survey.add(new SurveyItem("잠이 들거나 계속 잠을 자는 것이 어려움, 또는 잠을 너무 많이 잠", answers));
        this.survey.add(new SurveyItem("피곤하다고 느끼거나 기운이 거의 없음", answers));
        this.survey.add(new SurveyItem("입맛이 없거나 과식을 함", answers));
        this.survey.add(new SurveyItem("자신을 부정적으로 봄 - 혹은 자신이 실패자라고 느끼거나 자신 또는 갖고을 실망시킴", answers));
        this.survey.add(new SurveyItem("신문을 읽거나 텔레비전 보는 것과 같은 일에 집중하는 것이 어려움", answers));
        this.survey.add(new SurveyItem("다른 사람들이 주목할 정도로 너무 느리게 움직이거나 말을 함. 또는 반대로 평상시보다 많이 움직여서, 너무 안절부절 못하거나 들떠 있음", answers));
        this.survey.add(new SurveyItem("초조하거나 불안하거나 조마조마하게 느낀다", answers));
        this.survey.add(new SurveyItem("걱정하는 것을 멈추거나 조절할 수가 없다", answers));
        this.survey.add(new SurveyItem("여러 가지 것들에 대해 걱정을 너무 많이 한다", answers));
        this.survey.add(new SurveyItem("편하게 있기가 어렵다", answers));
        this.survey.add(new SurveyItem("너무 안절부절못해서 가만히 있기가 힘들다", answers));
        this.survey.add(new SurveyItem("쉽게 짜증이 나거나 쉽게 성을 내게 된다", answers));
        this.survey.add(new SurveyItem("마치 끔찍한 일이 생길 것처럼 두렵게 느껴진다", answers));
    }

    public List<SurveyItem> getSurveyList() {
        return survey;
    }
}
