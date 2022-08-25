package ch.ost.rj.mge.u06.mailer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "emails")
public final class Email {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String from;

    @ColumnInfo
    public String to;

    @ColumnInfo
    public String subject;

    @ColumnInfo
    public String content;
}
