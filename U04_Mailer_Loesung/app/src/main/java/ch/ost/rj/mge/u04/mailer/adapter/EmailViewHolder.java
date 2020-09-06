package ch.ost.rj.mge.u04.mailer.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EmailViewHolder extends RecyclerView.ViewHolder {
    public TextView fromTextView;
    public TextView subjectTextView;

    public EmailViewHolder(View parent, TextView fromTextView, TextView subjectTextView) {
        super(parent);
        this.fromTextView = fromTextView;
        this.subjectTextView = subjectTextView;
    }
}
