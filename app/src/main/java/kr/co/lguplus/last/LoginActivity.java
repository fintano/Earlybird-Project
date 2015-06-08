package kr.co.lguplus.last;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginActivity extends Activity {
    EditText ID, PW, res_email, code, newpass;
    Button login, cont, cont_code, cancel, cancel1, register, map;
    String IDtxt, PWtxt, email_res_txt, code_txt, npass_txt;
    List<loginStruct> params;
    Dialog reset;
    loginStruct loginInfo;
    //ServerRequest sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstview);
        //loginInfo = new loginStruct();

        ID = (EditText) findViewById(R.id.first_view_edit_id);
        PW = (EditText) findViewById(R.id.first_view_edit_pw);
        login = (Button) findViewById(R.id.first_view_button_login);
        register = (Button) findViewById(R.id.first_view_button_register);
        //forpass = (Button)findViewById(R.id.forgotpass);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regactivity);
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                IDtxt = ID.getText().toString();
                PWtxt = PW.getText().toString();

                Intent intent = getIntent();
                //params = (List<loginStruct>)intent.getSerializableExtra("loginData");

                Iterator<loginStruct> itr = UserInfo.user.iterator();

                while (itr.hasNext()) {
                    loginInfo = itr.next();
                    if (IDtxt.equals(loginInfo.ID) && PWtxt.equals(loginInfo.PW)) {

                        // set permitted user information
                        UserInfo.setUserInfo(loginInfo);
                        Intent profactivity = new Intent(LoginActivity.this, ProfileActivity.class);
                        //Bundle bundle = new Bundle();
                        //bundle.putInt("EXTRA_INT",UserInfo.FROM_LOGIN);
                        profactivity.putExtra("EXTRA_INT", UserInfo.FROM_LOGIN);
                        startActivity(profactivity);
                        finish();
                    }
                }

            }
        });
    }
}