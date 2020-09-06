package ch.ost.rj.mge.u03.mailer.services;

import android.widget.EditText;

public class InputVerificationService {

    public static boolean hasInvalidInput(EditText editText) {
        return editText.getText().length() == 0 || editText.getError() != null;
    }

}
