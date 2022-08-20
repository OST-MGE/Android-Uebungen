package ch.ost.rj.mge.u04.mailer.fragments;

import androidx.fragment.app.Fragment;

import ch.ost.rj.mge.u04.mailer.R;

public class LoginProgressFragment extends Fragment {

    public static LoginProgressFragment create() {
        return new LoginProgressFragment();
    }

    public LoginProgressFragment() {
        super(R.layout.fragment_login_progress);
    }
}