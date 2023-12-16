package sixsmobile.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Set1Fragment extends Fragment {

    private int question5Response = -1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Set1Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Set1Fragment newInstance(String param1, String param2) {
        Set1Fragment fragment = new Set1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set1, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("SORT4_DATA")) {
            int sort4Data = bundle.getInt("SORT4_DATA", -1);
            TextView sort4data = view.findViewById(R.id.sort4);
            sort4data.setText("Sort 4 Data: " + sort4Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            question5Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ5RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ5RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }

    public int getQuestion5Response() {
        return question5Response;
    }
}