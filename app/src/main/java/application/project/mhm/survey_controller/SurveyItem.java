package application.project.mhm.survey_controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungho Han on 2017-05-14.
 */

public class SurveyItem implements Serializable {

    private String question = null;
    private List<String> answers = null;

    public SurveyItem (String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void addAnswer (String answer) {
        this.answers.add(answer);
    }

}
