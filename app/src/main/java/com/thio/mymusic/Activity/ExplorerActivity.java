package com.thio.mymusic.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.thio.mymusic.Adapter.ExplorerAdapter;
import com.thio.mymusic.MainActivity;
import com.thio.mymusic.R;
import java.util.ArrayList;
import java.util.List;
import com.thio.mymusic.Activity.ExplorerActivity.BoxPlaylist;




public class ExplorerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExplorerAdapter explorerAdapter;
    private List<BoxPlaylist> boxPlaylists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);

        // Inisialisasi komponen
        setupBottomAppBar();
        setupFloatingActionButton();

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.view1);

        // Inisialisasi data playlist (dummy data, sesuaikan dengan kebutuhan Anda)
        boxPlaylists = generateDummyData();

        // Set layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(explorerAdapter);
    }

    // Metode untuk menghasilkan dummy data playlist
    private List<BoxPlaylist> generateDummyData() {
        List<BoxPlaylist> dummyData = new ArrayList<>();
        dummyData.add(new BoxPlaylist("Playlist 1", "Subtitle 1", R.drawable.adele));
        dummyData.add(new BoxPlaylist("Playlist 2", "Subtitle 2", R.drawable.ariana));
        dummyData.add(new BoxPlaylist("Playlist 3", "Subtitle 3", R.drawable.shawnmandes));
        // Tambahkan lebih banyak data sesuai kebutuhan
        return dummyData;
    }

    private void setupBottomAppBar() {
        // Implementasikan fungsi-fungsi untuk bottomAppBar
        // Misalnya: bottomAppBar.setNavigationOnClickListener(...)
        // atau bottomAppBar.setOnMenuItemClickListener(...)
    }

    private void setupFloatingActionButton() {
        // Implementasikan fungsi-fungsi untuk floatingActionButton
        // Misalnya: floatingActionButton.setOnClickListener(...)
    }

    // Jika Anda ingin menangani navigasi antar halaman
    private void navigateToMain() {
        Intent mainIntent = new Intent(ExplorerActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish(); // Menutup ExplorerActivity agar tidak dapat dikembalikan dengan tombol "Back"
    }

    private void navigateToFavorites() {
        Intent favoritesIntent = new Intent(ExplorerActivity.this, FavoritesActivity.class);
        startActivity(favoritesIntent);
        finish(); // Menutup ExplorerActivity agar tidak dapat dikembalikan dengan tombol "Back"
    }

    // Kelas untuk menyimpan data playlist
    public static class BoxPlaylist {
        private String title;
        private String subtitle;
        private int imageResourceId;

        public BoxPlaylist(String title, String subtitle, int imageResourceId) {
            this.title = title;
            this.subtitle = subtitle;
            this.imageResourceId = imageResourceId;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }
    }
}
