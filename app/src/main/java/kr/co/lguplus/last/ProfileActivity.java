package kr.co.lguplus.last;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ReadingRoomStruct currentUser = UserInfo.getUserInfo();
        Button menubtn = (Button) findViewById(R.id.profile_button);
        TextView nameView = (TextView) findViewById(R.id.profile_name);
        TextView infoView = (TextView) findViewById(R.id.profile_roominfo);

        Intent intent = new Intent(this.getIntent());
        //Bundle bundle = intent.getExtras();
        String str;
        int whichAct = intent.getIntExtra("EXTRA_INT",1);

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = (ImageView) findViewById(R.id.profile_barcode);
        //make Random number for Barcode
        Random rd = new Random();
        int barcodeNum = rd.nextInt(MAX - MIN)+MIN;
        nameView.setText(currentUser.name + " 학생");

        //if it is went through PayActivity
        if(whichAct == UserInfo.FROM_PAY) {
            str = currentUser.RRname + "\n" + currentUser.fromDate + " ~ " + currentUser.toDate + "\n Seat.No " + currentUser.seatNo;
            // barcode creation
            try {
                bitmap = encodeAsBitmap(Integer.toString(barcodeNum), BarcodeFormat.CODE_128, 600, 300);
                iv.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }

            infoView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            infoView.setText(str);
        }

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapactivity = new Intent(ProfileActivity.this, SearchDemoActivity.class);
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
}
