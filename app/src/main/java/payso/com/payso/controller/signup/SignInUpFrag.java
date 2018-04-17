package payso.com.payso.controller.signup;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import payso.com.payso.R;
import payso.com.payso.controller.HomeMainActivity;


/**
 * This is the welcome page for new users to login into existing account or sign up for a new
 * account.
 *
 * @author mabdira
 */
public class SignInUpFrag extends Fragment {

    private static final String TAG = SignInUpFrag.class.getSimpleName();

    // Initialize the UI Widgets
    private VideoView mVideoView;
    private TextView mLanguageTV;
    private Button mLoginButton;
    private Button mSignUpButton;

    private OnFragmentInteractionListener mListener;

    public static SignInUpFrag newInstance() {
        // Required empty public constructor
        return new SignInUpFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the primary_gradient_background for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Initialize the views
        mVideoView = view.findViewById(R.id.videoView);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() +
                "/" + R.raw.online_shopping));
        // set replay on
        //Video Loop
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start(); //need to make transition seamless.
            }
        });
        //mVideoView.start();

        mLanguageTV = (TextView) view.findViewById(R.id.languageTV);
        mLanguageTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // upload the choose language child fragment
                Log.d(TAG, "Choose a language textview is pressed...");
                if (mListener != null) {
                    mListener.onChooseLanguageTVPressed();
                }
            }
        });

        mLoginButton = (Button) view.findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Upload the login fragment
                Log.d(TAG, "Login button is pressed...");
                onButtonPressed(SignUpActivity.LOGIN);
            }
        });

        mSignUpButton = (Button) view.findViewById(R.id.signupButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Upload the phone number fragment
                Log.d(TAG, "Sign Up button is pressed...");
                onButtonPressed(SignUpActivity.SIGN_UP);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mVideoView.stopPlayback();
        mVideoView = null;
    }

    /**
     * Button is pressed
     * @param buttonName Login or Sign up
     */
    public void onButtonPressed(String buttonName) {
        if (mListener != null) {
            mListener.onButtonPressed(buttonName);
        }
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
        void onButtonPressed(String buttonName);

        void onChooseLanguageTVPressed();
    }
}
