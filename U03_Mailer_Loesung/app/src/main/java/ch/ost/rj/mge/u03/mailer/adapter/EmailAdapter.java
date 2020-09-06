package ch.ost.rj.mge.u03.mailer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ch.ost.rj.mge.u03.mailer.model.Email;

public class EmailAdapter extends RecyclerView.Adapter<EmailViewHolder> {
    private final ArrayList<Email> emails;

    public EmailAdapter(ArrayList<Email> emails) {
        this.emails = emails;
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
        holder.fromTextView.setText(email.getFrom());
        holder.subjectTextView.setText(email.getSubject());
    }

    @Override
    public int getItemCount() {
        return this.emails.size();
    }
}
