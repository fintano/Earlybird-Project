package kr.co.lguplus.last;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SearchDetailActivity extends Activity {

    Item item;

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed(); //지워야 실행됨
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail);
        Button seat = (Button) findViewById(R.id.login_detail_seat_btn);
        item = (Item) getIntent().getExtras().getSerializable("search");
        updateInfo(item);
        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set ReadingRoomName of currentUser
                UserInfo.getUserInfo().setName(item.title);
                // Go to SeatInfoavtivity
                Intent mapactivity = new Intent(SearchDetailActivity.this, SeatActivity.class);
                startActivity(mapactivity);

            }
        });
    }

    /* update 독서실 information to Display */

    protected void updateInfo(Item item){

        String url;
        Bitmap image;
        TextView nameText = (TextView) findViewById(R.id.login_detail_name);
        ImageView imageView = (ImageView) findViewById(R.id.login_detail_image);
        TextView adrsText = (TextView) findViewById(R.id.login_detail_adrs2);
        TextView phoneNumText =(TextView) findViewById(R.id.login_detail_phoneNum2);

        nameText.setText(item.title);

        /* if image of 독서실 doesn't exist, update another image such as "noimage" file
         */
        //if(item.imageUrl == null)
        //   image = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
        //else
        //System.out.println(item.imageUrl);
        phoneNumText.setText(item.phone);

        new DownloadImageTask(imageView)
                .execute(item.imageUrl);

        //imageView.setImageBitmap(image);
        /*
        Bitmap resized = Bitmap.createScaledBitmap(image, 450, 200, true);
        imageView.setImageBitmap(resized);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 레이아웃 크기에 이미지를 맞춘다
        imageView.setPadding(3, 3, 3, 3);
*/
/*
        if(item.imageUrl == null)
            imageView.setImageResource(R.drawable.noimage);
        else
            loadImageFromURL(item.imageUrl, imageView);
*/
        adrsText.setText(item.address);
        //phoneNumText.setText(item.phone);

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    public Bitmap loadImageFromURL(String fileUrl){
        try {
            URL myFileUrl = new URL (fileUrl);
            HttpURLConnection conn =
                    (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            return BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}