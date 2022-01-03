package lv.groupOne.linkedinhelper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PrimaryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    FirebaseAuth fAuth;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container,
                        new SearchFragment()
                ).commit();
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container,
                        new FavoritesFragment()
                ).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container,
                        new ProfileFragment()
                ).commit();
                break;
            case R.id.nav_log_out:
                fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container,
                    new SearchFragment()
            ).commit();

            navigationView.setCheckedItem(R.id.nav_search);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}