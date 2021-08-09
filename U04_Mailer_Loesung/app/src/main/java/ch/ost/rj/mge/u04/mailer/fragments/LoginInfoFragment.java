package ch.ost.rj.mge.u04.mailer.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.ost.rj.mge.u04.mailer.R;

public class LoginInfoFragment extends Fragment {
    private final static String WEBSITE_URL = "http://www.ost.ch";

    public static LoginInfoFragment create() {
        return new LoginInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_login_info, container, false);

        TextView urlTextView = fragment.findViewById(R.id.login_text_url);
        urlTextView.setOnClickListener(v -> openUrl());

        return fragment;
    }

    private void openUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WEBSITE_URL));
        startActivity(intent);
    }
}