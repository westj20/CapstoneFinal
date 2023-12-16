package sixsmobile.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Sort2Fragment extends Fragment {

    private int question2Response = -1;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sort2Fragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static Sort2Fragment newInstance(String param1, String param2) {
        Sort2Fragment fragment = new Sort2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_sort2, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("SORT1_DATA")) {
            int sort1Data = bundle.getInt("SORT1_DATA", -1);
            TextView textView8 = view.findViewById(R.id.textView8);
            textView8.setText("Sort 1 Data: " + sort1Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Update the question1Response variable based on the selected radio button
            question2Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ2RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ2RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }
    public int getQuestion2Response() {
        return question2Response;
    }
}

