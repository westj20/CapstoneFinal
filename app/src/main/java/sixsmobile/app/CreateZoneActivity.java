package sixsmobile.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateZoneActivity extends AppCompatActivity {

    private EditText editTextCreateZone;
    private Button create, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone);

        // Initialize UI elements
        editTextCreateZone = findViewById(R.id.editTextCreateZone);
        create = findViewById(R.id.create);
        back = findViewById(R.id.btnBack);


        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from the input field
                String zone = editTextCreateZone.getText().toString();

                if (!zone.isEmpty()) {
                    // Create the database and tables
                    Database database = new Database(CreateZoneActivity.this);

                    // Insert the zone into the database
                    database.insertZone(zone);

                    // Clear the input field
                    editTextCreateZone.setText("");

                    // Optionally, you can show a message or perform other actions here
                } else {
                    // Handle the case where the zone input is empty
                    // You may want to display an error message to the user
                }
            }
        });
    }
}

