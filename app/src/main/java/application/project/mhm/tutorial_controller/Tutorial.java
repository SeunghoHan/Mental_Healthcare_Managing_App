package application.project.mhm.tutorial_controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungho Han on 2017-05-06.
 */

public class Tutorial {

    private List<TutorialStep> steps;
    private int stepNum;
    public int currentStep;

    private String name = null;
    private int age = 0;
    private int gender = -1; // 0: Male, 1: Female

    public Tutorial() {
        this.steps = new ArrayList<>();
        this.initTutorial();
        this.stepNum = steps.size();
        this.currentStep = 0;
    }

    private boolean initTutorial() {
        String step1 = "안녕하세요! \n마음연습실에 오신걸 환영합니다!";
        this.steps.add(new TutorialStep(step1, TutorialStep.Types.NONE));

        String step2 = "저는 마실의 가이드, 000 에요.";
        this.steps.add(new TutorialStep(step2, TutorialStep.Types.NONE));

        String step3 = "등록을 하지 않으셔도 연습실을 둘러볼 수 있답니다.";
        this.steps.add(new TutorialStep(step3, TutorialStep.Types.NONE));

        String step4 = "홈 화면에 있는 메뉴를 하나씩 눌러 어떤 기능이 있는지 살펴보세요.";
        this.steps.add(new TutorialStep(step4, TutorialStep.Types.NONE));

        String step5 = "어떻게 사용하는지 잘 모르겠으면 \n언제든지 저를 불러주세요!";
        this.steps.add(new TutorialStep(step5, TutorialStep.Types.NONE));

        String step6 = "짝짝짝! \n마음 연습을 향한 첫 걸음을 떼신 것을 축하드려요!";
        this.steps.add(new TutorialStep(step6, TutorialStep.Types.NONE));

        String step7 = "저는 오늘부터 당신의 마음연습을 도와줄 가이드, 000 이에요. \n제가 뭐라고 불러드리면 될까요?";
        this.steps.add(new TutorialStep(step7, TutorialStep.Types.NAME));

        String step8 = "정말 반가워요 NONAME님! \nNONAME님께 더 적절한 도움을 드리기 위해 몇 가지만 더 물어볼께요.";
        this.steps.add(new TutorialStep(step8, TutorialStep.Types.NONE));

        String step9 = "성별이 어떻게 되시나요?";
        this.steps.add(new TutorialStep(step9, TutorialStep.Types.GENDER));

        String step10 = "나이를 여쭤봐도 될까요?";
        this.steps.add(new TutorialStep(step10, TutorialStep.Types.AGE));

        String step11 = "이번에는 NONAME님의 현재 마음 상태를 알아보는 간단한 심리검사를 준비해봤어요.";
        this.steps.add(new TutorialStep(step11, TutorialStep.Types.NONE));

        String step12 = "어때요? \n지금 해보시곘어요?";
        this.steps.add(new TutorialStep(step12, TutorialStep.Types.YN));

        this.stepNum = this.steps.size();
        return true;
    }

    public List<TutorialStep> getSteps() {
        return steps;
    }

    public void setSteps(List<TutorialStep> steps) {
        this.steps = steps;
    }

    public TutorialStep getCurrentTutorialStep() {
        if (steps == null) {
            Log.e("[Tutorial]", "getCurrentTutorialStep, null");
        }
        return this.steps.get(currentStep);
    }

    public int getCurrentStep() {
        return this.currentStep;
    }

    public void backTutorialStep() {
        this.currentStep -= 1;
    }

    public void nextTutorialStep() {
        this.currentStep += 1;
    }

    public int getStepNum() {
        return stepNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
