package lv.groupOne.linkedinhelper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    boolean debug = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(debug){
            startActivity(new Intent(this, PrimaryActivity.class));
            setContentView(R.layout.activity_primary);
        }else{
            startActivity(new Intent(this, AuthActivity.class));
            setContentView(R.layout.activity_auth);
        }
    }
}