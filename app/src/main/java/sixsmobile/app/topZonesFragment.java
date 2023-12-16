package sixsmobile.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class topZonesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public topZonesFragment() {
        // Required empty public constructor
    }

    public static topZonesFragment newInstance(String param1, String param2) {
        topZonesFragment fragment = new topZonesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_zones, container, false);

        // Get a reference to the LinearLayout where you want to display the top departments
        LinearLayout topDepartmentsLayout = view.findViewById(R.id.chartContainer);

        // Get an instance of the database
        Database database = new Database(getContext());
        SQLiteDatabase db = database.getReadableDatabase();

        // Get the cursor with data for the current month
        Cursor cursor = database.getDataForCurrentMonth();

        int departmentIndex = cursor.getColumnIndex("department");
        int totalScoreIndex = cursor.getColumnIndex("totalScore");

        if (cursor != null && cursor.moveToFirst() && departmentIndex != -1 && totalScoreIndex != -1) {
            // Map to store department-wise total scores
            Map<String, Integer> departmentScores = new HashMap<>();

            // Iterate through the cursor and populate the map
            do {
                String department = cursor.getString(departmentIndex);
                int totalScore = cursor.getInt(totalScoreIndex);
                departmentScores.put(department, totalScore);
            } while (cursor.moveToNext());

            // Sort departments based on total scores
            List<Map.Entry<String, Integer>> sortedDepartments = new ArrayList<>(departmentScores.entrySet());
            Collections.sort(sortedDepartments, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Display the top 5 departments
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedDepartments) {
                if (count < 5) {
                    String department = entry.getKey();
                    int totalScore = entry.getValue();

                    // Create a TextView for each department
                    TextView departmentTextView = new TextView(getContext());
                    departmentTextView.setText(department + ": " + totalScore);

                    // Add the TextView to the layout
                    topDepartmentsLayout.addView(departmentTextView);

                    count++;
                } else {
                    break; // Stop when top 5 departments are displayed
                }
            }
        } else {
            Log.e("topZonesFragment", "Error: Cursor or column indices are invalid");
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return view;
    }
}
