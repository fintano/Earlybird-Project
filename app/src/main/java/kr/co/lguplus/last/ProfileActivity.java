package kr.co.lguplus.last;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode);

        currentUser = UserInfo.getUserInfo();
        ImageButton menubtn = (ImageButton) findViewById(R.id.barcode_button_menu);
        TextView nameView = (TextView) findViewById(R.id.barcode_edit_name);
        TextView infoView;
        Button selectButton;
        LinearLayout layout = (LinearLayout) findViewById(R.id.barcode_mainlayout);
        Intent intent = new Intent(this.getIntent());
        //Bundle bundle = intent.getExtras();
        String str;

        int whichAct = intent.getIntExtra("EXTRA_INT",100);

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = (ImageView) findViewById(R.id.profile_barcode);
        //make Random number for Barcode
        Random rd = new Random();
        int barcodeNum = rd.nextInt(MAX - MIN)+MIN;
        if(currentUser.name.equals(""))
            currentUser.name = "유불럭";
        nameView.setText("   " + currentUser.name + " 학생");

        //if it is went through PayActivity
        if(whichAct == UserInfo.FROM_PAY) {
            // barcode creation
            try {
                bitmap = encodeAsBitmap(Integer.toString(barcodeNum), BarcodeFormat.CODE_128, 600, 300);
                iv.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }
            infoView = new TextView(this);
            fromSeatEvent(infoView);
            layout.addView(infoView);
        }
        //if it is went through LoginActivity
        else if(whichAct == UserInfo.FROM_LOGIN){
            selectButton = new Button(this);
            // set button's property
            fromLoginEvent(selectButton);
            layout.addView(selectButton);
        }

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapactivity = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(mapactivity);
                finish();
            }
        });
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
    private void fromLoginEvent(Button selectButton){

        float dpInPx;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = 20;
        selectButton.setLayoutParams(params);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // get pixel value from dp
        dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, dm); //300dp
        selectButton.setWidth((int)dpInPx);
        dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, dm);  //50dp
        selectButton.setHeight((int)dpInPx);
        selectButton.setText("독서실을 선택해주세요");
        selectButton.setBackgroundColor(Color.parseColor("#ec068d"));
        selectButton.setTextColor(Color.WHITE);
        selectButton.setTextSize(25);
        selectButton.setTypeface(null, Typeface.BOLD);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(ProfileActivity.this, SearchDemoActivity.class);
                startActivity(regactivity);
                finish();
            }
        });

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
