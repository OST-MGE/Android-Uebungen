package ch.ost.rj.mge.u03.mailer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ch.ost.rj.mge.u03.mailer.R;
import ch.ost.rj.mge.u03.mailer.adapter.EmailAdapter;
import ch.ost.rj.mge.u03.mailer.adapter.EmailViewHolder;
import ch.ost.rj.mge.u03.mailer.model.EmailRepository;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_outbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_new_message:
                showComposeActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showComposeActivity() {
        Intent intent = ComposeActivity.createIntent(this, loginEmail);
        startActivity(intent);
    }
}