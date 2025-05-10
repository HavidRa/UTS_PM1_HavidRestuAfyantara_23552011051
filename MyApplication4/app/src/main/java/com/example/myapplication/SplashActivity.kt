package com.example.myapplication  // Mendefinisikan package

// Import library yang diperlukan
import android.content.Intent
import android.os.Bundle
import android.os.Handler  // Untuk menangani delay
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {  // Mendefinisikan class untuk splash screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)  // Mengatur layout splash screen

        // Menjalankan kode setelah delay 2 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            val isLoggedIn = sharedPref.getString("isLoggedIn", "false")  // Cek status login
            val savedUsername = sharedPref.getString("username", null)  // Cek username tersimpan
            val savedPassword = sharedPref.getString("password", null)  // Cek password tersimpan

            // Logika navigasi berdasarkan status login
            if (isLoggedIn == "true" && !savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
                startActivity(Intent(this, ChatListActivity::class.java))  // Ke halaman chat jika sudah login
            } else {
                startActivity(Intent(this, LoginActivity::class.java))  // Ke halaman login jika belum
            }

            finish()  // Menutup splash screen
        }, 2000)  // Delay 2 detik
    }
}