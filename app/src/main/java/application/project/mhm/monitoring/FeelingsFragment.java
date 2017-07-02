package application.project.mhm.monitoring;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.List;

import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-20.
 */


public class FeelingsFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener, View.OnTouchListener {

    private static final String INTENT_KEY = "FINDING";
    private static final String KEY = "FELLINGS";
    private static final int FIND_FEELING_BTN_ID = 45312;
    public static final int INTENT_CODE = 1111;

    private InputMethodManager imm = null;

    private FeelingsObj feelingsObj = null;
    private DatePickerDialog datePickerDialog = null;
    private Button findFeelingsBtn = null;
    private EditText conditionContent = null;
    private FloatingActionButton saveBtn = null;
    private RelativeLayout findingLayout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feelingsObj = (FeelingsObj) getArguments().getSerializable(KEY);
        int[] curDate = getCurrentDate();
        datePickerDialog = new DatePickerDialog(getActivity(), this, curDate[0], curDate[1], curDate[2]);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feelings_frag, container, false);

        ImageView datePicker = (ImageView) view.findViewById(R.id.feelings_date_img);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        findingLayout = (RelativeLayout) view.findViewById(R.id.find_feeling_layout);

        this.createFindingFeelingBtn();

        conditionContent = (EditText) view.findViewById(R.id.feelings_condition_content);

        saveBtn = (FloatingActionButton) view.findViewById(R.id.feelings_save_btn);
        saveBtn.setOnClickListener(this);

        return view;
    }

    public static FeelingsFragment newInstance(FeelingsObj fellingObj) {
        FeelingsFragment fragment = new FeelingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, fellingObj);
        fragment.setArguments(args);
        return fragment;
    }

    private void createFindingFeelingBtn() {
        findFeelingsBtn = new Button(getContext());
        findFeelingsBtn.setBackground(getResources().getDrawable(R.drawable.find_feelings));
        findFeelingsBtn.setAlpha(0.65f);
        findFeelingsBtn.setScaleX(0.4f);
        findFeelingsBtn.setScaleY(0.4f);
        findFeelingsBtn.setId(FIND_FEELING_BTN_ID + 10);
        findFeelingsBtn.setOnClickListener(this);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //  RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(80, 80);
        param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        findFeelingsBtn.setLayoutParams(param);
        findingLayout.addView(findFeelingsBtn);
    }

    private int[] getCurrentDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new int[]{year, month, day};
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //Log.e("!!", year + " - " + month + " - " + dayOfMonth);
    }

    @Override
    public void onClick(View v) {
//        if (v == findFeelingsBtn) {
//            Intent intent = new Intent(getActivity(), FindingMyFeelingsDialog.class);
//            startActivityForResult(intent, INTENT_CODE);
//        }
        switch (v.getId()) {
            case FIND_FEELING_BTN_ID + 10:
                //  case R.id.find_feeling_img:
                Intent intent = new Intent(getActivity(), FindingMyFeelingsDialog.class);
                startActivityForResult(intent, INTENT_CODE);
                break;
            case R.id.feelings_save_btn:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == INTENT_CODE) {
            List<Integer> result = (List<Integer>) data.getSerializableExtra(INTENT_KEY);

            if (result != null) {
                //Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                int[] size = {findingLayout.getWidth(), findingLayout.getHeight()};

                FeelingManager selectedFeelings = new FeelingManager(getContext(), findingLayout, size);
                selectedFeelings.createFeelingList(View.VISIBLE, null);
                selectedFeelings.addNewButton(findFeelingsBtn);
                result.add(new Integer(View.VISIBLE));

                for (int i = 0; i < selectedFeelings.getFeelingList().size(); i++) {
                    selectedFeelings.setButtonVisibility(i, result.get(i));
                }
                selectedFeelings.updateFeelingsList();

            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.imm.hideSoftInputFromWindow(conditionContent.getWindowToken(), 0);
        return true;
    }
}

