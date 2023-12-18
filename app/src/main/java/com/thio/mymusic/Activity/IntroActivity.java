package com.thio.mymusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.thio.mymusic.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button masukButton = findViewById(R.id.button);

        masukButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Arahkan ke halaman login
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                // Optional: tambahkan finish() jika Anda ingin menutup IntroActivity setelah pindah ke LoginActivity
                finish();
            }
        });
    }
}