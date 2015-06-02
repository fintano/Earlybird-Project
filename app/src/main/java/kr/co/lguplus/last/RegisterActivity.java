package kr.co.lguplus.last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {
    private EditText ID,PW,AGE,NAME;
    private Button register;
    private String IDtxt,PWtxt,ageInfo,nameInfo;
    private int genderInfo,typeInfo;
    private List<loginStruct> params;
    private RadioGroup rd,rd2;
    private loginStruct loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ID = (EditText)findViewById(R.id.ID);
        PW = (EditText)findViewById(R.id.PW);
        AGE = (EditText)findViewById(R.id.age);
        rd = (RadioGroup)findViewById(R.id.gender);
        rd2 = (RadioGroup)findViewById(R.id.type);
        NAME = (EditText)findViewById(R.id.name);
        register = (Button)findViewById(R.id.registerbtn);
        //login = (Button)findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get user's information and store them
                IDtxt = ID.getText().toString();
                PWtxt = PW.getText().toString();
                ageInfo = AGE.getText().toString();
                genderInfo = rd.getCheckedRadioButtonId();
                typeInfo = rd2.getCheckedRadioButtonId();
                nameInfo = NAME.getText().toString();
                loginInfo = new loginStruct(IDtxt, PWtxt,nameInfo,ageInfo,genderInfo,typeInfo);
                // store user's information
                UserInfo.user.add(loginInfo);
                // Point!!!
                /* Need to delete next sentences later */
               // params = new ArrayList<loginStruct>();
               // params.add(loginInfo);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                //intent.putExtra("loginData", (Serializable) params);
                startActivity(intent);
                finish();
            }
        });
    }




}