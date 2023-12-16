package sixsmobile.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottomDepartmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottomDepartmentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bottomDepartmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bottomDepartmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static bottomDepartmentFragment newInstance(String param1, String param2) {
        bottomDepartmentFragment fragment = new bottomDepartmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_department, container, false);

        // Get a reference to the LinearLayout where you want to display the top departments
        LinearLayout bottomDepartmentsLayout = view.findViewById(R.id.bottomDepartmentChartContainer);

        // Get an instance of the database
        Database database = new Database(getContext());
        SQLiteDatabase db = database.getReadableDatabase();

        // Get the cursor with data for the current month
        Cursor cursor = database.getDataForCurrentMonth();

        if (cursor != null) {
            int departmentIndex = cursor.getColumnIndex("department");
            int totalScoreIndex = cursor.getColumnIndex("totalScore");

            // Map to store department-wise total scores
            Map<String, Integer> departmentScores = new HashMap<>();

            if (cursor.moveToFirst() && departmentIndex != -1 && totalScoreIndex != -1) {
                // Iterate through the cursor and populate the map
                do {
                    String department = cursor.getString(departmentIndex);
                    int totalScore = cursor.getInt(totalScoreIndex);
                    departmentScores.put(department, totalScore);
                } while (cursor.moveToNext());

                // Sort departments based on total scores
                List<Map.Entry<String, Integer>> sortedDepartments = new ArrayList<>(departmentScores.entrySet());
                Collections.sort(sortedDepartments, Map.Entry.comparingByValue());



                int count = 0;
                int totalDepartments = sortedDepartments.size();

                for (int i = 0; i < totalDepartments && count < 5; i++) {
                    Map.Entry<String, Integer> entry = sortedDepartments.get(i);

                    String department = entry.getKey();
                    int totalScore = entry.getValue();

                    // Create a TextView for each department
                    TextView departmentTextView = new TextView(getContext());
                    departmentTextView.setText(department + ": " + totalScore);

                    // Add the TextView to the layout
                    bottomDepartmentsLayout.addView(departmentTextView);

                    count++;
                }

            } else {
                Log.e("bottomDepartmentFragment", "Error: Cursor or column indices are invalid");
            }

            cursor.close();
            db.close();
        }

        return view;
    }
}