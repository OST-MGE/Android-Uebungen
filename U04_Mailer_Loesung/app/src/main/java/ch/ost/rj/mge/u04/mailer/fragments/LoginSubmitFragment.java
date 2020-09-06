package ch.ost.rj.mge.u04.mailer.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ost.rj.mge.u04.mailer.R;

public class LoginSubmitFragment extends Fragment {
    private final static float FULL_VISIBLE_ALPHA = 1.0f;
    private final static float HALF_VISIBLE_ALPHA = 0.5f;

    private LoginSubmitFragmentCallback callback;
    private View loginButton;

    public static LoginSubmitFragment create() {
        return new LoginSubmitFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_login_submit, container, false);

        loginButton = fragment.findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(v -> {
            updateStatus(false);
            callback.onSubmitClicked();
        });

        return fragment;
    }

    public void updateStatus(boolean inputsAreValid) {
        float buttonAlpha = inputsAreValid ? FULL_VISIBLE_ALPHA : HALF_VISIBLE_ALPHA;

        loginButton.setEnabled(inputsAreValid);
        loginButton.setAlpha(buttonAlpha);
    }
}
