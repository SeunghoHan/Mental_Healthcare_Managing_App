package application.project.mhm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import application.project.mhm.debug_keygen.MemoryManager;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-01.
 */

public class LoginActivity extends Activity implements Button.OnClickListener {
    public static final int REGISTER_INTENT_CODE = 1111;
    public static final String LOGIN = "로그인";
    public static final String REGISTER = "등록";

    private Button registerBtn = null;
    private EditText etId = null;
    private EditText etPw = null;
    private InputMethodManager imm = null;

    private Picasso picasso = null;
    private LruCache picassoLruCache = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        this.initResource();

        etId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etId.getText().length() > 0 || etPw.getText().length() > 0) {
                    registerBtn.setText(LOGIN);
                } else {
                    registerBtn.setText(REGISTER);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * To manage the resources in this activity
     */
    private void initResource() {
        this.etId = (EditText) findViewById(R.id.lg_account_tf);
        this.etPw = (EditText) findViewById(R.id.lg_password_tf);
        this.registerBtn = (Button) findViewById(R.id.lg_register_btn);
        this.imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        this.registerListener();

        this.picassoLruCache = new LruCache(this);
        this.picasso = new Picasso.Builder(this) //
                .memoryCache(picassoLruCache) //
                .build();
    }

    /**
     * To register the listeners in this activity
     */
    private void registerListener() {
        this.registerBtn.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.imm.hideSoftInputFromWindow(etId.getWindowToken(), 0);
        this.imm.hideSoftInputFromWindow(etPw.getWindowToken(), 0);
        return true;
        //return super.onTouchEvent(event);
    }

    /**
     * To handle mouse click event
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.lg_register_btn:
               // if (checkString(etId.getText().toString()) && checkString(etPw.getText().toString())) {
                if(true) {
                    // check Id/Pw validation
                    // Also consider in case of id = null or pw = null
                    intent = new Intent(this, HomeActivity.class);
                    //intent.putExtra("ID", etId.getText().toString());
                    //intent.putExtra("PW", etPw.getText().toString());]
                    this.finish();
                    startActivity(intent);
                } else if (checkString(etId.getText().toString()) && !checkString(etPw.getText().toString())) {
                    Toast toast = Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!checkString(etId.getText().toString()) && checkString(etPw.getText().toString())) {
                    Toast toast = Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (registerBtn.getText().toString().compareTo(REGISTER) == 0) {
                    intent = new Intent(this, RegisterActivity.class);
                    startActivityForResult(intent, REGISTER_INTENT_CODE);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        picassoLruCache.clear();
    }

    public boolean checkString(String input) {
        if (input.length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Handel the result of register step
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case REGISTER_INTENT_CODE:
                Log.e("!!!", data.getStringExtra("new_profile_info"));
                break;
        }
    }

}
