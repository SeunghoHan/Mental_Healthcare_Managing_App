package application.project.mhm.tutorial_controller;

/**
 * Created by Seungho Han on 2017-05-06.
 */

public class TutorialStep {

    public enum Types {
        NONE, NAME, AGE, GENDER, YN
    }

    private String content;
    private Types questionType;
    private int yorn; // 0: Yes, 1: No

    public TutorialStep (String content, Types questionType) {
        this.content = content;
        this.questionType = questionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Types getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Types subQuestionType) {
        this.questionType = subQuestionType;
    }

    public int getYorn() {
        return yorn;
    }

    public void setYorn(int yorn) {
        this.yorn = yorn;
    }
}
