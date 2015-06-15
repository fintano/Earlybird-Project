package kr.co.lguplus.last;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;


public class DreamActivity extends Activity {

    Intent intent;
    ImageView myFirstView;
    ImageView mySecondView;
    Animation FadeIn, FadeOut,FadeInShort;

    ImageView demoImage;
    int imagesToShow[] = {R.drawable.logo_test, R.drawable.plus};

    private void animate(final ImageView imageView, final int images[], final int imageIndex, final boolean forever) {

        //imageView <-- The View which displays the images
        //images[] <-- Holds R references to the images to display
        //imageIndex <-- index of the first image to show in images[]
        //forever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned

        //imageView.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
        imageView.setImageResource(images[0]);
        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(FadeIn);
        //animation.setRepeatCount(1);
        imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
             /*   if (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1, forever); //Calls itself until it gets to the end of the array
                } else {
                    if (forever == true) {
                        animate(imageView, images, 0, forever);  //Calls itself to start the animation all over again in a loop if forever = true
                    }
                }*/
                        /*Rotate 'plus*/
                ImageView plusView = (ImageView) findViewById(R.id.activity_plus);
                final AnimationSet rollingIn = new AnimationSet(true);
                Animation moving = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 5, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                moving.setDuration(5000);
                final Animation rotating = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotating.setDuration(5000);
                rollingIn.addAnimation(FadeInShort);
                rollingIn.addAnimation(rotating);
                rollingIn.addAnimation(moving);
                rollingIn.setFillAfter(true);
                rollingIn.setDuration(4000);
        /*Fade in 'study'*/
                plusView.setImageResource(images[1]);
                plusView.setAnimation(rollingIn);
                rollingIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //animation.setStartOffset(1500);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                if(imageIndex == 0 )
                    animate(imageView,images,imageIndex+1,forever);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream1);

        //myFirstView = (ImageView) findViewById(R.id.activity_studypluslogo2);
        //mySecondView = (ImageView) findViewById(R.id.activity_studypluslogo1);

        FadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadein);
        FadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeout);
        FadeInShort =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        demoImage = (ImageView) findViewById(R.id.activity_studypluslogo1);

        animate(demoImage, imagesToShow, 0, false);

//        FadeIn.setAnimationListener(this);
//        FadeOut.setAnimationListener(this);

//        mySecondView.setVisibility(View.VISIBLE);
//        mySecondView.startAnimation(FadeIn);
//        myFirstView.startAnimation(FadeOut);

        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                //��Ƽ��Ƽ ����

                intent = new Intent(DreamActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //3�� �ε� ���� ����
        handler.sendEmptyMessageDelayed(0, 9000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dream, menu);
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
