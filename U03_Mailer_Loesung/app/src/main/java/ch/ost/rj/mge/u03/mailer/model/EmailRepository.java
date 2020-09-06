package ch.ost.rj.mge.u03.mailer.model;

import java.util.ArrayList;

public final class EmailRepository {
    private static ArrayList<Email> emails;

    static {
        emails = new ArrayList<>();

        emails.add(new Email("test@ost.ch", "mge@ost.ch", "Testnachricht 1", "Testnachricht ohne Inhalt"));
        emails.add(new Email("test@ost.ch", "mge@ost.ch", "Testnachricht 2", "Testnachricht ohne Inhalt"));
    }

    public static ArrayList<Email> getEmails() {
        return emails;
    }

    public static void addEmail(Email email) {
        emails.add(email);
    }
}
