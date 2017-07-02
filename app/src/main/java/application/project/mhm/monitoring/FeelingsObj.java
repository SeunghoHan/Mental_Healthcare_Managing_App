package application.project.mhm.monitoring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungho Han on 2017-05-20.
 */

public class FeelingsObj implements Serializable {

    public enum FeelingsConditions {
        WORSE, BAD, NORMAL, GOOD, BEST
    }

    public static String[] FeelingsTypes = {"배고파요", "슬퍼요", "행복해요", "화가나요", "기뻐요", "즐거워요", "AAA", "BBBBBBBBBBBBBB", "CCAACCCCCCCCCC", "DDD", "EEE", "ASDASD", "DFSQWE", "EWRFW", "FFFF", "GGG", "HHH", "III", "JJJ", "KKKK", "LLL", "MMMM"};

    private String date = null;
    private FeelingsConditions myFeelingsCondition = null;
    private List<String> myFeelingsTypes = null;
    private String mySituation = null;

    public FeelingsObj(String date, FeelingsConditions condition) {
        this.date = date;
        this.myFeelingsCondition = condition;
        this.myFeelingsTypes = new ArrayList<>();
        this.mySituation = "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FeelingsConditions getMyFeelingsCondition() {
        return myFeelingsCondition;
    }

    public void setMyFeelingsCondition(FeelingsConditions myFeelingsCondition) {
        this.myFeelingsCondition = myFeelingsCondition;
    }

    public List<String> getMyFeelingsTypes() {
        return myFeelingsTypes;
    }

    public void setMyFeelingsTypes(List<String> myFeelingsTypes) {
        this.myFeelingsTypes = myFeelingsTypes;
    }

    public void addMyFeelingsTypes(String feelings) {
        this.myFeelingsTypes.add(feelings);
    }

    public String getMySituation() {
        return mySituation;
    }

    public void setMySituation(String mySituation) {
        this.mySituation = mySituation;
    }
}
