package sixsmobile.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Sort4Fragment extends Fragment {

    private int question4Response = -1;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sort4Fragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static Sort4Fragment newInstance(String param1, String param2) {
        Sort4Fragment fragment = new Sort4Fragment();
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
        View view = inflater.inflate(R.layout.fragment_sort4, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("SORT3_DATA")) {
            int sort3Data = bundle.getInt("SORT3_DATA", -1);
            TextView sort3data = view.findViewById(R.id.sort3);
            sort3data.setText("Sort 3 Data: " + sort3Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            question4Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ4RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ4RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }


    public int getQuestion4Response() {
        return question4Response;
    }
}