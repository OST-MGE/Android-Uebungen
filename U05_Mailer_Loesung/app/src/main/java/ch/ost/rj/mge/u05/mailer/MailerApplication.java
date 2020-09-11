package ch.ost.rj.mge.u05.mailer;

import android.app.Application;

import ch.ost.rj.mge.u05.mailer.model.EmailRepository;
import ch.ost.rj.mge.u05.mailer.services.VibrationService;

public class MailerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        EmailRepository.initialize(getApplicationContext());
        VibrationService.initialize(getApplicationContext());
    }
}
