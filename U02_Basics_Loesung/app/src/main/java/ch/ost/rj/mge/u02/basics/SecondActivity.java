package ch.ost.rj.mge.u02.basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static String TEXT_PARAMETER = "Text";

    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        outputText = this.findViewById(R.id.textOutput);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String parameter = this.getIntent().getStringExtra(TEXT_PARAMETER);
        outputText.setText(parameter);
    }
}
