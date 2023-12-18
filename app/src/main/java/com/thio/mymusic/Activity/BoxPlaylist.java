package com.thio.mymusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.thio.mymusic.R;
import com.thio.mymusic.Adapter.ExplorerAdapter;
import com.thio.mymusic.R.layout;

public class BoxPlaylist extends AppCompatActivity {

    private String title;
    private String subtitle;

    public BoxPlaylist(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getPlaylistTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.item_view_music);

        // Dapatkan referensi ke widget di layout (misalnya, ImageView, TextView, RecyclerView)
        ImageView imageViewAlbum = findViewById(R.id.imageViewAlbum);
        RecyclerView recyclerViewMusicList = findViewById(R.id.recyclerViewMusicList);

        // Implementasikan logika untuk menampilkan foto album dan daftar lagu
        // Misalnya, dapatkan data dari Intent
        String playlistId = getIntent().getStringExtra("playlist_id");
        // Gunakan data yang dikirim dari ExplorerActivity atau API untuk mendapatkan informasi playlist
        // Tampilkan data di widget sesuai kebutuhan Anda
        // Jika menggunakan Retrofit atau sumber data lainnya, Anda dapat membuat panggilan API di sini
    }


}
