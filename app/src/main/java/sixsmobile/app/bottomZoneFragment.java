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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottomZoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottomZoneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bottomZoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bottomZoneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static bottomZoneFragment newInstance(String param1, String param2) {
        bottomZoneFragment fragment = new bottomZoneFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_zone, container, false);
        LinearLayout topZonesLayout = view.findViewById(R.id.topZoneChartContainer);

        // Get an instance of the database
        Database database = new Database(getContext());
        SQLiteDatabase db = database.getReadableDatabase();

        // Get the cursor with data for the current month
        Cursor cursor = database.getDataForCurrentMonth();

        int departmentIndex = cursor.getColumnIndex("department");
        int zoneIndex = cursor.getColumnIndex("zone");
        int totalScoreIndex = cursor.getColumnIndex("totalScore");

        if (cursor != null && cursor.moveToFirst() && departmentIndex != -1 && zoneIndex != -1 && totalScoreIndex != -1) {
            // List to store all scores
            List<Map.Entry<String, Integer>> allScores = new ArrayList<>();

            // Iterate through the cursor and populate the list
            do {
                String department = cursor.getString(departmentIndex);
                String zone = cursor.getString(zoneIndex);
                int totalScore = cursor.getInt(totalScoreIndex);

                allScores.add(new AbstractMap.SimpleEntry<>(department + " - " + zone, totalScore));

            } while (cursor.moveToNext());

            // Sort all scores in ascending order based on total scores
            Collections.sort(allScores, Comparator.comparingInt(Map.Entry::getValue));


            int count = 0;
            for (Map.Entry<String, Integer> entry : allScores) {
                if (count < 5) {
                    String departmentAndZone = entry.getKey();
                    int totalScore = entry.getValue();

                    // Create a TextView for each score
                    TextView scoreTextView = new TextView(getContext());
                    scoreTextView.setText(departmentAndZone + ": " + totalScore);

                    // Add the TextView to the layout
                    topZonesLayout.addView(scoreTextView);

                    count++;
                } else {
                    break;
                }
            }
        } else {
            Log.e("topZoneFragment", "Error: Cursor or column indices are invalid");
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return view;
    }
}
