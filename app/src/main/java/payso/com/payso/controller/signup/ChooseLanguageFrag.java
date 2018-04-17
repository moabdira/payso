package payso.com.payso.controller.signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.Locale;

import payso.com.payso.R;
import payso.com.payso.controller.HomeMainActivity;
import payso.com.payso.model.LanguagesEnum;
import payso.com.payso.service.LocalCache;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseLanguageFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseLanguageFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseLanguageFrag extends Fragment {

    private static final String TAG = ChooseLanguageFrag.class.getSimpleName();

    private Toolbar mToolBar;
    private ActionBar mActionBar;

    private CheckedTextView mEnglishTV;
    private CheckedTextView mSomaliTV;
    private LanguagesEnum mCurrentLanguage;
    private TextView mDoneActionTV;
    private boolean isDoneChecked = false;

    private OnFragmentInteractionListener mListener;

    public static ChooseLanguageFrag newInstance() {
        // Required empty public constructor
        return new ChooseLanguageFrag();
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
        return inflater.inflate(R.layout.fragment_choose_language, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolBar = view.findViewById(R.id.toolbar);

        if (mToolBar != null) {
            // Enable the Up Button and initialize the action bar
            showBackButton();
        }

        mEnglishTV = view.findViewById(R.id.EnglishCheckedTV);
        mSomaliTV = view.findViewById(R.id.SomaliCheckedTV);

        // make sure the current language is selected per the user device Locale setting
        mCurrentLanguage = LanguagesEnum.ENGLISH;
        if (getLocaleCurrentLanguage() == LanguagesEnum.SOMALI) {
            Log.d(TAG, "The local language of the device is to " + LanguagesEnum.SOMALI);
            setDefaultLanguage(LanguagesEnum.SOMALI);
        }

        mEnglishTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentLanguage != LanguagesEnum.ENGLISH) {
                    isDoneChecked = true;
                    setDefaultLanguage(LanguagesEnum.ENGLISH);
                }
            }
        });

        mSomaliTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentLanguage != LanguagesEnum.SOMALI) {
                    isDoneChecked = true;
                    setDefaultLanguage(LanguagesEnum.SOMALI);
                }
            }
        });
    }


    private LanguagesEnum getLocaleCurrentLanguage() {
        Locale locale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            locale = getResources().getConfiguration().getLocales().get(0);
        else
            locale = getResources().getConfiguration().locale;

        String language = locale.getLanguage();
        Log.d(TAG, "The device locale current language is set to " + language);
        if (language == "so")
            return LanguagesEnum.SOMALI;
        else
            return LanguagesEnum.ENGLISH;
    }

    private void setDefaultLanguage(LanguagesEnum language) {
        SharedPreferences.Editor settings = LocalCache.getInstance(getContext())
                .getSettingsSharedPreferences(getContext()).edit();

        if (language == LanguagesEnum.SOMALI) {
            // Set Somali language as the default
            mEnglishTV.setTextColor(getResources().getColor(R.color.textBlackPrimaryColor));
            mEnglishTV.setChecked(false);
            mEnglishTV.setCheckMarkDrawable(null);

            mSomaliTV.setTextColor(getResources().getColor(R.color.colorPrimary));
            mSomaliTV.setChecked(true);
            mSomaliTV.setCheckMarkDrawable(getResources().getDrawable(R.drawable.ic_check_black_24dp));
        } else {
            // Set English as the default language
            mSomaliTV.setTextColor(getResources().getColor(R.color.textBlackPrimaryColor));
            mSomaliTV.setChecked(false);
            mSomaliTV.setCheckMarkDrawable(null);

            mEnglishTV.setTextColor(getResources().getColor(R.color.colorPrimary));
            mEnglishTV.setChecked(true);
            mEnglishTV.setCheckMarkDrawable(getResources().getDrawable(R.drawable.ic_check_black_24dp));
        }

        settings.putString(LanguagesEnum.LANGUAGE.toString(), language.toString());
        settings.apply();
        mCurrentLanguage = language;
        Log.d(TAG, "The current language is set to " + mCurrentLanguage);

        // Turn on the Done Action
        if(isDoneChecked)
            mDoneActionTV.setTextColor(getResources().getColor(R.color.textWhitePrimaryColor));
    }

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
        inflater.inflate(R.menu.menu_choose_language, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem actionViewItem = menu.findItem(R.id.doneAction);

        // Retrieve the action-view from menu
        View view = actionViewItem.getActionView();

        // Get the Textview within action-view
        mDoneActionTV = view.findViewById(R.id.done_TV);
        mDoneActionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Call the fragment that asks the user to enter their phone number
                // TODO: Call the fragment that asks the user to enter their phone number
                if (isDoneChecked) {
                    // It has been clicked
                    Log.d(TAG, "Done action executed where the language is set to "
                            + mCurrentLanguage);
                    mListener.onLanguageSelected(mCurrentLanguage);
                }
            }
        });

        // Handle button click here
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "The UP arrow back action is called...");
                mListener.onLanguageSelected(mCurrentLanguage);
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
        void onLanguageSelected(LanguagesEnum language);
    }
}
