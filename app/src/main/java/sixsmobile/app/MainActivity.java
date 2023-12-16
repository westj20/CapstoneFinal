package sixsmobile.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private int currentPosition = 0;

    private Fragment[] fragments = {
            new topZoneFragment(),
            new topZonesFragment(),
            new bottomZoneFragment(),
            new bottomDepartmentFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        ImageView menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the navigation drawer
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.create_department) {
                    Log.d("MainActivity", "Create Department selected");
                    openCreateDepartmentActivity();
                } else if (itemId == R.id.create_zone) {
                    Log.d("MainActivity", "Create Zone selected");
                    openCreateZoneActivity();
                } else if (itemId == R.id.audit) {
                    Log.d("MainActivity", "Audit selected");
                    openAuditActivity();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        loadFragment(fragments[currentPosition]);

        // Set click listeners for the navigation buttons
        Button backButton = findViewById(R.id.back_button);
        Button nextButton = findViewById(R.id.next_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(-1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(1);
            }
        });
    }
    private void navigate(int direction) {
        // Update the current position based on the direction
        currentPosition = (currentPosition + direction + fragments.length) % fragments.length;

        // Load the new fragment
        loadFragment(fragments[currentPosition]);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    private void openCreateDepartmentActivity() {
        Log.d("MainActivity", "openCreateDepartmentActivity() called");
        Intent intent = new Intent(this, CreateDepartmentActivity.class);
        startActivity(intent);
    }

    private void openCreateZoneActivity() {
        Log.d("MainActivity", "openCreateZoneActivity() called");
        Intent intent = new Intent(this, CreateZoneActivity.class);
        startActivity(intent);
    }

    private void openAuditActivity() {
        Log.d("MainActivity", "openAuditActivity() called");
        Intent intent = new Intent(this, AuditActivity.class);
        startActivity(intent);
    }
}
