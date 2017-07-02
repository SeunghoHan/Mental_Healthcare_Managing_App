package application.project.mhm.monitoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;

import application.project.mhm.mental_healthcare_manaing.R;


/**
 * Created by Seungho Han on 2017-05-22.
 */

public class FindingMyFeelingsDialog extends Activity implements View.OnClickListener {
    private static final String INTENT_KEY = "FINDING";
    public static final int INTENT_CODE = 1111;

    private RelativeLayout selectedFeelingListLayout = null;
    private RelativeLayout feelingListLayout = null;

    private FeelingManager feelingList = null;
    private FeelingManager selectedList = null;

    private InputMethodManager imm = null;

    private EditText search = null;
    private TextView cancelBtn = null;
    private TextView okBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.find_feelings);

        Intent intent = getIntent();
        int[] size = this.changeActivitySize();
        feelingListLayout = (RelativeLayout) findViewById(R.id.finding_feelings_list);
        selectedFeelingListLayout = (RelativeLayout) findViewById(R.id.finding_feelings_selected_list);

        feelingList = new FeelingManager(getApplicationContext(), feelingListLayout, size);
        selectedList = new FeelingManager(getApplicationContext(), selectedFeelingListLayout, size);
        feelingList.createFeelingList(View.VISIBLE, this);
        selectedList.createFeelingList(View.INVISIBLE, this);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        cancelBtn = (TextView) findViewById(R.id.finding_cancel_btn);
        cancelBtn.setOnClickListener(this);
        okBtn = (TextView) findViewById(R.id.finding_ok_btn);
        okBtn.setOnClickListener(this);

        search = (EditText) findViewById(R.id.finding_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = search.getText().toString();
                if (inputText.length() < 0) return;
//                feelingsBtnList.get(0).setVisibility(View.GONE);

                for (int i = 0; i < feelingList.getFeelingList().size(); i++) {
                    if (feelingList.getFeelingList().get(i).getText().toString().contains(inputText)) {
                        feelingList.setButtonVisibility(i, View.VISIBLE);
                    } else {
                        feelingList.setButtonVisibility(i, View.GONE);
                    }
                }
                feelingList.updateFeelingsList();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        feelingList.updateFeelingsList();
        selectedList.setAllButtonVisibility(View.GONE);
        selectedList.updateFeelingsList();
    }

    private int[] changeActivitySize() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int[] size = new int[2];
        size[0] = (int) (display.getWidth() * 0.9);
        size[1] = (int) (display.getHeight() * 0.85);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = size[0];
        params.height = size[1];
        this.getWindow().setAttributes(params);

        return size;
    }


    @Override
    public void onClick(View v) {
        if (v == cancelBtn) {
            finish();
        } else if (v == okBtn) {
            Intent intent = new Intent();
            intent.putExtra(INTENT_KEY, (Serializable) selectedList.getVisibilityList());
            setResult(INTENT_CODE, intent);
            finish();
        } else {
            for (int i = 0; i < feelingList.getFeelingList().size(); i++) {
                if (v.getId() == feelingList.getFeelingList().get(i).getId()) {
                    if (feelingList.getFeelingList().get(i).isSelected() == true) {
                    } else {
                        feelingList.setSelected(i, true);
                        selectedList.setButtonVisibility(i, View.VISIBLE);
                        selectedList.setSelected(i, true);
                        selectedList.updateFeelingsList();
                    }
                }
            }
        }

        if (selectedList.checkSelectedFeeling() == true) {
            for (int i = 0; i < selectedList.getFeelingList().size(); i++) {
                if (v.getId() == selectedList.getFeelingList().get(i).getId()) {
                    selectedList.setSelected(i, false);
                    selectedList.setButtonVisibility(i, View.INVISIBLE);
                    selectedList.updateFeelingsList();

                    feelingList.setSelected(i, false);
                    feelingList.updateFeelingsList();
                }
            }
        }
    }
}
