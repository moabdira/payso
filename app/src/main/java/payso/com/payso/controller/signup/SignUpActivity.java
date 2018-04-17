package payso.com.payso.controller.signup;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import payso.com.payso.R;
import payso.com.payso.controller.HomeMainActivity;
import payso.com.payso.model.LanguagesEnum;
import payso.com.payso.model.PhoneCarrierEnum;

/**
 * This is the Sign Up Activity that hosts all fragments related to signing up the user such as
 * the welcoming page, sign up, sign in, etc
 *
 * @author mabdira
 */

public class SignUpActivity extends AppCompatActivity
        implements SignInUpFrag.OnFragmentInteractionListener,
        ChooseLanguageFrag.OnFragmentInteractionListener,
        PhoneNumberFrag.OnFragmentInteractionListener,
        OTPVerifyFrag.OnFragmentInteractionListener {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    // Fragments that the home activity hosts
    private SignInUpFrag mSignInUpFrag;
    private ChooseLanguageFrag mChooseLangFrag;
    private PhoneNumberFrag mPhoneNumberFrag;

    public static final String SIGN_UP = "Sign Up";
    public static final String LOGIN = "Log In";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Check that the activity is using the primary_gradient_background version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_signup_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                Log.d(TAG, "Restored from a previous state");

                return;
            }

            // Ask the user to login or sign up
            addSignInUpFrag();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSignInUpFrag = null;
        mPhoneNumberFrag = null;
        mChooseLangFrag = null;
    }

    /**
     * Loads or adds the phone number fragment into this activity. This method also adds the
     * fragment into the backstack.
     */
    private void addPhoneNumberFrag() {
        Log.d(TAG, "Loading the enter phone number fragment into the activity...");
        mPhoneNumberFrag = PhoneNumberFrag.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_signup_container, mPhoneNumberFrag);
        transaction.addToBackStack("PhoneNumberFrag");
        transaction.commit();
    }

    /**
     * Loads or adds the sign in and sign up fragment into this activity. It also adds it into the
     * back stack.
     */
    private void addSignInUpFrag() {
        Log.d(TAG, "Loading the welcome fragment into the activity...");
        // Create a new Fragment to be placed in the activity primary_gradient_background
        mSignInUpFrag = SignInUpFrag.newInstance();

        // Add the fragment to the 'fragment_container' FrameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_signup_container, mSignInUpFrag);
        transaction.addToBackStack("SignInUpFragment");
        transaction.commit();
    }

    /**
     * Loads or adds the choose a language child fragment on top of this fragment.
     */
    private void loadChooseLanguageFrag() {
        Log.d(TAG, "Loading the choose language fragment...");
        mChooseLangFrag = ChooseLanguageFrag.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.add(R.id.fragment_signup_container, mChooseLangFrag);
        transaction.addToBackStack("ChooseLanguageFragment");
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onButtonPressed(String button) {
        if (LOGIN == button) {
            // The login button is pressed
            Log.d(TAG, "Launching or deploying the login fragment...");
            // TODO: Launch the login fragment
        } else {
            // The signup button is pressed
            Log.d(TAG, "Launching or deploying the phone number fragment as sign up button " +
                    "is pressed...");
            addPhoneNumberFrag();
        }
    }

    @Override
    public void onChooseLanguageTVPressed() {
        // Load the choose language fragment as the "language" Textview is pressed
        loadChooseLanguageFrag();
    }

    @Override
    public void onLanguageSelected(LanguagesEnum language) {
        // TODO: Translate the app based on language chosen by the user
        Log.d(TAG, String.format("The following language was selected by the user: %s", language));
        ChooseLanguageFrag frag = (ChooseLanguageFrag) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_signup_container);
        if (frag != null) {
            Log.d(TAG, "Found the choose language fragment from the back stack " +
                    "and removing it...");
            getSupportFragmentManager().beginTransaction().remove(frag).commit();
        }
    }


    @Override
    public void onPhoneNumberAdded(PhoneCarrierEnum phoneCarrier, PhoneNumberUtils phoneNumber) {
        // TODO: Persist the phone number and it's carrier information before continue to next page
        // First check in case the up arrow back action button is pressed, if yes, simply return
        // to the welcome page.
        if(phoneCarrier == null || phoneNumber == null) {
            // Simply dismiss the phone number fragment from the back stack and return back to
            // the parent fragment or activity
            PhoneNumberFrag frag = (PhoneNumberFrag) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_signup_container);
            if (frag != null) {
                Log.d(TAG, "Found the phone number fragment object from the back stack " +
                        "and removing it...");
                getSupportFragmentManager().beginTransaction().remove(frag).commit();
            }
        }
    }

    @Override
    public void onVerifyPhoneNumber(boolean verified, int activationCode) {
        if (!verified && activationCode == -1) {
            // The back up arrow button is pressed
            addPhoneNumberFrag();
        } else if(verified && activationCode != -1) {
            // TODO: phone number is correctly verified. Call the next fragment to create the account
        } else {
            // The back up arrow button is pressed but the activation code is incorrect
            addPhoneNumberFrag();
        }
    }
}
