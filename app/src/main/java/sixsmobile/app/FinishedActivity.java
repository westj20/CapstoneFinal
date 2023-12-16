package sixsmobile.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class FinishedActivity extends AppCompatActivity {

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        submit = findViewById(R.id.submit);
        MyApplication myApp = (MyApplication) getApplication();
        AuditData auditData = myApp.getAuditData();

        TextView textView6 = findViewById(R.id.textView6);
        TextView textView7 = findViewById(R.id.textView7);

        textView6.setText(String.valueOf(auditData.getQuestion1Response()));
        textView7.setText(String.valueOf(auditData.getQuestion2Response()));

        submit.setOnClickListener(view -> {
            // Get data from AuditData
            int question1Response = auditData.getQuestion1Response();
            int question2Response = auditData.getQuestion2Response();
            int question3Response = auditData.getQuestion3Response();
            int question4Response = auditData.getQuestion4Response();
            int question5Response = auditData.getQuestion5Response();
            int question6Response = auditData.getQuestion6Response();
            int question7Response = auditData.getQuestion7Response();
            int question8Response = auditData.getQuestion8Response();
            int question9Response = auditData.getQuestion9Response();
            int question10Response = auditData.getQuestion10Response();
            int question11Response = auditData.getQuestion11Response();
            int question12Response = auditData.getQuestion12Response();
            int question13Response = auditData.getQuestion13Response();
            int question14Response = auditData.getQuestion14Response();
            int question15Response = auditData.getQuestion15Response();
            int question16Response = auditData.getQuestion16Response();
            int question17Response = auditData.getQuestion17Response();
            int question18Response = auditData.getQuestion18Response();
            int question19Response = auditData.getQuestion19Response();
            int question20Response = auditData.getQuestion20Response();
            int question21Response = auditData.getQuestion21Response();
            int question22Response = auditData.getQuestion22Response();
            int question23Response = auditData.getQuestion23Response();
            int question24Response = auditData.getQuestion24Response();

            Database dbHelper = new Database(FinishedActivity.this);


            dbHelper.close();
        });
    }
}