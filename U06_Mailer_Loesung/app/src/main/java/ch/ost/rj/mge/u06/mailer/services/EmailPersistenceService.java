package ch.ost.rj.mge.u06.mailer.services;

import android.content.Context;
import android.content.SharedPreferences;

public class EmailPersistenceService {
    private static final String FILE_NAME = "ch.ost.rj.mge.u06.mailer.preferences";
    private static final String EMAIL_KEY = "email";
    private static final String EMAIL_EMPTY = "none";
    private static SharedPreferences preferences;

    public static void initialize(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean hasStoredEmail() {
        String email = getStoredEmail();
        return !email.equals(EMAIL_EMPTY);
    }

    public static String getStoredEmail() {
        return preferences.getString(EMAIL_KEY, EMAIL_EMPTY);
    }

    public static void updateStoredEmail(String email) {
        preferences.edit().putString(EMAIL_KEY, email).apply();
    }

    public static void clearStoredEmail() {
        preferences.edit().remove(EMAIL_KEY).apply();
    }
}
