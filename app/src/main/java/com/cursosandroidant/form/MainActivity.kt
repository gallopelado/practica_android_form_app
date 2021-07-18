package com.cursosandroidant.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cursosandroidant.form.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // Luego crear este atributo, se genera automáticamente
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar dicho atributo
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Se utiliza root, en este caso es nuestro ScrollView del activity_main.xml
        setContentView(binding.root)
    }

    // Vamos a inflar el menu en esta activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Método para manipular los items del menu
    /*
    * Para la nueva manera de obtener datos, hay que ir primero al build.gradle(app)
    * dentro de android {}
    *   buildFeatures {
            viewBinding true
        }
    * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_send){
            // Forma tradicional usando findViewById
            val name: String = findViewById<TextInputEditText>(R.id.etName).text.toString().trim()
            // Forma nueva con viewbinding
            val surname: String = binding.etSurname.text.toString().trim()
            Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}