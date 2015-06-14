package kr.co.lguplus.last;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;


public class PayActivity extends Activity {

    Button[] paybtn;
    TextView studyInfo;

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paylist1);
        studyInfo = (TextView) findViewById(R.id.paylist_text);
        paybtn = new Button[]{
                (Button) findViewById(R.id.paylist_button_1),
                (Button) findViewById(R.id.paylist_button_2),
                (Button) findViewById(R.id.paylist_button_3),
                (Button) findViewById(R.id.paylist_button_4),
                (Button) findViewById(R.id.paylist_button_5),
                (Button) findViewById(R.id.paylist_button_6),
                (Button) findViewById(R.id.paylist_button_7),
                (Button) findViewById(R.id.paylist_button_8),
                (Button) findViewById(R.id.paylist_button_9),
                (Button) findViewById(R.id.paylist_button_10),
                (Button) findViewById(R.id.paylist_button_11),
                (Button) findViewById(R.id.paylist_button_12)
        };



/*It's menu that is shown by Profile*/
        ImageButton searchbtn = (ImageButton) findViewById(R.id.bill_search_btn);
        ImageButton alarmbtn = (ImageButton) findViewById(R.id.bill_alarm_btn);
        ImageButton studybtn = (ImageButton) findViewById(R.id.bill_study_btn);
        ImageButton billbtn = (ImageButton) findViewById(R.id.bill_pay_btn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, SearchDemoActivity.class);
                startActivity(intent);

            }
        });
        alarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, AlarmActivity.class);
                startActivity(intent);

            }
        });
        studybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, StudyListActivity.class);
                startActivity(intent);

            }
        });
        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, PayActivity.class);
                startActivity(intent);

            }
        });

/***************************************************************************************/


        for (int i = 0; i < 12; i++) {
            if (i == 4) {
                paybtn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studyInfo.setText("05.13.수요일\n" +
                                "글사랑독서실  ㅣ  (2015.5.13-2015.6.12)  ㅣ  150,000원");
                    }
                });
            } else {
                paybtn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studyInfo.setText("납부 내역 없음");
                    }
                });
            }
        }
    }
/*
    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pay, menu);
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
