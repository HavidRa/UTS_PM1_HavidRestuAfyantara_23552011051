package com.example.myapplication  // Mendefinisikan package untuk file ini

// Import library yang diperlukan
import android.content.Intent  // Untuk navigasi antar activity
import android.os.Bundle  // Untuk menyimpan state activity
import android.widget.Button  // Untuk tombol
import android.widget.EditText  // Untuk input text
import android.widget.Toast  // Untuk menampilkan pesan singkat
import androidx.appcompat.app.AppCompatActivity  // Base class untuk activity

class LoginActivity : AppCompatActivity() {  // Mendefinisikan class yang mewarisi AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {  // Method yang dipanggil saat activity dibuat
        super.onCreate(savedInstanceState)  // Memanggil method onCreate dari parent class
        setContentView(R.layout.activity_login)  // Mengatur layout untuk activity ini

        // Mengambil referensi ke view dari layout
        val etUsername = findViewById<EditText>(R.id.etUsername)  // Input field username
        val etPassword = findViewById<EditText>(R.id.etPassword)  // Input field password
        val btnLogin = findViewById<Button>(R.id.btnLogin)  // Tombol login
        val btnGoToRegister = findViewById<Button>(R.id.btnGoToRegister)  // Tombol ke halaman register

        btnLogin.setOnClickListener {  // Menangani event klik tombol login
            val usernameInput = etUsername.text.toString()  // Mengambil input username
            val passwordInput = etPassword.text.toString()  // Mengambil input password

            // Validasi input kosong
            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Username dan password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mengambil data yang tersimpan
            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            val savedUsername = sharedPref.getString("username", "")
            val savedPassword = sharedPref.getString("password", "")

            // Validasi login
            if (usernameInput == savedUsername && passwordInput == savedPassword) {
                // Menyimpan status login
                with(sharedPref.edit()) {
                    putString("isLoggedIn", "true")
                    apply()
                }

                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ChatListActivity::class.java))  // Pindah ke halaman chat
                finish()  // Menutup activity login
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        btnGoToRegister.setOnClickListener {  // Menangani event klik tombol register
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)  // Pindah ke halaman register
        }
    }
}