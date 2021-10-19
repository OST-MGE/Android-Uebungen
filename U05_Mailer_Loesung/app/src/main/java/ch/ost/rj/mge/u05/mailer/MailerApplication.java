package ch.ost.rj.mge.u05.mailer;

import android.app.Application;
import android.content.Context;

import ch.ost.rj.mge.u05.mailer.model.EmailRepository;
import ch.ost.rj.mge.u05.mailer.services.EmailPersistenceService;
import ch.ost.rj.mge.u05.mailer.services.VibrationService;

public class MailerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();

        EmailPersistenceService.initialize(context);
        EmailRepository.initialize(context);
        VibrationService.initialize(context);
    }
}
