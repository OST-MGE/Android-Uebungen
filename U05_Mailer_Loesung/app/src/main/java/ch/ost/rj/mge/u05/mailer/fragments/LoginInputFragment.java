package ch.ost.rj.mge.u05.mailer.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ch.ost.rj.mge.u05.mailer.R;
import ch.ost.rj.mge.u05.mailer.services.EmailVerificationService;
import ch.ost.rj.mge.u05.mailer.services.InputVerificationService;
import ch.ost.rj.mge.u05.mailer.services.VibrationService;

public class LoginInputFragment extends Fragment {
    private final static int MINIMUM_PASSWORD_LENGTH = 10;

    private final String initialEmail;
    private final boolean initialKeepData;

    private LoginInputFragmentCallback callback;
    private EditText emailEditText;
    private EditText passwordEditText;
    private SwitchCompat keepDataSwitch;

    public LoginInputFragment(String initialEmail, boolean initialKeepData) {
        this.initialEmail = initialEmail;
        this.initialKeepData = initialKeepData;
    }

    public static LoginInputFragment create(String initialEmail, boolean initialKeepData) {
        return new LoginInputFragment(initialEmail, initialKeepData);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (LoginInputFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Owner activity must implement LoginFragmentCallback.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_login_input, container, false);

        emailEditText = fragment.findViewById(R.id.login_edittext_email);
        emailEditText.setText(initialEmail);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String email = emailEditText.getText().toString();
                boolean isValidEmail = EmailVerificationService.isValidEmail(email);

                if (!isValidEmail) {
                    emailEditText.setError(getString(R.string.shared_invalid_email));
                }  else {
                    emailEditText.setError(null);
                }

                verifyInputs();
            }
        });

        passwordEditText = fragment.findViewById(R.id.login_edittext_password);
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < MINIMUM_PASSWORD_LENGTH) {
                    passwordEditText.setError(getString(R.string.login_invalid_password_length));
                } else {
                    passwordEditText.setError(null);
                }

                verifyInputs();
            }
        });

        keepDataSwitch = fragment.findViewById(R.id.login_switch_keep);
        keepDataSwitch.setChecked(initialKeepData);
        keepDataSwitch.setOnClickListener(v -> {
            VibrationService.vibrateInfo();
            verifyInputs();
        });

        verifyInputs();

        return fragment;
    }

    private void verifyInputs() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean emailHasError = InputVerificationService.hasInvalidInput(emailEditText);
        boolean passwordHasError = InputVerificationService.hasInvalidInput(passwordEditText);
        boolean keepData = keepDataSwitch.isChecked();
        boolean inputsAreValid = !emailHasError && !passwordHasError;

        callback.onInputChanged(email, password, keepData, inputsAreValid);
    }
}
