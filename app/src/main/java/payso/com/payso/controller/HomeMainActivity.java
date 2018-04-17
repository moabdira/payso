package payso.com.payso.controller;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import payso.com.payso.R;
import payso.com.payso.controller.signup.ChooseLanguageFrag;
import payso.com.payso.controller.signup.PhoneNumberFrag;
import payso.com.payso.controller.signup.SignInUpFrag;
import payso.com.payso.controller.signup.SignUpActivity;
import payso.com.payso.model.LanguagesEnum;
import payso.com.payso.model.PhoneCarrierEnum;

/**
 * This is the main Home Main Activity of the App. Fragments are deployed on top of this Main activity
 * and include fragments such as the welcome page, sign up, sign in, and the main home dashboard/
 *
 * @author mabdira
 */

public class HomeMainActivity extends AppCompatActivity {

    private static final String TAG = HomeMainActivity.class.getSimpleName();

    public static boolean isLoggedIn = false;

    // Keep track of activity for results between the various activities called
    public static final int PICK_SIGNUP_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        // Check that the activity is using the primary_gradient_background version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_dashboard_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                Log.d(TAG, "Restored from a previous state");

                return;
            }

            if (!isLoggedIn) {
                // Call Sign in and up activity
                Log.d(TAG, "Loading up the sign up activity...");
                startActivityForResult(new Intent(this, SignUpActivity.class),
                                                    PICK_SIGNUP_REQUEST);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_SIGNUP_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The SignUpActivity has returned and where the user has successfully registered
                // or logged in
                // TODO: Load the dashboard fragment
            }
        }
    }
}
