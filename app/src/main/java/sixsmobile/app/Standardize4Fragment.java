package sixsmobile.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Standardize4Fragment extends Fragment {

    private int question16Response = -1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Standardize4Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Standardize4Fragment newInstance(String param1, String param2) {
        Standardize4Fragment fragment = new Standardize4Fragment();
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
        View view = inflater.inflate(R.layout.fragment_standardize4, container, false);


        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("STANDARDIZE3_DATA")) {
            int standardize3Data = bundle.getInt("STANDARDIZE3_DATA", -1);
            TextView standardize3data = view.findViewById(R.id.standardize3);
            standardize3data.setText("Standardize 3 Data: " + standardize3Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Update the question1Response variable based on the selected radio button
            question16Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ16RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ16RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }


    public int getQuestion16Response() {
        return question16Response;
    }
}