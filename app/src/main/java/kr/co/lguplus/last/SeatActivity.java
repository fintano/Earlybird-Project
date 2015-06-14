package kr.co.lguplus.last;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;


public class SeatActivity extends Activity {

    private int mYear, mMonth, mDay;
    private static final int DATE_DIALOG_FROM = 0;
    private static final int DATE_DIALOG_TO = 1;
    private static final int price = 150000;
    private int[] seatNum = new int[18];
    private int[] isSelected = new int[18];
    private Button[] btn;
    private TextView fromTextView;
    private TextView toTextView;

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat);
        //loginInfo = new loginStruct();
        Button seatSearch = (Button) findViewById(R.id.seat_btn_search);
        final LinearLayout seatLayout = (LinearLayout) findViewById(R.id.seat_layout2);
        final TextView seatTextView = (TextView) findViewById(R.id.seat_info_seat);
        final TextView priceTextView = (TextView) findViewById(R.id.seat_info_price);
        final Button payBtn = (Button) findViewById(R.id.seat_btn_pay);
         fromTextView = (TextView) findViewById(R.id.textView);
         toTextView = (TextView) findViewById(R.id.textView3);
        int beforeSelectedBtn;
        StringBuffer str;
                /* initialize buttons */
        btn = new Button[]
                {
                        (Button) findViewById(R.id.seat_btn1),
                        (Button) findViewById(R.id.seat_btn2),
                        (Button) findViewById(R.id.seat_btn3),
                        (Button) findViewById(R.id.seat_btn4),
                        (Button) findViewById(R.id.seat_btn5),
                        (Button) findViewById(R.id.seat_btn6),
                        (Button) findViewById(R.id.seat_btn7),
                        (Button) findViewById(R.id.seat_btn8),
                        (Button) findViewById(R.id.seat_btn9),
                        (Button) findViewById(R.id.seat_btn10),
                        (Button) findViewById(R.id.seat_btn11),
                        (Button) findViewById(R.id.seat_btn12),
                        (Button) findViewById(R.id.seat_btn13),
                        (Button) findViewById(R.id.seat_btn14),
                        (Button) findViewById(R.id.seat_btn15),
                        (Button) findViewById(R.id.seat_btn16),
                        (Button) findViewById(R.id.seat_btn17),
                        (Button) findViewById(R.id.seat_btn18),
                };
        /*********************** Date Picker ************************/
        // set from Date
        fromTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // ���� ��¥ ��������
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dlgDate = new DatePickerDialog(SeatActivity.this, myDateSetListener, mYear, mMonth, mDay);
                dlgDate.getDatePicker().setTag(DATE_DIALOG_FROM);
                dlgDate.show();
                //updateDisplay(fromTextView);
            }
        });


        // set to Date
        toTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //���� ��¥ ��������
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                // showDialog(DATE_DIALOG_ID);
                DatePickerDialog dlgDate = new DatePickerDialog(SeatActivity.this, myDateSetListener, mYear, mMonth, mDay);
                dlgDate.getDatePicker().setTag(DATE_DIALOG_TO);
                dlgDate.show();
                //updateDisplay(toTextView);
            }
        });

        /***********************************************************************/

        /************************* Show Seat ***********************************/

        /* Set Event of Buttons when user click the button */
        for (int i = 17; i >= 0; i--) {
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button tmpBtn = (Button) view;
                    int tmp = Integer.parseInt(tmpBtn.getText().toString());
                    int isexist = 0;
                    /* Only reservable seat */
                    if (seatNum[tmp - 1] != 1) {
                        if (isSelected[tmp - 1] == 0) {
                            //payTextView.setText("Seat " + tmp + " 150,000 Won");
                            tmpBtn.setBackgroundResource(R.drawable.roundedbutton_seat_select);
                            //payBtn.setVisibility(View.VISIBLE);
                            isSelected[tmp - 1] = 1;
                        } else {
                            //payTextView.setText("");
                            tmpBtn.setBackgroundResource(R.drawable.roundedbutton_seat);
                            //payBtn.setVisibility(View.INVISIBLE);
                            isSelected[tmp - 1] = 0;
                        }
                    }
                    /* check if each seat is selected and then print seat and price information in below textview*/
                    int cnt = 0;
                    StringBuffer str = new StringBuffer();
                    for (int i = 17; i >= 0; i--) {
                        if (isSelected[i] == 1) {
                            str.append(i+1 + " ");
                            cnt++;
                        }
                    }
                    seatTextView.setText(str);
                    // set seat Number of current user
                    UserInfo.getUserInfo().setSeatNo(str.toString());
                    if (cnt == 0) {
                        seatTextView.setText("");
                        priceTextView.setText("");
                        payBtn.setVisibility(View.INVISIBLE);
                    } else
                        payBtn.setVisibility(View.VISIBLE);
                    priceTextView.setText(Integer.toString(cnt * price));
                }
            });
        }
        /* Set event of search button */
        seatSearch.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                // Randomly make picked seat
                Random rd = new Random();
                int tmp;
                Toast toast;

                // If user didn't enter date ..
                if ( fromTextView.getText().equals("") || toTextView.getText().equals("")) {
                    toast = Toast.makeText(getApplicationContext(),
                            "Please enter date", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    for (int i = 17; i >= 0; i--) {
                        isSelected[i] = 0;
                        seatNum[i] = 0;
                        btn[i].setBackgroundResource(R.drawable.roundedbutton_seat);
                    }
                /* picked seats */
                    for (int i = 10; i >= 0; i--) {
                        tmp = rd.nextInt(18);
                        btn[tmp].setBackgroundResource(R.drawable.noseat_xml);
                        seatNum[tmp] = 1;
                    }
                    seatLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        // set option of pay Buttons when user click it
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SeatActivity.this,PayConfirmActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putInt("EXTRA_INT",UserInfo.FROM_PAY);
                startActivity(intent);

            }
        });
    }

    /**
     * ********************* End onCreate ***********************
     */

    private void updateDisplay(TextView tv,String str) {
        tv.setText(str);
    }

    //DatePicker ������
    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            int tag = (Integer) view.getTag();
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String str;
            switch (tag) {
                case DATE_DIALOG_FROM:
                    str = String.format("%d-%d-%d", mYear, mMonth + 1, mDay);
                    UserInfo.getUserInfo().setFromDate(str);
                    updateDisplay(fromTextView, str);
                    break;
                case DATE_DIALOG_TO:
                    str = String.format("%d-%d-%d", mYear, mMonth + 1, mDay);
                    UserInfo.getUserInfo().setToDaTe(str);
                    updateDisplay(toTextView,str);
                    break;
            }
        }
    };


}