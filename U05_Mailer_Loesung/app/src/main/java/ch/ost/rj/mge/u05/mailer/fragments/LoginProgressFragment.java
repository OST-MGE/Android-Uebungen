package ch.ost.rj.mge.u05.mailer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.ost.rj.mge.u05.mailer.R;

public class LoginProgressFragment extends Fragment {

    public static LoginProgressFragment create() {
        return new LoginProgressFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_progress, container, false);
    }
}