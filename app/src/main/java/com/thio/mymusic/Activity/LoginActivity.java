package com.thio.mymusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.thio.mymusic.Activity.ExplorerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thio.mymusic.MainActivity;
import com.thio.mymusic.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView btnRegister;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin = findViewById(R.id.button2);
        btnRegister = findViewById(R.id.btnRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tambahkan kode untuk pindah ke layar Register
                navigateToRegister();
            }
        });
    }

    private void loginUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Lakukan autentikasi pengguna dengan Firebase
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login berhasil
                            loginSuccess();
                        } else {
                            // Login gagal
                            loginFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    private void loginSuccess() {
        // Login successful
        showToast("Login successful!");
        // You can add further actions here, such as navigating to the home screen.
        // Navigasi ke halaman Explorer setelah login berhasil
        navigateToActivity();
    }

    private void loginFailure(String errorMessage) {
        // Login failed
        showToast("Login failed: " + errorMessage);
        // You can add further actions here, such as displaying an error message.
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    // Metode untuk menangani klik pada tombol Register
    private void navigateToRegister() {
        // Implementasikan logika untuk pindah ke layar RegisterActivity
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
    // Metode untuk menangani klik pada tombol Explorer
    private void navigateToActivity() {
        // Implementasikan logika untuk pindah ke halaman ExplorerActivity
        Intent explorerIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(explorerIntent);
        finish(); // Menutup LoginActivity agar tidak dapat dikembalikan dengan tombol "Back"
    }
}
