package com.example.myapplication  // Mendefinisikan package

// Import library yang diperlukan
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChatListActivity : AppCompatActivity() {  // Mendefinisikan class untuk halaman chat list
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)  // Mengatur layout

        val btnLogout = findViewById<Button>(R.id.btnLogout)  // Mengambil referensi tombol logout

        btnLogout.setOnClickListener {  // Menangani event klik tombol logout
            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("isLoggedIn", "false")  // Mengubah status login menjadi false
                apply()
            }

            Toast.makeText(this, "Logout berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)  // Kembali ke halaman login
            finish()  // Menutup activity chat list
        }
    }
}