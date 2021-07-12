package ch.ost.rj.mge.u03.mailer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ch.ost.rj.mge.u03.mailer.R;
import ch.ost.rj.mge.u03.mailer.services.EmailVerificationService;
import ch.ost.rj.mge.u03.mailer.services.InputVerificationService;

public class LoginActivity extends AppCompatActivity {
    private final static String WEBSITE_URL = "http://www.ost.ch";
    private final static int MINIMUM_PASSWORD_LENGTH = 10;
    private final static float FULL_VISIBLE_ALPHA = 1.0f;
    private final static float HALF_VISIBLE_ALPHA = 0.5f;

    private EditText emailEditText;
    private EditText passwordEditText;
    private View loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView urlTextView = findViewById(R.id.login_text_url);
        urlTextView.setOnClickListener(v -> openUrl());

        emailEditText = findViewById(R.id.login_edittext_email);
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

                updateLoginButton();
            }
        });

        passwordEditText = findViewById(R.id.login_edittext_password);
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

                updateLoginButton();
            }
        });

        loginButton = findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(v -> showOutboxActivity());

        updateLoginButton();
    }

    private void openUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WEBSITE_URL));
        startActivity(intent);
    }

    private void updateLoginButton() {
        boolean emailHasError = InputVerificationService.hasInvalidInput(emailEditText);
        boolean passwordHasError = InputVerificationService.hasInvalidInput(passwordEditText);
        boolean buttonIsEnabled = !emailHasError && !passwordHasError;
        float buttonAlpha = buttonIsEnabled ? FULL_VISIBLE_ALPHA : HALF_VISIBLE_ALPHA;

        loginButton.setEnabled(buttonIsEnabled);
        loginButton.setAlpha(buttonAlpha);
    }

    private void showOutboxActivity() {
        String email = emailEditText.getText().toString();
        Intent intent = OutboxActivity.createIntent(this, email);
        startActivity(intent);
    }
}