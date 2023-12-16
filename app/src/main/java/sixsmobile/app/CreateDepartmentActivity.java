package sixsmobile.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CreateDepartmentActivity extends AppCompatActivity {


    private EditText editTextCreateDepartment;
    private Button create, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_department);

        // Initialize UI elements
        editTextCreateDepartment = findViewById(R.id.editTextCreateDepartment);
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
                String department = editTextCreateDepartment.getText().toString();

                if (!department.isEmpty()) {
                    // Create the database and tables
                    Database database = new Database(CreateDepartmentActivity.this);

                    // Insert the department into the database
                    database.department(department);

                    // Clear the input field
                    editTextCreateDepartment.setText("");

                    // Optionally, you can show a message or perform other actions here
                } else {
                    // Handle the case where the department input is empty
                    // You may want to display an error message to the user
                }
            }
        });
    }
}
