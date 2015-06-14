package kr.co.lguplus.last;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class PayConfirmActivity extends Activity {

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        Button confirmBtn = (Button) findViewById(R.id.btn_pay);

        final CheckBox cb_all = (CheckBox) findViewById(R.id.checkBox1);

        /* 결제 서비스 약관에 모두 동의할 때 그 하위에 있는 약관도 같이 동의하게 한다.*/
        cb_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox2);
            final CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox3);
            final CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox4);
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (buttonView.getId() == R.id.checkBox1) {
                    if (isChecked) {
                        cb1.setChecked(true);
                        cb2.setChecked(true);
                        cb3.setChecked(true);
                    } else {

                        cb1.setChecked(false);
                        cb2.setChecked(false);
                        cb3.setChecked(false);

                    }
                }
            }
        });

        // When you click the pay button , it show confirm dialog
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayConfirmActivity.this);     // 여기서 this는 Activity의 this 여기서 부터는 알림창의 속성 설정
                builder.setMessage("정말 결제 하시겠습니까?")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /*결제하면 결제 확인 내용을 띄운다.*/
                              /*고객에게 정보 확인을 시켜준다.*/

                                AlertDialog.Builder innerBuilder = new AlertDialog.Builder(PayConfirmActivity.this);     // 여기서 this는 Activity의 this 여기서 부터는 알림창의 속성 설정
                                innerBuilder.setMessage("결제가 완료되었습니다.")        // 메세지 설정
                                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                            // 확인 버튼 클릭시 설정
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                Intent intent = new Intent(PayConfirmActivity.this, ProfileActivity.class);
                                                intent.putExtra("EXTRA_INT", UserInfo.FROM_PAY);
                                                startActivity(intent);

                                            }
                                        });
                                AlertDialog innerDialog = innerBuilder.create();    // 알림창 객체 생성
                                Dialog innderD = innerBuilder.show();    // 알림창 띄우기

                            }
                        })

                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pay_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
