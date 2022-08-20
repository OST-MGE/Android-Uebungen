package ch.ost.rj.mge.u04.mailer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ch.ost.rj.mge.u04.mailer.R;
import ch.ost.rj.mge.u04.mailer.services.EmailVerificationService;
import ch.ost.rj.mge.u04.mailer.services.InputVerificationService;

public class LoginInputFragment extends Fragment {
    private final static int MINIMUM_PASSWORD_LENGTH = 10;

    private LoginInputFragmentCallback callback;
    private EditText emailEditText;
    private EditText passwordEditText;

    public static LoginInputFragment create() {
        return new LoginInputFragment();
    }

    public LoginInputFragment() {
        super(R.layout.fragment_login_input);
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        emailEditText = view.findViewById(R.id.login_edittext_email);
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

        passwordEditText = view.findViewById(R.id.login_edittext_password);
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

        verifyInputs();
    }

    private void verifyInputs() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean emailHasError = InputVerificationService.hasInvalidInput(emailEditText);
        boolean passwordHasError = InputVerificationService.hasInvalidInput(passwordEditText);
        boolean inputsAreValid = !emailHasError && !passwordHasError;

        callback.onInputChanged(email, password, inputsAreValid);
    }
}
