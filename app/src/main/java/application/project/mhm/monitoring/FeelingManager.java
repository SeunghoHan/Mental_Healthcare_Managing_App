package application.project.mhm.monitoring;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-06-29.
 */

public class FeelingManager {
    private static int BTN_PREFIX_ID = 1485479;
    private int layoutWidth = 0;
    private int layoutHeight = 0;
    private RelativeLayout layout = null;
    private Context context = null;
    private List<Button> feelingList = null;
    private List<Integer> visibilityList = null;

    public FeelingManager(Context context, RelativeLayout layout, int[] displaySize) {
        this.context = context;
        this.layout = layout;
        this.feelingList = new ArrayList<>();
        this.visibilityList = new ArrayList<>();
        this.layoutWidth = displaySize[0];
        this.layoutHeight = displaySize[1];
    }

    public void createFeelingList(int visible, View.OnClickListener listener) {
        for (int i = 0; i < FeelingsObj.FeelingsTypes.length; i++) {
            Button btn = new Button(context);
            btn.setBackgroundResource(R.drawable.button_selector_for_feelings);
            btn.setText(FeelingsObj.FeelingsTypes[i]);
            btn.setTextSize(15);
            btn.setTextColor(Color.WHITE);
            btn.setScaleX(0.8f);
            btn.setScaleY(0.65f);
            btn.setOnClickListener(listener);
            btn.setSelected(false);
            btn.setId(BTN_PREFIX_ID + 1);
            BTN_PREFIX_ID += 1;
            btn.setVisibility(visible);
            this.layout.addView(btn);

            this.feelingList.add(btn);
            this.visibilityList.add(visible);
        }
    }

    public void addNewButton(Button newBtn) {
        feelingList.add(newBtn);
        visibilityList.add(View.VISIBLE);
    }


    public void updateFeelingsList() {
        int curTotWidth = 0;
        int lastAnchorId = feelingList.get(0).getId();
        int prevBtnId = lastAnchorId;
        boolean firstItem = false;

        for (int i = 0; i < feelingList.size(); i++) {
            Button btn = feelingList.get(i);

            if (visibilityList.get(i) == View.GONE || visibilityList.get(i) == View.INVISIBLE)
                continue;

            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            curTotWidth += btn.getMeasuredWidth();

            if (firstItem == false) {
                param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                firstItem = true;
            } else {
                if (curTotWidth < layoutWidth) {
                    param.addRule(RelativeLayout.RIGHT_OF, prevBtnId);
                    if (lastAnchorId != feelingList.get(0).getId())
                        param.addRule(RelativeLayout.BELOW, lastAnchorId);
                } else {
                    lastAnchorId = prevBtnId;
                    param.addRule(RelativeLayout.BELOW, lastAnchorId);
                    curTotWidth = btn.getWidth();
                }
            }
            btn.setLayoutParams(param);
            prevBtnId = btn.getId();
        }
    }

    public void updateFeelingsList2(int id) {
        int curTotWidth = 0;
        int lastAnchorId = feelingList.get(0).getId();
        int prevBtnId = lastAnchorId;
        boolean firstItem = false;

        for (int i = 0; i < feelingList.size(); i++) {
            Button btn = feelingList.get(i);

            if (visibilityList.get(i) == View.GONE || visibilityList.get(i) == View.INVISIBLE)
                continue;

            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            curTotWidth += btn.getWidth();

            if (firstItem == false) {
                param.addRule(RelativeLayout.LEFT_OF, id);
                firstItem = true;
                curTotWidth += 30;
            } else {
                if (curTotWidth < layoutWidth) {
                    param.addRule(RelativeLayout.RIGHT_OF, prevBtnId);
                    if (lastAnchorId != feelingList.get(0).getId())
                        param.addRule(RelativeLayout.BELOW, lastAnchorId);
                } else {
                    lastAnchorId = prevBtnId;
                    param.addRule(RelativeLayout.BELOW, lastAnchorId);
                    curTotWidth = btn.getWidth();
                }
            }
            btn.setLayoutParams(param);
            prevBtnId = btn.getId();
        }
    }

    public void setButtonVisibility(int idx, int visible) {
        this.feelingList.get(idx).setVisibility(visible);
        this.visibilityList.set(idx, visible);
    }

    public void setAllButtonVisibility(int visible) {
        for (int i = 0; i < feelingList.size(); i++) {
            this.feelingList.get(i).setVisibility(visible);
            this.visibilityList.set(i, visible);
        }
    }

    public void setSelected (int idx, boolean selected) {
        feelingList.get(idx).setSelected(selected);
    }

    public int getNumOfBtn () {
        return feelingList.size();
    }

    public Button getLastButton () {
        return feelingList.get(getNumOfBtn()-1);
    }

    public int getBtnId (Button btn) {
        return btn.getId();
    }

    public int[] getButtonSize(int idx) {
        return new int[]{feelingList.get(idx).getWidth(), feelingList.get(idx).getHeight()};
    }

    public boolean checkSelectedFeeling() {
        boolean isSelected = false;

        for (Button btn : feelingList) {
            if (btn.isSelected() == true) {
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }

    public List<Button> getFeelingList() {
        return feelingList;
    }

    public List<Integer> getVisibilityList() {
        return visibilityList;
    }
}