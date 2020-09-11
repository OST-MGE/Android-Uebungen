package ch.ost.rj.mge.u05.mailer.services;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;

public class VibrationService {
    private static final long SHORT_DURATION = 100;
    private static final long LONG_DURATION = 500;
    private static final int MIN_AMPLITUDE = 1;
    private static final int MAX_AMPLITUDE = 255;
    private static final int NO_REPEAT = -1;

    private static Vibrator vibrator;

    public static void initialize(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static void vibrateInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrate(VibrationEffect.createOneShot(SHORT_DURATION, MAX_AMPLITUDE));
        } else {
            vibrate(SHORT_DURATION);
        }
    }

    public static void vibrateSuccess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrate(VibrationEffect.createOneShot(LONG_DURATION, MAX_AMPLITUDE));
        } else {
            vibrate(SHORT_DURATION);
        }
    }

    public static void vibrateError() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrate(VibrationEffect.createWaveform(
                    new long[] { SHORT_DURATION, SHORT_DURATION, SHORT_DURATION },
                    new int[] { MAX_AMPLITUDE, MIN_AMPLITUDE, MAX_AMPLITUDE },
                    NO_REPEAT));
        } else {
            vibrate(SHORT_DURATION);
            vibrate(SHORT_DURATION);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void vibrate(VibrationEffect effect) {
        vibrator.vibrate(effect);
    }

    private static void vibrate(long duration) {
        vibrator.vibrate(duration);
    }
}
