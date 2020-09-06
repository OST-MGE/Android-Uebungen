package ch.ost.rj.mge.u03.mailer.services;

import android.util.Patterns;

public class EmailVerificationService {

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
