package payso.com.payso.controller.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import payso.com.payso.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseAccountFrag extends Fragment {

    private static final String TAG = ChooseAccountFrag.class.getCanonicalName();

    // The fragment argument representing the verified phone number entered by the user
    public static final String PHONE_NUMBER = "";

    public static ChooseAccountFrag newInstance() {
        return new ChooseAccountFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable menu_choose_language items
        // setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolBar = view.findViewById(R.id.toolbar);
        ((SignUpActivity) getActivity()).setSupportActionBar(toolBar);

        // Show the Up button in the action bar.
        ActionBar actionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "The UP arrow back action is called...");
                // TODO: navigate back to the previous fragment page
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
