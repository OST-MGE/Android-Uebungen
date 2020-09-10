package ch.ost.rj.mge.u05.mailer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ch.ost.rj.mge.u05.mailer.R;
import ch.ost.rj.mge.u05.mailer.adapter.EmailAdapter;
import ch.ost.rj.mge.u05.mailer.adapter.EmailViewHolder;
import ch.ost.rj.mge.u05.mailer.model.EmailRepository;

public class OutboxActivity extends AppCompatActivity {
    private static final String LOGIN_EMAIL_KEY = "email";
    private String loginEmail;
    RecyclerView.Adapter<EmailViewHolder> adapter;

    public static Intent createIntent(Context context, String fromEmail) {
        Intent intent = new Intent(context, OutboxActivity.class);
        intent.putExtra(LOGIN_EMAIL_KEY, fromEmail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_outbox);

        loginEmail = getIntent().getStringExtra(LOGIN_EMAIL_KEY);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new EmailAdapter(EmailRepository.getEmails());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.outbox_sent_emails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = findViewById(R.id.outbox_fab_new);
        fab.setOnClickListener(v -> {
            showComposeActivity();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    private void showComposeActivity() {
        Intent intent = ComposeActivity.createIntent(this, loginEmail);
        startActivity(intent);
    }
}
