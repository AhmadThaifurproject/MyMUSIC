package com.thio.mymusic.Api;

import android.os.AsyncTask;
import okhttp3.*;

public class MusicApiTask extends AsyncTask<Void, Void, String> {

    private MusicApiListener listener; // Deklarasikan variabel listener

    // Konstruktor yang menerima listener sebagai parameter
    public MusicApiTask(MusicApiListener listener) {
        this.listener = listener;
    }

    // Interface untuk mendengarkan hasil dari MusicApiTask
    public interface MusicApiListener {
        void onMusicApiTaskComplete(String result);
    }

    // Metode untuk melakukan permintaan ke API
    public void executeApiRequest() {
        execute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Inisialisasi OkHttpClient
            OkHttpClient client = new OkHttpClient();

            // Persiapkan body request
            MediaType mediaType = MediaType.parse("application/json");
            String requestBody = "{\n" +
                    "  \"track\": \"Bezos I\",\n" +
                    "  \"artist\": \"Bo Burnham\",\n" +
                    "  \"type\": \"track\",\n" +
                    "  \"sources\": [\n" +
                    "    \"spotify\"\n" +
                    "  ]\n" +
                    "}";
            RequestBody body = RequestBody.create(mediaType, requestBody);

            // Bangun request
            Request request = new Request.Builder()
                    .url("https://api.musicapi.com/public/search")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Token 2cf82db3-4064-4235-8253-16994eb51773")
                    .build();

            // Eksekusi request
            Response response = client.newCall(request).execute();

            // Dapatkan response body sebagai string
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return "Error: " + response.code();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // Kirim hasil response ke listener
        if (listener != null) {
            listener.onMusicApiTaskComplete(result);
        }
    }
}
