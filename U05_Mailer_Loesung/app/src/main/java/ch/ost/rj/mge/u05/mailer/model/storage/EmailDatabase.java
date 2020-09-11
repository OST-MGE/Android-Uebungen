package ch.ost.rj.mge.u05.mailer.model.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ch.ost.rj.mge.u05.mailer.model.Email;

@Database(entities = { Email.class }, version = 1, exportSchema = false)
public abstract class EmailDatabase extends RoomDatabase {
    public abstract EmailDao emailDao();
}
