package com.bana.assistant;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 60, 40, 40);

        TextView title = new TextView(this);
        title.setText("Bana Assistant");
        title.setTextSize(30);

        TextView status = new TextView(this);
        status.setText("Ready, Boss");
        status.setTextSize(20);

        Button speakButton = new Button(this);
        speakButton.setText("🔊 Speak");

        layout.addView(title);
        layout.addView(status);
        layout.addView(speakButton);

        setContentView(layout);

        tts = new TextToSpeech(this, result -> {
            if (result == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
                tts.speak("Ready, Boss", TextToSpeech.QUEUE_FLUSH, null, "ready");
            }
        });

        speakButton.setOnClickListener(v ->
            tts.speak("Yes Boss, I am listening", TextToSpeech.QUEUE_FLUSH, null, "listen")
        );
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
