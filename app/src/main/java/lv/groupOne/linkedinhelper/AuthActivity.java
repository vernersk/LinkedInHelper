package lv.groupOne.linkedinhelper;

import android.os.Bundle;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container);
        setFragment(new RegisterFragment());
    }
}