package application.project.mhm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import application.project.mhm.data_transfer_objects.ProfileAccount;
import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-03.
 */

public class RegisterActivity extends Activity implements Button.OnClickListener {
    public static final int REGISTER_INTENT_CODE = 1111;

    private EditText etIdInput = null;
    private EditText etPsInput = null;
    private EditText etPsReinput = null;

    private Button backBtn = null;
    private Button registerBtn = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);

        this.manageResource();

    }

    private void manageResource() {
        this.etIdInput = (EditText) findViewById(R.id.rg_account_input_tf);
        this.etPsInput = (EditText) findViewById(R.id.rg_password_input_tf);
        this.etPsReinput= (EditText) findViewById(R.id.rg_password_reinput_tf);
        this.backBtn = (Button) findViewById(R.id.rg_back_btn);
        this.registerBtn = (Button) findViewById(R.id.rg_register_check_btn);

        this.registerListener();
    }

    private void registerListener() {
        this.backBtn.setOnClickListener(this);
        this.registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rg_back_btn:
                intent.putExtra("new_profile_info", "CANCEL");
                setResult(REGISTER_INTENT_CODE, intent);
                finish();
                break;
            case R.id.rg_register_check_btn:
                ProfileAccount pa = new ProfileAccount(etIdInput.getText().toString(), etPsInput.getText().toString());
                // Store the profile account info in DB
                //intent.putExtra("new_profile_info", (Serializable) pa);
                //setResult(REGISTER_INTENT_CODE, intent);
                intent.putExtra("new_profile_info", "SUCCESS");
                setResult(REGISTER_INTENT_CODE, intent);
                Toast toast = Toast.makeText(this, "등록을 완료했습니다.", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                break;
        }
    }
}
