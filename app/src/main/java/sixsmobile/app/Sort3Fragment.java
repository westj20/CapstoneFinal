package sixsmobile.app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Sort3Fragment extends Fragment {

    private int question3Response = -1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sort3Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Sort3Fragment newInstance(String param1, String param2) {
        Sort3Fragment fragment = new Sort3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_sort3, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("SORT2_DATA")) {
            int sort2Data = bundle.getInt("SORT2_DATA", -1);
            TextView textView9 = view.findViewById(R.id.textView9);
            textView9.setText("Sort 2 Data: " + sort2Data);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            question3Response = getResponseFromRadioButton(checkedId);
        });

        return view;
    }

    private int getResponseFromRadioButton(int checkedId) {
        if (checkedId == R.id.yesQ3RadioButton) {
            return 1; // Yes
        } else if (checkedId == R.id.noQ3RadioButton) {
            return 0; // No
        } else {
            return -1; // None selected
        }
    }


    public int getQuestion3Response() {
        return question3Response;
    }
}
