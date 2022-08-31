package ch.ost.rj.mge.u04.mailer.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ch.ost.rj.mge.u04.mailer.R;

public class LoginInfoFragment extends Fragment {
    private final static String WEBSITE_URL = "http://www.ost.ch";

    public static LoginInfoFragment create() {
        return new LoginInfoFragment();
    }

    public LoginInfoFragment() {
        super(R.layout.fragment_login_info);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView urlTextView = view.findViewById(R.id.login_text_url);
        urlTextView.setOnClickListener(v -> openUrl());
    }

    private void openUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WEBSITE_URL));
        startActivity(intent);
    }
}