package ch.ost.rj.mge.u04.mailer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import ch.ost.rj.mge.u04.mailer.R;
import ch.ost.rj.mge.u04.mailer.fragments.LoginInfoFragment;
import ch.ost.rj.mge.u04.mailer.fragments.LoginInputFragment;
import ch.ost.rj.mge.u04.mailer.fragments.LoginInputFragmentCallback;
import ch.ost.rj.mge.u04.mailer.fragments.LoginProgressFragment;
import ch.ost.rj.mge.u04.mailer.fragments.LoginSubmitFragment;
import ch.ost.rj.mge.u04.mailer.fragments.LoginSubmitFragmentCallback;

public class LoginActivity extends AppCompatActivity implements LoginInputFragmentCallback, LoginSubmitFragmentCallback {

    private LoginInfoFragment loginInfoFragment;
    private LoginInputFragment loginInputFragment;
    private LoginSubmitFragment loginSubmitFragment;
    private LoginProgressFragment loginProgressFragment;

    private String currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginInfoFragment = LoginInfoFragment.create();
        loginInputFragment = LoginInputFragment.create();
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
    public void onInputChanged(String email, String password, boolean inputsAreValid) {
        currentEmail = email;
        loginSubmitFragment.updateButtonAvailability(inputsAreValid);
    }

    @Override
    public void onSubmitClicked() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_input_container, loginProgressFragment)
                .commit();

        Runnable navigateToOutbox = () -> {
            Intent intent = OutboxActivity.createIntent(this, currentEmail);
            startActivity(intent);
            finish();
        };

        Looper looper = Looper.getMainLooper();
        Handler handler = new Handler(looper);
        handler.postDelayed(navigateToOutbox, 3000);
    }
}