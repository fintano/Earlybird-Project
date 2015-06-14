package kr.co.lguplus.last;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    private int mYear, mMonth, mDay;

    public void onBackPressed() {
        // TODO Auto-generated method stub
         super.onBackPressed(); //지워야 실행됨
         finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);

        ID = (EditText)findViewById(R.id.register_edit_id);
        PW = (EditText)findViewById(R.id.register_edit_pw);
        // Need to revise it !
        AGE = (EditText)findViewById(R.id.register_edit_birthday);
        rd = (RadioGroup)findViewById(R.id.gender);
        //rd2 = (RadioGroup)findViewById(R.id.type);
        NAME = (EditText)findViewById(R.id.register_edit_name);
        register = (Button)findViewById(R.id.register_button_login);
        //login = (Button)findViewById(R.id.login);

        // show calender to set Birthday
        AGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dlgDate = new DatePickerDialog(RegisterActivity.this, myDateSetListener, mYear, mMonth, mDay);
                dlgDate.show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // get user's information and store them
                IDtxt = ID.getText().toString();
                PWtxt = PW.getText().toString();
                ageInfo = AGE.getText().toString();
                genderInfo = rd.getCheckedRadioButtonId();
                typeInfo = 0; //default value
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

            }
        });
    }
    private void updateDisplay(String str) {
        AGE.setText(str);
    }

    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            int tag = (Integer) view.getTag();
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String str;
            str = String.format("%d년 %d월 %d일", mYear, mMonth + 1, mDay);
            UserInfo.getUserInfo().setFromDate(str);
            updateDisplay(str);
        }
    };
}