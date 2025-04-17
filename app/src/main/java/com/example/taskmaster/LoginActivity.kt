package com.example.taskmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.editTextEmail)
        val senha = findViewById<EditText>(R.id.editTextSenha)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            if (email.text.toString() == "admin" && senha.text.toString() == "123") {
                startActivity(Intent(this, ListaTarefasActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
