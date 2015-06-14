package kr.co.lguplus.last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class AlarmActivity extends Activity {

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm1);

/*It's menu that is shown by Profile*/
        ImageButton searchbtn = (ImageButton) findViewById(R.id.alarm_search_btn);
        ImageButton alarmbtn = (ImageButton) findViewById(R.id.alarm_alarm_btn);
        ImageButton studybtn = (ImageButton) findViewById(R.id.alarm_study_btn);
        ImageButton billbtn = (ImageButton) findViewById(R.id.alarm_bill_btn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, SearchDemoActivity.class);
                startActivity(intent);
            }
        });
        alarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this,AlarmActivity.class);
                startActivity(intent);

            }
        });
        studybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, StudyListActivity.class);
                startActivity(intent);

            }
        });
        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, PayActivity.class);
                startActivity(intent);

            }
        });

/***************************************************************************************/

        Button alarmbutton = (Button) findViewById(R.id.alarm_button_register);

        alarmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(),
                        "알람이 설정 되었습니다.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Intent intent = new Intent(AlarmActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm, menu);
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
