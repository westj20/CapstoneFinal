package sixsmobile.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class set2Fragment extends Fragment {

    private int question6Response = -1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public set2Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static set2Fragment newInstance(String param1, String param2) {
        set2Fragment fragment = new set2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_set2, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("SET1_DATA")) {
            int set1Data = bundle.getInt("SET1_DATA", -1);
            TextView set1data = view.findViewById(R.id.set1);
            set1data.setText("Set 1 Data: " + set1Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            question6Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ6RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ6RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }

    public int getQuestion6Response() {
        return question6Response;
    }
}
