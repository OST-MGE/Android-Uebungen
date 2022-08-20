package ch.ost.rj.mge.u04.mailer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ch.ost.rj.mge.u04.mailer.R;

public class LoginSubmitFragment extends Fragment {
    private final static float FULL_VISIBLE_ALPHA = 1.0f;
    private final static float HALF_VISIBLE_ALPHA = 0.5f;

    private LoginSubmitFragmentCallback callback;
    private View loginButton;

    public static LoginSubmitFragment create() {
        return new LoginSubmitFragment();
    }

    public LoginSubmitFragment() {
        super(R.layout.fragment_login_submit);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (LoginSubmitFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Owner activity must implement LoginSubmitFragmentCallback.");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        loginButton = view.findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(v -> {
            updateButtonAvailability(false);
            callback.onSubmitClicked();
        });
    }

    public void updateButtonAvailability(boolean inputsAreValid) {
        float buttonAlpha = inputsAreValid ? FULL_VISIBLE_ALPHA : HALF_VISIBLE_ALPHA;

        loginButton.setEnabled(inputsAreValid);
        loginButton.setAlpha(buttonAlpha);
    }
}
