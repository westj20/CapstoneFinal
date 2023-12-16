package sixsmobile.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import sixsmobile.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class StartedAuditActivity extends AppCompatActivity {

    private int currentFragmentIndex = 0;
    private Button submit;
    private Sort1Fragment sort1Fragment;
    private Sort2Fragment sort2Fragment;
    private Sort3Fragment sort3Fragment;
    private Sort4Fragment sort4Fragment;

    private Set1Fragment set1Fragment;

    private set2Fragment set2Fragment;

    private set3Fragment set3Fragment;

    private set4Fragment set4Fragment;
    private Sustain1Fragment sustain1Fragment;

    private Sustain2Fragment sustain2Fragment;
    private Sustain3Fragment sustain3Fragment;
    private Sustain4Fragment sustain4Fragment;
    private Standardize1Fragment standardize1Fragment;
    private Standardize2Fragment standardize2Fragment;
    private Standardize3Fragment standardize3Fragment;
    private Standardize4Fragment standardize4Fragment;

    private Shine1Fragment shine1Fragment;
    private Shine2Fragment shine2Fragment;
    private Shine3Fragment shine3Fragment;
    private Shine4Fragment shine4Fragment;

    private Safety1Fragment safety1Fragment;
    private Safety2Fragment safety2Fragment;
    private Safety3Fragment safety3Fragment;
    private Safety4Fragment safety4Fragment;

    private final List<String> fragmentDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_audit);

        submit = findViewById(R.id.submitBtn);


        // Initialize the currentFragmentIndex based on the saved instance state
        if (savedInstanceState != null) {
            currentFragmentIndex = savedInstanceState.getInt("CURRENT_FRAGMENT_INDEX", 0);
        }

        // Get the audit ID from the intent
        long auditId = getIntent().getLongExtra("AUDIT_ID", -1);
        Log.d("StartedAuditActivity", "Audit ID: " + auditId);

        if (savedInstanceState == null) {
            loadFragment(new Sort1Fragment());
        }

        Database database = new Database(this);
        if (database != null) {
            // Retrieve audit data from the database
            try {
                String[] auditData = database.getAuditDataById((int) auditId);

                // Check if data is available and update TextViews
                if (auditData != null && auditData.length == 3) {
                    // Assuming auditData is an array with three elements: department, zone, date
                    String department = auditData[0];
                    String zone = auditData[1];
                    String date = auditData[2];

                    // Update the TextViews with retrieved data
                    TextView departmentText = findViewById(R.id.departmenttext);
                    TextView zoneText = findViewById(R.id.zonetext);
                    TextView dateText = findViewById(R.id.datetext);

                    departmentText.setText(department);
                    zoneText.setText(zone);
                    dateText.setText(date);

                } else {
                    Log.e("StartedAuditActivity", "Invalid audit data");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("StartedAuditActivity", "Error retrieving audit data: " + e.getMessage());
            }
        } else {
            Log.e("StartedAuditActivity", "Database is null");
        }


        findViewById(R.id.button2).setOnClickListener(view -> {
            if (isRadioButtonChecked()) {
                // If a radio button is checked, proceed to the next fragment
                switch (currentFragmentIndex) {
                    case 1:
                        int sort1Data = ((Sort1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion1Response();
                        if (sort1Data != -1) {
                            fragmentDataList.add(String.valueOf(sort1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SORT1_DATA", sort1Data);


                            Sort2Fragment sort2Fragment = new Sort2Fragment();
                            sort2Fragment.setArguments(bundle);

                            loadFragment(sort2Fragment);
                        } else {
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        int sort2Data = ((Sort2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion2Response();

                        if (sort2Data != -1) {
                            fragmentDataList.add(String.valueOf(sort2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SORT2_DATA", sort2Data);

                            Sort3Fragment sort3Fragment = new Sort3Fragment();
                            sort3Fragment.setArguments(bundle);

                            loadFragment(sort3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        int sort3Data = ((Sort3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion3Response();

                        if (sort3Data != -1) {
                            fragmentDataList.add(String.valueOf(sort3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SORT3_DATA", sort3Data);

                            Sort4Fragment sort4Fragment = new Sort4Fragment();
                            sort4Fragment.setArguments(bundle);

                            loadFragment(sort4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        int sort4Data = ((Sort4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion4Response();

                        if (sort4Data != -1) {
                            fragmentDataList.add(String.valueOf(sort4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SORT4_DATA", sort4Data);

                            Set1Fragment set1Fragment = new Set1Fragment();
                            set1Fragment.setArguments(bundle);

                            loadFragment(set1Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        int set1Data = ((Set1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion5Response();

                        if (set1Data != -1) {
                            fragmentDataList.add(String.valueOf(set1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SET1_DATA", set1Data);

                            set2Fragment set2Fragment = new set2Fragment();
                            set2Fragment.setArguments(bundle);

                            loadFragment(set2Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 6:
                        int set2Data = ((set2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion6Response();

                        if (set2Data != -1) {
                            fragmentDataList.add(String.valueOf(set2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SET2_DATA", set2Data);

                            set3Fragment set3Fragment = new set3Fragment();
                            set3Fragment.setArguments(bundle);

                            loadFragment(set3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        int set3Data = ((set3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion7Response();

                        if (set3Data != -1) {
                            fragmentDataList.add(String.valueOf(set3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SET3_DATA", set3Data);

                            set4Fragment set4Fragment = new set4Fragment();
                            set4Fragment.setArguments(bundle);

                            loadFragment(set4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 8:
                        int set4Data = ((set4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion8Response();

                        if (set4Data != -1) {
                            fragmentDataList.add(String.valueOf(set4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SET4_DATA", set4Data);

                            Sustain1Fragment sustain1Fragment = new Sustain1Fragment();
                            sustain1Fragment.setArguments(bundle);

                            loadFragment(sustain1Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 9:
                        int sustain1Data = ((Sustain1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion9Response();

                        if (sustain1Data != -1) {
                            fragmentDataList.add(String.valueOf(sustain1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SUSTAIN1_DATA", sustain1Data);

                            Sustain2Fragment sustain2Fragment = new Sustain2Fragment();
                            sustain2Fragment.setArguments(bundle);

                            loadFragment(sustain2Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 10:
                        int sustain2Data = ((Sustain2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion10Response();

                        if (sustain2Data != -1) {
                            fragmentDataList.add(String.valueOf(sustain2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SUSTAIN2_DATA", sustain2Data);

                            Sustain3Fragment sustain3Fragment = new Sustain3Fragment();
                            sustain3Fragment.setArguments(bundle);

                            loadFragment(sustain3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 11:
                        int sustain3Data = ((Sustain3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion11Response();

                        if (sustain3Data != -1) {
                            fragmentDataList.add(String.valueOf(sustain3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SUSTAIN3_DATA", sustain3Data);

                            Sustain4Fragment sustain4Fragment = new Sustain4Fragment();
                            sustain4Fragment.setArguments(bundle);

                            loadFragment(sustain4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 12:
                        int sustain4Data = ((Sustain4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion12Response();

                        if (sustain4Data != -1) {
                            fragmentDataList.add(String.valueOf(sustain4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SUSTAIN4_DATA", sustain4Data);

                            Standardize1Fragment standardize1Fragment = new Standardize1Fragment();
                            standardize1Fragment.setArguments(bundle);

                            loadFragment(standardize1Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 13:
                        int standardize1Data = ((Standardize1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion13Response();

                        if (standardize1Data != -1) {
                            fragmentDataList.add(String.valueOf(standardize1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("STANDARDIZE1_DATA", standardize1Data);

                            Standardize2Fragment standardize2Fragment = new Standardize2Fragment();
                            standardize2Fragment.setArguments(bundle);

                            loadFragment(standardize2Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 14:
                        int standardize2Data = ((Standardize2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion14Response();

                        if (standardize2Data != -1) {
                            fragmentDataList.add(String.valueOf(standardize2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("STANDARDIZE2_DATA", standardize2Data);

                            Standardize3Fragment standardize3Fragment = new Standardize3Fragment();
                            standardize3Fragment.setArguments(bundle);

                            loadFragment(standardize3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 15:
                        int standardize3Data = ((Standardize3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion15Response();

                        if (standardize3Data != -1) {
                            fragmentDataList.add(String.valueOf(standardize3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("STANDARDIZE3_DATA", standardize3Data);

                            Standardize4Fragment standardize4Fragment = new Standardize4Fragment();
                            standardize4Fragment.setArguments(bundle);

                            loadFragment(standardize4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 16:
                        int standardize4Data = ((Standardize4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion16Response();

                        if (standardize4Data != -1) {
                            fragmentDataList.add(String.valueOf(standardize4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("STANDARDIZE4_DATA", standardize4Data);

                            Shine1Fragment shine1Fragment = new Shine1Fragment();
                            shine1Fragment.setArguments(bundle);

                            loadFragment(shine1Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 17:
                        int shine1Data = ((Shine1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion17Response();

                        if (shine1Data != -1) {
                            fragmentDataList.add(String.valueOf(shine1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SHINE1_DATA", shine1Data);

                            Shine2Fragment shine2Fragment = new Shine2Fragment();
                            shine2Fragment.setArguments(bundle);

                            loadFragment(shine2Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 18:
                        int shine2Data = ((Shine2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion18Response();

                        if (shine2Data != -1) {
                            fragmentDataList.add(String.valueOf(shine2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SHINE2_DATA", shine2Data);

                            Shine3Fragment shine3Fragment = new Shine3Fragment();
                            shine3Fragment.setArguments(bundle);

                            loadFragment(shine3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 19:
                        int shine3Data = ((Shine3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion19Response();

                        if (shine3Data != -1) {
                            fragmentDataList.add(String.valueOf(shine3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SHINE3_DATA", shine3Data);

                            Shine4Fragment shine4Fragment = new Shine4Fragment();
                            shine4Fragment.setArguments(bundle);

                            loadFragment(shine4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 20:
                        int shine4Data = ((Shine4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion20Response();

                        if (shine4Data != -1) {
                            fragmentDataList.add(String.valueOf(shine4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SHINE4_DATA", shine4Data);

                            Safety1Fragment safety1Fragment = new Safety1Fragment();
                            safety1Fragment.setArguments(bundle);

                            loadFragment(safety1Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 21:
                        int safety1Data = ((Safety1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion21Response();

                        if (safety1Data != -1) {
                            fragmentDataList.add(String.valueOf(safety1Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SAFETY1_DATA", safety1Data);

                            Safety2Fragment safety2Fragment = new Safety2Fragment();
                            safety2Fragment.setArguments(bundle);

                            loadFragment(safety2Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 22:
                        int safety2Data = ((Safety2Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion22Response();

                        if (safety2Data != -1) {
                            fragmentDataList.add(String.valueOf(safety2Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SAFETY2_DATA", safety2Data);

                            Safety3Fragment safety3Fragment = new Safety3Fragment();
                            safety3Fragment.setArguments(bundle);

                            loadFragment(safety3Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 23:
                        int safety3Data = ((Safety3Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion23Response();

                        if (safety3Data != -1) {
                            fragmentDataList.add(String.valueOf(safety3Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SAFETY3_DATA", safety3Data);

                            Safety4Fragment safety4Fragment = new Safety4Fragment();
                            safety4Fragment.setArguments(bundle);

                            loadFragment(safety4Fragment);
                        } else {
                            // Handle the case when no radio button is selected
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 24:
                        int safety4Data = ((Safety4Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer)).getQuestion24Response();

                        if (safety4Data != -1) {
                            fragmentDataList.add(String.valueOf(safety4Data));

                            Bundle bundle = new Bundle();
                            bundle.putInt("SAFETY4_DATA", safety4Data);

                            SubmitFragment submitFragment = new SubmitFragment();
                            submitFragment.setArguments(bundle);

                            if (submitFragment != null && submitFragment.getArguments() != null) {
                                Log.d("StartedAuditActivity", "Current Fragment Index: " + currentFragmentIndex);
                                Log.d("StartedAuditActivity", "Is SubmitFragment Visible: " + submitFragment.isVisible());

                                findViewById(R.id.button2).setVisibility(View.GONE);

                                loadFragment(submitFragment);

                            } else {
                                Toast.makeText(StartedAuditActivity.this, "Fragment initialization error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(StartedAuditActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                }
            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick();
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        Log.d("StartedAuditActivity", "Loading Fragment: " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
        if (fragment instanceof Sort1Fragment) {
            sort1Fragment = (Sort1Fragment) fragment;
        } else if (fragment instanceof Sort2Fragment) {
            sort2Fragment = (Sort2Fragment) fragment;
        }else if (fragment instanceof Sort3Fragment) {
            sort3Fragment = (Sort3Fragment) fragment;
        }else if (fragment instanceof Sort4Fragment) {
            sort4Fragment = (Sort4Fragment) fragment;
        }

        else if (fragment instanceof Set1Fragment) {
            set1Fragment = (Set1Fragment) fragment;
        }
        else if (fragment instanceof set2Fragment) {
            set2Fragment = (set2Fragment) fragment;
        }
        else if (fragment instanceof set3Fragment) {
            set3Fragment = (set3Fragment) fragment;
        }
        else if (fragment instanceof set4Fragment) {
            set4Fragment = (set4Fragment) fragment;
        }

        else if (fragment instanceof Sustain1Fragment) {
            sustain1Fragment = (Sustain1Fragment) fragment;
        }
        else if (fragment instanceof Sustain2Fragment) {
            sustain2Fragment = (Sustain2Fragment) fragment;
        }
        else if (fragment instanceof Sustain3Fragment) {
            sustain3Fragment = (Sustain3Fragment) fragment;
        }
        else if (fragment instanceof Sustain4Fragment) {
            sustain4Fragment = (Sustain4Fragment) fragment;
        }

        else if (fragment instanceof Standardize1Fragment) {
            standardize1Fragment = (Standardize1Fragment) fragment;
        }  else if (fragment instanceof Standardize2Fragment) {
            standardize2Fragment = (Standardize2Fragment) fragment;
        }  else if (fragment instanceof Standardize3Fragment) {
            standardize3Fragment = (Standardize3Fragment) fragment;
        }  else if (fragment instanceof Standardize4Fragment) {
            standardize4Fragment = (Standardize4Fragment) fragment;
        }
        else if (fragment instanceof Shine1Fragment) {
            shine1Fragment = (Shine1Fragment) fragment;
        }  else if (fragment instanceof Shine2Fragment) {
            shine2Fragment = (Shine2Fragment) fragment;
        }  else if (fragment instanceof Shine3Fragment) {
            shine3Fragment = (Shine3Fragment) fragment;
        }  else if (fragment instanceof Shine4Fragment) {
            shine4Fragment = (Shine4Fragment) fragment;
        }
        else if (fragment instanceof  Safety1Fragment) {
            safety1Fragment = (Safety1Fragment) fragment;
        } else if (fragment instanceof  Safety2Fragment) {
            safety2Fragment = (Safety2Fragment) fragment;
        } else if (fragment instanceof  Safety3Fragment) {
            safety3Fragment = (Safety3Fragment) fragment;
        } else if (fragment instanceof  Safety4Fragment) {
            safety4Fragment = (Safety4Fragment) fragment;
        }
        currentFragmentIndex++;
    }


    private boolean isRadioButtonChecked() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return selectedRadioButtonId != View.NO_ID;
    }
    public void handleButtonClick() {

        try {
            // Retrieve data from TextViews
            TextView departmentText = findViewById(R.id.departmenttext);
            TextView zoneText = findViewById(R.id.zonetext);
            TextView dateText = findViewById(R.id.datetext);

            String department = departmentText.getText().toString();
            String zone = zoneText.getText().toString();
            String date = dateText.getText().toString();

            int sort1 = sort1Fragment.getQuestion1Response();
            int sort2 = sort2Fragment.getQuestion2Response();
            int sort3 = sort3Fragment.getQuestion3Response();
            int sort4 = sort4Fragment.getQuestion4Response();
            int set1 = set1Fragment.getQuestion5Response();
            int set2 = set2Fragment.getQuestion6Response();
            int set3 = set3Fragment.getQuestion7Response();
            int set4 = set4Fragment.getQuestion8Response();
            int sustain1 = sustain1Fragment.getQuestion9Response();
            int sustain2 = sustain2Fragment.getQuestion10Response();
            int sustain3 = sustain3Fragment.getQuestion11Response();
            int sustain4 = sustain4Fragment.getQuestion12Response();
            int standardize1 = standardize1Fragment.getQuestion13Response();
            int standardize2 = standardize2Fragment.getQuestion14Response();
            int standardize3 = standardize3Fragment.getQuestion15Response();
            int standardize4 = standardize4Fragment.getQuestion16Response();
            int shine1 = shine1Fragment.getQuestion17Response();
            int shine2 = shine2Fragment.getQuestion18Response();
            int shine3 = shine3Fragment.getQuestion19Response();
            int shine4 = shine4Fragment.getQuestion20Response();
            int safety1 = safety1Fragment.getQuestion21Response();
            int safety2 = safety2Fragment.getQuestion22Response();
            int safety3 = safety3Fragment.getQuestion23Response();
            int safety4 = safety4Fragment.getQuestion24Response();

            // Now you can use the data for your database operation
            Database database = new Database(StartedAuditActivity.this);
            database.insertAuditResults(date, department, zone, sort1, sort2, sort3, sort4, set1, set2, set3, set4, sustain1, sustain2, sustain3, sustain4, standardize1, standardize2, standardize3, standardize4, shine1, shine2, shine3, shine4, safety1, safety2, safety3, safety4);
            Intent intent = new Intent(StartedAuditActivity.this, MainActivity.class);
            startActivity(intent);

            // Show a toast message indicating that data has been saved
            Toast.makeText(StartedAuditActivity.this, "Data Saved to Database", Toast.LENGTH_SHORT).show();

            clearFragments();
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, perhaps show an error message to the user
            Toast.makeText(StartedAuditActivity.this, "Error performing database operation", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
