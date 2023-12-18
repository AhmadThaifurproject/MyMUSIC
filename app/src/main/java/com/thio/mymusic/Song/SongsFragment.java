package com.thio.mymusic.Song;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thio.mymusic.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thio.mymusic.Adapter.MusicAdapter;
import com.thio.mymusic.R;

public class SongsFragment extends Fragment {

    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    public SongsFragment(){
        //required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_songs_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if (!(musicFiles.size()) < 1)
        {
            musicAdapter = new MusicAdapter(getContext(), musicFiles);
            recyclerView.setAdapter(musicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                    false));
        }
        return view;
    }
}
