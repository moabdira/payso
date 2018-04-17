package payso.com.payso.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * A class to get device local cache data
 *
 * Created by mabdira on 11/12/17.
 */

public class LocalCache {

    private static LocalCache mLocalCache;

    private LocalCache() {}

    /**
     * Get an instance of the LocalCache data
     *
     * @return LocalCache
     */
    public static LocalCache getInstance(Context context) {
        if (mLocalCache == null) {
            synchronized (LocalCache.class) {
                if (mLocalCache == null) {
                    mLocalCache = new LocalCache();
                }
            }
        }

        return mLocalCache;
    }

    /**
     * Get the Settings SharePreferences
     *
     * @return Settings - the settings of the app
     */
    public SharedPreferences getSettingsSharedPreferences(Context context) {
        return context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }


}
