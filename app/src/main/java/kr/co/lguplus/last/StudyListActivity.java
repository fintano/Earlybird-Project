package kr.co.lguplus.last;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;


public class StudyListActivity extends Activity {

    TextView tv;
    private int mYear, mMonth, mDay;
    TextView dateText;

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studylist1);
        tv = (TextView) findViewById(R.id.studylist_info);
        dateText = (TextView) findViewById(R.id.studylist_date);

/*It's menu that is shown by Profile*/
        ImageButton searchbtn = (ImageButton) findViewById(R.id.study_search_btn);
        ImageButton alarmbtn = (ImageButton) findViewById(R.id.study_alarm_btn);
        ImageButton studybtn = (ImageButton) findViewById(R.id.study_study_btn);
        ImageButton billbtn = (ImageButton) findViewById(R.id.study_pay_btn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyListActivity.this, SearchDemoActivity.class);
                startActivity(intent);

            }
        });
        alarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyListActivity.this, AlarmActivity.class);
                startActivity(intent);

            }
        });
        studybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyListActivity.this, StudyListActivity.class);
                startActivity(intent);

            }
        });
        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyListActivity.this, PayActivity.class);
                startActivity(intent);

            }
        });

/***************************************************************************************/

        Button searchDateBtn = (Button) findViewById(R.id.studylist_button);

        searchDateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                // showDialog(DATE_DIALOG_ID);
                DatePickerDialog dlgDate = new DatePickerDialog(StudyListActivity.this, myDateSetListener, mYear, mMonth, mDay);
                dlgDate.show();
            }
        });

    }

    //DatePicker setting
    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String str;
            str = String.format("%d-%d-%d", mYear, mMonth + 1, mDay);
            UserInfo.getUserInfo().setFromDate(str);
            dateText.setText(str + " 열공내역");

            tv.setText(str + " 오전 10시 28분 입실\n"+str+" 오후 12시 03분 퇴실\n"+str+" 오후 1시 22분 입실\n"+str+" 오후 4시 34분 퇴실");
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_list, menu);
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
