package com.thio.mymusic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thio.mymusic.Activity.DetailMusicActivity;
import com.thio.mymusic.Activity.DownloadActivity;
import com.thio.mymusic.Activity.ExplorerActivity;
import com.thio.mymusic.Activity.FavoritesActivity;
import com.thio.mymusic.Activity.ProfileActivity;
import com.thio.mymusic.Api.MusicApiTask;

public class MainActivity extends AppCompatActivity implements MusicApiTask.MusicApiListener {

    private ImageView explorer, favorites, playbutton, download, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi ImageView
        explorer = findViewById(R.id.explorer);
        favorites = findViewById(R.id.favorites);
        playbutton = findViewById(R.id.playbutton);
        download = findViewById(R.id.download);
        profile = findViewById(R.id.profile);

        // Atur listener untuk setiap ImageView
        explorer.setOnClickListener(view -> navigateToExplorer());
        favorites.setOnClickListener(view -> navigateToFavorites());
        playbutton.setOnClickListener(view -> {
            // Tambahkan kode untuk menangani klik pada Play Button
            // (Removed the startPlayback method)
        });
        download.setOnClickListener(view -> navigateToDownload());
        profile.setOnClickListener(view -> navigateToProfile());

        // Panggil AsyncTask untuk melakukan permintaan ke API
        findViewById(R.id.floatingActionButton).setOnClickListener(this::requestMusicApi);
    }

    // Metode untuk menangani klik pada Explorer
    private void navigateToExplorer() {
        Intent explorerIntent = new Intent(MainActivity.this, ExplorerActivity.class);
        startActivity(explorerIntent);
    }

    // Metode untuk menangani klik pada Favorites
    private void navigateToFavorites() {
        Intent favoritesIntent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(favoritesIntent);
    }

    // Metode untuk menangani klik pada Download
    private void navigateToDownload() {
        Intent downloadIntent = new Intent(MainActivity.this, DownloadActivity.class);
        startActivity(downloadIntent);
    }

    // Metode untuk menangani klik pada Profile
    private void navigateToProfile() {
        Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    // Metode untuk menangani klik pada Floating Action Button
    private void requestMusicApi(View view) {
        // Panggil AsyncTask untuk melakukan permintaan ke API
        new MusicApiTask(this).executeApiRequest();
    }

    // Implementasi dari MusicApiListener untuk menangani hasil response dari AsyncTask
    @Override
    public void onMusicApiTaskComplete(String result) {
        // Handle hasil response di sini
        Log.d("MainActivity", "Response: " + result);
        Toast.makeText(this, "Music API response received", Toast.LENGTH_SHORT).show();
    }

}
