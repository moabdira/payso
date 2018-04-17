package payso.com.payso.controller.signup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import payso.com.payso.R;
import payso.com.payso.model.CarrierItemData;
import payso.com.payso.model.PhoneCarrierEnum;
import payso.com.payso.util.ui.PhoneCarrierSpinnerAdapter;

/**
 * This fragment class asks the user to enter their carrier and mobile phone number on this phone.
 *
 * @author mabdira
 */
public class PhoneNumberFrag extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = ChooseLanguageFrag.class.getSimpleName();

    private Toolbar mToolBar;
    private ActionBar mActionBar;

    private PhoneNumberFrag.OnFragmentInteractionListener mListener;

    // Layout views
    private Spinner mCarrierSpinner;
    private EditText mPhoneEditText;
    private Button mContinueButton;

    public static PhoneNumberFrag newInstance() {
        // Required empty public constructor
        return new PhoneNumberFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable menu_choose_language items
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the primary_gradient_background for this fragment
        return inflater.inflate(R.layout.fragment_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolBar = view.findViewById(R.id.toolbar);

        if (mToolBar != null) {
            // Enable the Up Button and initialize the action bar
            showBackButton();
        }

        // Initialize the required views
        mPhoneEditText = view.findViewById(R.id.phoneEditText);
        mContinueButton = view.findViewById(R.id.continueButton);

        // Initialize the phone carrier spinner
        mCarrierSpinner = view.findViewById(R.id.carrierSpinner);
        if(mCarrierSpinner != null) {
            mCarrierSpinner.setOnItemSelectedListener(this);

            // Initialize the list of the region carrier item data
            ArrayList<CarrierItemData> list = new ArrayList<CarrierItemData>(7);
            list.add(new CarrierItemData(getContext().getString(R.string.select_from_list), 0));
            list.add(new CarrierItemData(PhoneCarrierEnum.Telesom.name(), 252));
            list.add(new CarrierItemData(PhoneCarrierEnum.Golis.name(), 252));
            list.add(new CarrierItemData(PhoneCarrierEnum.Hormuud.name(), 252));
            list.add(new CarrierItemData(PhoneCarrierEnum.Somtel.name(), 252));
            list.add(new CarrierItemData(PhoneCarrierEnum.Verizon.name(), 1));

            // Create the phone carrier spinner adapter object
            PhoneCarrierSpinnerAdapter adapter = new PhoneCarrierSpinnerAdapter(getContext(),
                    R.layout.phone_carrier_spinner_item, R.id.carrierNameTextview, list);
            mCarrierSpinner.setAdapter(adapter);
        } else
            Log.e(TAG, "Carrier spinner object when entering the phone number is null...");

    }

    @Override
    /**
     * Keeps track of what phone carrier was selected
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        // TODO: get the phone carrier name that was selected
    }

    @Override
    /**
     * Called when nothing selected
     */
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        // TODO: Display a diaglog box or highlight the spinner in Red when no phone carrier is selected
    }

    private void showBackButton() {
        if (getActivity() != null) {
            ((SignUpActivity) getActivity()).setSupportActionBar(mToolBar);
            mActionBar = ((SignUpActivity) getActivity()).getSupportActionBar();
        } else
            Log.e(TAG, "No SignUp Activity exists...");

        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true); 
            mActionBar.setDisplayShowTitleEnabled(false);
        } else
            Log.e(TAG, "ActionBar is set to NULL...");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PhoneNumberFrag.OnFragmentInteractionListener) {
            mListener = (PhoneNumberFrag.OnFragmentInteractionListener) context;
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
                mListener.onPhoneNumberAdded(null, null);
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
        void onPhoneNumberAdded(PhoneCarrierEnum phoneCarrier, PhoneNumberUtils phoneNumber);
    }
}
