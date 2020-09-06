package ch.ost.rj.mge.u03.mailer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.ost.rj.mge.u03.mailer.R;
import ch.ost.rj.mge.u03.mailer.model.Email;
import ch.ost.rj.mge.u03.mailer.model.EmailRepository;
import ch.ost.rj.mge.u03.mailer.services.EmailVerificationService;
import ch.ost.rj.mge.u03.mailer.services.InputVerificationService;

public class ComposeActivity extends AppCompatActivity {
    private static final String FROM_EMAIL_KEY = "from";
    private final static float FULL_VISIBLE_ALPHA = 1.0f;
    private final static float HALF_VISIBLE_ALPHA = 0.5f;

    private EditText fromEditText;
    private EditText toEditText;
    private EditText subjectEditText;
    private EditText contentEditText;
    private Button sendButton;

    public static Intent createIntent(Context context, String fromEmail) {
        Intent intent = new Intent(context, ComposeActivity.class);
        intent.putExtra(FROM_EMAIL_KEY, fromEmail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        fromEditText = findViewById(R.id.compose_edittext_from);
        fromEditText.setText(getIntent().getStringExtra(FROM_EMAIL_KEY));

        toEditText = findViewById(R.id.compose_edittext_to);
        toEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String email = toEditText.getText().toString();
                boolean isValidEmail = EmailVerificationService.isValidEmail(email);

                if (!isValidEmail) {
                    toEditText.setError(getString(R.string.shared_invalid_email));
                }  else {
                    toEditText.setError(null);
                }

                updateSendButton();
            }
        });

        subjectEditText = findViewById(R.id.compose_edittext_subject);

        contentEditText = findViewById(R.id.compose_edittext_content);

        sendButton = findViewById(R.id.compose_button_send);
        sendButton.setOnClickListener(v -> sendEmail());

        updateSendButton();
    }

    private void updateSendButton() {
        boolean toHasError = InputVerificationService.hasInvalidInput(toEditText);
        boolean buttonIsEnabled = !toHasError;
        float buttonAlpha = buttonIsEnabled ? FULL_VISIBLE_ALPHA : HALF_VISIBLE_ALPHA;

        sendButton.setEnabled(buttonIsEnabled);
        sendButton.setAlpha(buttonAlpha);
    }

    private void sendEmail() {
        Email email = new Email(
                fromEditText.getText().toString(),
                toEditText.getText().toString(),
                subjectEditText.getText().toString(),
                contentEditText.getText().toString());

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email.getTo() });
        intent.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
        intent.putExtra(Intent.EXTRA_TEXT, email.getContent());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            EmailRepository.addEmail(email);
            finish();
        } else {
            Toast.makeText(this, R.string.compose_no_email_app_found, Toast.LENGTH_LONG).show();
        }
    }
}