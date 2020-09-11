package ch.ost.rj.mge.u05.mailer.model.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.ost.rj.mge.u05.mailer.model.Email;

@Dao
public interface EmailDao {
    @Query("SELECT * FROM emails")
    List<Email> getEmails();

    @Insert
    void insert(Email email);
}
