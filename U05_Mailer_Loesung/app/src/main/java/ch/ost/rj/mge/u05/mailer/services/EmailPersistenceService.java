package ch.ost.rj.mge.u05.mailer.services;

import android.content.Context;
import android.content.SharedPreferences;

public class EmailPersistenceService {
    private static final String FILE_NAME = "ch.ost.rj.mge.u05.mailer.preferences";
    private static final String EMAIL_KEY = "email";
    private static final String EMAIL_EMPTY = "none";

    public static boolean hasStoredEmail(Context context) {
        String email = getStoredEmail(context);
        return !email.equals(EMAIL_EMPTY);
    }

    public static String getStoredEmail(Context context) {
        SharedPreferences preferences = getPreferences(context);
        String email = preferences.getString(EMAIL_KEY, EMAIL_EMPTY);

        return email;
    }

    public static void updateStoredEmail(Context context, String email) {
        SharedPreferences preferences = getPreferences(context);
        preferences.edit().putString(EMAIL_KEY, email).apply();
    }

    public static void clearStoredEmail(Context context) {
        SharedPreferences preferences = getPreferences(context);
        preferences.edit().remove(EMAIL_KEY).apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }
}
