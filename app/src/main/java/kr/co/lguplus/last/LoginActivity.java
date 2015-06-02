package kr.co.lguplus.last;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    SharedPreferences pref;
    Dialog reset;
    loginStruct loginInfo;
    //ServerRequest sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //loginInfo = new loginStruct();

        ID = (EditText) findViewById(R.id.login_ID);
        PW = (EditText) findViewById(R.id.login_PW);
        login = (Button) findViewById(R.id.loginbtn);
        register = (Button) findViewById(R.id.registerbtn);
        //forpass = (Button)findViewById(R.id.forgotpass);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);


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

                while(itr.hasNext()) {
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
/*
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", IDtxt));
                params.add(new BasicNameValuePair("password", PWtxt));

                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://10.0.2.2:8080/login", params);
  */     /*
                if (json != null) {
                    try {
                        String jsonstr = json.getString("response");
                        if (json.getBoolean("res")) {
                            String token = json.getString("token");
                            String grav = json.getString("grav");
                            SharedPreferences.Editor edit = pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                            edit.putString("grav", grav);
                            edit.commit();
                            Intent profactivity = new Intent(LoginActivity.this, ProfileActivity.class);

                            startActivity(profactivity);
                            finish();
                        }

                        Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                */
            }
        });
    }
}