package kr.co.lguplus.last;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PayActivity extends Activity {

    Button[] paybtn;
    TextView studyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paylist);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
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
