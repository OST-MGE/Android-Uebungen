package ch.ost.rj.mge.u03.mailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {
    private static final String EMAIL_KEY = "email";
    private EditText fromEditText;
    private EditText toEditText;
    private EditText subjectEditText;
    private EditText contentEditText;
    private Button sendButton;

    public static Intent createIntent(Context context, String email) {
        Intent intent = new Intent(context, ComposeActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        fromEditText = findViewById(R.id.compose_edittext_from);
        fromEditText.setText(getIntent().getStringExtra(EMAIL_KEY));

        toEditText = findViewById(R.id.compose_edittext_to);
        toEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String email = toEditText.getText().toString();
                boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (!isValidEmail) {
                    toEditText.setError("Ungültige Adresse");
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
        boolean toHasError = toEditText.getText().length() == 0|| toEditText.getError() != null;
        boolean buttonIsEnabled = !toHasError;
        float buttonAlpha = buttonIsEnabled ? 1.0f : 0.5f;

        sendButton.setEnabled(buttonIsEnabled);
        sendButton.setAlpha(buttonAlpha);
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { toEditText.getText().toString() });
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, contentEditText.getText().toString());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Kein Mail-Programm vorhanden", Toast.LENGTH_LONG).show();
        }
    }
}