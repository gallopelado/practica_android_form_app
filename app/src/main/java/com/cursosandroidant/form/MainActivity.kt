package com.cursosandroidant.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Vamos a inflar el menu en esta activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Método para manipular los items del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_send){
            // Forma tradicional usando findViewById
            val name: String = findViewById<TextInputEditText>(R.id.etName).text.toString().trim()
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}