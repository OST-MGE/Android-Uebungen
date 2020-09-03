package ch.ost.rj.mge.u03.mailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView emailTextView;
    private TextView passwordTextView;
    private View loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView urlTextView = findViewById(R.id.login_text_url);
        urlTextView.setOnClickListener(v -> openUrl("http://www.theapp.com"));

        emailTextView = findViewById(R.id.login_edittext_email);
        emailTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String email = emailTextView.getText().toString();
                boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (!isValidEmail) {
                    emailTextView.setError("Ungültige Adresse");
                }  else {
                    emailTextView.setError(null);
                }

                updateLoginButton();
            }
        });

        passwordTextView = findViewById(R.id.login_edittext_password);
        passwordTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 10) {
                    passwordTextView.setError("Passwort zu kurz");
                } else {
                    passwordTextView.setError(null);
                }

                updateLoginButton();
            }
        });

        loginButton = findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(v -> showComposeScreen());

        updateLoginButton();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void updateLoginButton() {
        boolean emailHasError = emailTextView.getText().length() == 0|| emailTextView.getError() != null;
        boolean passwordHasError = emailTextView.getText().length() == 0 || passwordTextView.getError() != null;
        boolean buttonIsEnabled = !emailHasError && !passwordHasError;
        float buttonAlpha = buttonIsEnabled ? 1.0f : 0.5f;

        loginButton.setEnabled(buttonIsEnabled);
        loginButton.setAlpha(buttonAlpha);
    }

    private void showComposeScreen() {
        String email = emailTextView.getText().toString();
        Intent intent = ComposeActivity.createIntent(this, email);
        startActivity(intent);
    }
}