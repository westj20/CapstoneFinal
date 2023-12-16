package sixsmobile.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AuditActivity extends AppCompatActivity {

    private Spinner departmentSpinner, zoneSpinner;
    private Database database;
    private Button back, startAudit;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);

        // Initialize calendar
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Handle the selected date
            String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        // Initialize database
        database = new Database(getApplicationContext());

        // Initialize Spinners
        departmentSpinner = findViewById(R.id.spinner3);
        zoneSpinner = findViewById(R.id.spinner4);

        // Initialize Buttons
        back = findViewById(R.id.back);
        startAudit = findViewById(R.id.start);

        back.setOnClickListener(view -> onBackPressed());

        startAudit.setOnClickListener(view -> {
            try {
                String selectedDepartment = departmentSpinner.getSelectedItem().toString();
                String selectedZone = zoneSpinner.getSelectedItem().toString();

                // Get the selected date from CalendarView
                long selectedDateMillis = calendarView.getDate();
                String selectedDate = convertMillisToDate(selectedDateMillis);

                Log.d("AuditActivity", "Selected Department: " + selectedDepartment);
                Log.d("AuditActivity", "Selected Zone: " + selectedZone);
                Log.d("AuditActivity", "Selected Date: " + selectedDate);

                // Save the data to the database and get the newly created audit ID
                long auditId = saveAuditData(selectedDepartment, selectedZone, selectedDate);

                if (auditId != -1) {
                    // Data saved successfully, proceed with the next steps
                    Intent intent = new Intent(AuditActivity.this, StartedAuditActivity.class);
                    intent.putExtra("AUDIT_ID", auditId);
                    startActivity(intent);
                    finish();
                } else {
                    // Log an error or display a message indicating an issue with data insertion
                    Log.e("AuditActivity", "Error saving audit data. Audit ID: " + auditId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("AuditActivity", "Error saving audit data: " + e.getMessage());
            }
        });

        // Retrieve data from the database and set it to the Spinners
        loadSpinnerData();
    }

    // Modify the saveAuditData method to return the audit ID
    private long saveAuditData(String department, String zone, String date) {
        return database.insertAuditData(department, zone, date);
    }

    private void loadSpinnerData() {
        // Load department data
        List<String> departmentList = database.getAllDepartments();
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentList);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);

        // Load zone data
        List<String> zoneList = database.getAllZones();
        ArrayAdapter<String> zoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zoneList);
        zoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zoneSpinner.setAdapter(zoneAdapter);
    }

    private String convertMillisToDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date(millis));
    }
}
