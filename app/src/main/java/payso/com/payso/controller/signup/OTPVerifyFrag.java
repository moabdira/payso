package payso.com.payso.controller.signup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import payso.com.payso.R;

/**
 * This fragment class verifies the users mobile phone number. It uses a six digit activation code
 * sent over as a SMS text message.
 *
 * @author mabdira
 */
public class OTPVerifyFrag extends Fragment {

    private static final String TAG = OTPVerifyFrag.class.getCanonicalName();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "phone number";

    private Toolbar mToolBar;
    private ActionBar mActionBar;

    // TODO: Rename and change types of parameters
    private String mPhoneNumber;

    // Views to act on
    private TextView mSubheadAppBarTV;
    private EditText mActivationEditText;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param phoneNumber
     * @return A new instance of fragment OTPVerifyFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static OTPVerifyFrag newInstance(String phoneNumber) {
        OTPVerifyFrag fragment = new OTPVerifyFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoneNumber = getArguments().getString(ARG_PARAM1);
        }

        // Enable menu_choose_language items
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otpverify, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolBar = view.findViewById(R.id.toolbar);
        if(mToolBar != null) {
            // Enable the Up Button and initialize the action bar
            showBackButton();
        }


        // initialize the views
        mActivationEditText.setText(String.format(mActivationEditText.getTextLocale()
                                                    + "%s", mPhoneNumber));
        mActivationEditText = view.findViewById(R.id.activation_number_EditText);
    }

    /**
     * Shows the back button in the tool bar
     */
    private void showBackButton() {
        if (getActivity() != null) {
            ((SignUpActivity) getActivity()).setSupportActionBar(mToolBar);
            mActionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        } else
            Log.e(TAG, "No SignUpActivity exists...");

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(false);
        } else
            Log.e(TAG, "ActionBar is set to NULL...");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.menu_choose_language, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "The UP arrow back action is called...");
                mListener.onVerifyPhoneNumber(false, -1);
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onVerifyPhoneNumber(boolean verified, int activationCode);
    }
}
