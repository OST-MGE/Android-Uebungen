package ch.ost.rj.mge.u02.basics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);

        logStateChange("onCreate");

        Button navigateButton = findViewById(R.id.buttonNavigate);
        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(SecondActivity.TEXT_PARAMETER, "Hallo Android!");
            startActivity(intent);
        });

        Button browserButton = findViewById(R.id.buttonBrowser);
        browserButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ost.ch"));

            // resolveActivity() braucht ab API-30 (Android 11) einen Eintrag im Manifest.
            // Mehr dazu hier:
            // https://developer.android.com/about/versions/11/privacy/package-visibility
            // https://stackoverflow.com/questions/62535856/intent-resolveactivity-returns-null-in-api-30/62856745#62856745
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        logStateChange("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logStateChange("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logStateChange("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logStateChange("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logStateChange("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logStateChange("onDestroy");
    }

    private static void logStateChange(String callback) {
        Log.d("MGE.U02.DEBUG", "Method: " + callback);
    }
}