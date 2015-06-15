package kr.co.lguplus.last;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ProfileActivity extends Activity {

    private final int MAX = 99999999;
    private final int MIN = 10000000;
    private ReadingRoomStruct currentUser;

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlogin1);

        currentUser = UserInfo.getUserInfo();

/*It's menu that is shown by Profile*/
        ImageButton talkbtn = (ImageButton) findViewById(R.id.profile_talk_btn);
        ImageButton searchbtn = (ImageButton) findViewById(R.id.profile_search_btn);
        ImageButton alarmbtn = (ImageButton) findViewById(R.id.profile_alarm_btn);
        ImageButton studybtn = (ImageButton) findViewById(R.id.profile_study_btn);
        ImageButton billbtn = (ImageButton) findViewById(R.id.profile_pay_btn);

        talkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MessageActivity.class);
                startActivity(intent);

            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, SearchDemoActivity.class);
                startActivity(intent);

            }
        });
        alarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,AlarmActivity.class);
                startActivity(intent);

            }
        });
        studybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, StudyListActivity.class);
                startActivity(intent);

            }
        });
        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, PayActivity.class);
                startActivity(intent);

            }
        });

/***************************************************************************************/

        TextView nameView = (TextView) findViewById(R.id.afterlogin_edit_name);
        TextView infoView;
        Button selectButton;
        LinearLayout layout = (LinearLayout) findViewById(R.id.afterlogin_layout);
        Intent intent = new Intent(this.getIntent());
        //Bundle bundle = intent.getExtras();
        String str;

        int whichAct = intent.getIntExtra("EXTRA_INT",100);

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = (ImageView) findViewById(R.id.afterlogin_imageView_barcode);
        //make Random number for Barcode
        Random rd = new Random();
        int barcodeNum = rd.nextInt(MAX - MIN)+MIN;
        if(currentUser.name.equals(""))
            currentUser.name = "유플럿";
        nameView.setText(currentUser.name + " 학생");

        //if it is went through PayActivity
        if(whichAct == UserInfo.FROM_PAY) {
            // barcode creation
            try {
                bitmap = encodeAsBitmap(Integer.toString(barcodeNum), BarcodeFormat.CODE_128, 600, 300);
                iv.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }
            infoView = (TextView)findViewById(R.id.afterlogin_text);
            fromSeatEvent(infoView);
//            layout.addView(infoView);
        }

        //if it is went through LoginActivity
        else if(whichAct == UserInfo.FROM_LOGIN) {
            fromLoginEvent();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     *
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     */

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
    private void fromLoginEvent(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);     // 여기서 this는 Activity의 this 여기서 부터는 알림창의 속성 설정
        builder.setTitle("처음 오셨나요?").setMessage("독서실을 선택해주세요.")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setNeutralButton("선택", new DialogInterface.OnClickListener() {
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(ProfileActivity.this, SearchDemoActivity.class);
                        startActivity(intent);

                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        Dialog d = builder.show();    // 알림창 띄우기
        // Change color of divider
        int dividerId = d.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = d.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.UplusColor));
        // Change Background color of title
        int textViewId = d.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) d.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.UplusColor));
        tv.setTextSize(24);

        //((Button)dialog.findViewById(android.R.id.button3)).setBackgroundResource(R.color.UplusColor_sub);

    }


    private void fromSeatEvent(TextView infoView){

        float dpInPx;

        String str = "      " + currentUser.RRname + "\n" + currentUser.fromDate + " ~ " + currentUser.toDate + "\n      Seat.No " + currentUser.seatNo;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = 20;
        params.leftMargin= 10;
        infoView.setLayoutParams(params);
        infoView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        infoView.setTextColor(Color.BLACK);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // get pixel value from dp
        dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, dm); //250dp
        infoView.setWidth((int)dpInPx);
        infoView.setTextSize(25);
        infoView.setTypeface(null, Typeface.BOLD);
        infoView.setText(str);
    }
}
