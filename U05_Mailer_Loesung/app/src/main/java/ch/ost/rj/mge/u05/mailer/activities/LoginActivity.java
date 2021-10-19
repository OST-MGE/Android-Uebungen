package ch.ost.rj.mge.u05.mailer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import ch.ost.rj.mge.u05.mailer.R;
import ch.ost.rj.mge.u05.mailer.fragments.LoginInfoFragment;
import ch.ost.rj.mge.u05.mailer.fragments.LoginInputFragment;
import ch.ost.rj.mge.u05.mailer.fragments.LoginInputFragmentCallback;
import ch.ost.rj.mge.u05.mailer.fragments.LoginProgressFragment;
import ch.ost.rj.mge.u05.mailer.fragments.LoginSubmitFragment;
import ch.ost.rj.mge.u05.mailer.fragments.LoginSubmitFragmentCallback;
import ch.ost.rj.mge.u05.mailer.services.EmailPersistenceService;

public class LoginActivity extends AppCompatActivity implements LoginInputFragmentCallback, LoginSubmitFragmentCallback {

    private LoginInfoFragment loginInfoFragment;
    private LoginInputFragment loginInputFragment;
    private LoginSubmitFragment loginSubmitFragment;
    private LoginProgressFragment loginProgressFragment;

    private String currentEmail;
    private boolean currentKeepData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        String initialEmail = getInitialEmail();
        boolean initialKeepDataState = getInitialKeepDataState();

        loginInfoFragment = LoginInfoFragment.create();
        loginInputFragment = LoginInputFragment.create(initialEmail, initialKeepDataState);
        loginSubmitFragment = LoginSubmitFragment.create();
        loginProgressFragment = LoginProgressFragment.create();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.login_info_container, loginInfoFragment)
                .add(R.id.login_submit_container, loginSubmitFragment)
                .add(R.id.login_input_container, loginInputFragment)
                .commit();
    }

    @Override
    public void onInputChanged(String email, String password, boolean keepData, boolean inputsAreValid) {
        currentEmail = email;
        currentKeepData = keepData;
        loginSubmitFragment.updateButtonAvailability(inputsAreValid);
    }

    @Override
    public void onSubmitClicked() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_input_container, loginProgressFragment)
                .commit();

        Runnable storeEmailAndNavigateToOutbox = () -> {
            persistEmail();
            Intent intent = OutboxActivity.createIntent(this, currentEmail);
            startActivity(intent);
        };

        Looper looper = Looper.getMainLooper();
        Handler handler = new Handler(looper);
        handler.postDelayed(storeEmailAndNavigateToOutbox, 3000);
    }

    private String getInitialEmail() {
        return EmailPersistenceService.hasStoredEmail() ? EmailPersistenceService.getStoredEmail() : "";
    }

    private boolean getInitialKeepDataState() {
        return EmailPersistenceService.hasStoredEmail();
    }

    private void persistEmail() {
        if (currentKeepData) {
            EmailPersistenceService.updateStoredEmail(currentEmail);
        } else {
            EmailPersistenceService.clearStoredEmail();
        }
    }
}