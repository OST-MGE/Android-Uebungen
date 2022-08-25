package ch.ost.rj.mge.u06.mailer.model;

import android.content.Context;

import androidx.room.Room;

import java.util.List;
import java.util.Observable;

import ch.ost.rj.mge.u06.mailer.model.storage.EmailDatabase;

public final class EmailRepository extends Observable {
    private static EmailRepository instance;
    private final EmailDatabase database;

    public static void initialize(Context context) {
        instance = new EmailRepository(context);
    }

    public static EmailRepository getInstance() {
        return instance;
    }

    private EmailRepository(Context context) {
        database = Room.databaseBuilder(context, EmailDatabase.class, "mailer.db").allowMainThreadQueries().build();

        if (getEmails().size() == 0) {
            addEmail("test@ost.ch", "mge@ost.ch", "Testnachricht 1", "Testnachricht ohne Inhalt");
            addEmail("test@ost.ch", "mge@ost.ch", "Testnachricht 2", "Testnachricht ohne Inhalt");
        }
    }

    public List<Email> getEmails() {
        return database.emailDao().getEmails();
    }

    public void addEmail(Email email) {
        database.emailDao().insert(email);
        setChanged();
        notifyObservers(email);
    }

    public Email addEmail(String from, String to, String subject, String content) {
        Email email = new Email();
        email.from = from;
        email.to = to;
        email.subject = subject;
        email.content = content;

        addEmail(email);

        return email;
    }
}
