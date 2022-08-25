package ch.ost.rj.mge.u06.mailer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ch.ost.rj.mge.u06.mailer.model.Email;
import ch.ost.rj.mge.u06.mailer.model.EmailRepository;

public class EmailAdapter extends RecyclerView.Adapter<EmailViewHolder> implements Observer {
    private final List<Email> emails;

    public EmailAdapter(EmailRepository repository) {
        this.emails = new ArrayList<>(repository.getEmails());
        repository.addObserver(this);
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(
                android.R.layout.simple_list_item_2,
                parent,
                false);

        TextView fromTextView = view.findViewById(android.R.id.text1);
        TextView subjectTextView = view.findViewById(android.R.id.text2);

        return new EmailViewHolder(view, fromTextView, subjectTextView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        Email email = this.emails.get(position);
        holder.fromTextView.setText(email.from);
        holder.subjectTextView.setText(email.subject);
    }

    @Override
    public int getItemCount() {
        return this.emails.size();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.emails.add((Email)arg);
        notifyItemInserted(this.emails.size());
    }
}
