package com.thio.mymusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thio.mymusic.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;
    private TextView btnLogin;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        buttonRegister = findViewById(R.id.button2);
        btnLogin = findViewById(R.id.btnLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tambahkan kode untuk pindah ke layar Login
                navigateToLogin();
            }
        });
    }

    // Metode untuk memeriksa apakah email sudah digunakan
    private void isEmailUsed(String email, final OnCompleteListener<QuerySnapshot> onCompleteListener) {
        // Query Firestore untuk memeriksa apakah email sudah digunakan
        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Periksa apakah password dan konfirmasi password sama
        if (!password.equals(confirmPassword)) {
            // Tampilkan pesan kesalahan
            showToast("Password and confirm password do not match");
            return;
        }

        // Periksa apakah email sudah digunakan
        isEmailUsed(email, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Periksa apakah email sudah digunakan
                    boolean emailUsed = !task.getResult().isEmpty();
                    if (emailUsed) {
                        // Jika email sudah digunakan, tampilkan pesan kesalahan
                        showToast("Email is already in use");
                    } else {
                        // Jika email belum digunakan, lanjutkan dengan registrasi
                        performRegistration(username, email);
                    }
                } else {
                    // Gagal mengambil data dari Firestore
                    showToast("Error checking email availability");
                }
            }
        });
    }

    private void performRegistration(final String username, String email) {
        // Lakukan autentikasi pengguna dengan Firebase
        auth.createUserWithEmailAndPassword(email, editTextPassword.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registrasi berhasil
                            saveUserData(username, email);
                        } else {
                            // Registrasi gagal
                            registrationFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    private void saveUserData(String username, String email) {
        // Buat objek data pengguna
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);

        // Simpan data pengguna ke Firestore dengan menggunakan UID sebagai kunci dokumen
        String uid = auth.getCurrentUser().getUid();
        db.collection("users")
                .document(uid)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Registrasi berhasil
                            registrationSuccess();
                        } else {
                            // Registrasi gagal
                            registrationFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    private void registrationFailure(String errorMessage) {
        // Registrasi gagal
        showToast("Registration failed: " + errorMessage);
    }

    private void showToast(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    private void registrationSuccess() {
        // Tampilkan pesan toast registrasi berhasil
        showToast("Selamat, registrasi Anda berhasil!");

        // Navigasi ke layar login setelah registrasi berhasil
        navigateToLogin();
    }

    // Metode untuk menangani klik pada tombol Login
    private void navigateToLogin() {
        // Implementasikan logika untuk pindah ke layar LoginActivity
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}