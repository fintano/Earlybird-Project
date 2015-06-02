package kr.co.lguplus.last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends Activity {

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

        //nameView.setText(currentUser.name + " ÇÐ»ý");
        //nameView.setText(currentUser.\

        //);
        if(whichAct == UserInfo.FROM_PAY) {
            str = currentUser.RRname + "\n" + currentUser.fromDate + " ~ " + currentUser.toDate + "\n Seat.No" + currentUser.seatNo;
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
}
