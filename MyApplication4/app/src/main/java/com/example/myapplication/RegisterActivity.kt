package com.example.myapplication  // Mendefinisikan package

// Import library yang diperlukan
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {  // Mendefinisikan class untuk halaman registrasi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)  // Mengatur layout

        // Mengambil referensi ke view dari layout
        val etUsername = findViewById<EditText>(R.id.etUsername)  // Input field username
        val etPassword = findViewById<EditText>(R.id.etPassword)  // Input field password
        val btnRegister = findViewById<Button>(R.id.btnRegister)  // Tombol register

        btnRegister.setOnClickListener {  // Menangani event klik tombol register
            val username = etUsername.text.toString()  // Mengambil input username
            val password = etPassword.text.toString()  // Mengambil input password

            // Validasi input kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Menyimpan data registrasi
            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("username", username)  // Simpan username
                putString("password", password)  // Simpan password
                apply()
            }

            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()

            // Kembali ke halaman login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Menutup activity register
        }
    }
}