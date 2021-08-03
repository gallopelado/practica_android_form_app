package com.cursosandroidant.form

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cursosandroidant.form.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // Luego crear este atributo, se genera automáticamente
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar dicho atributo
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Se utiliza root, en este caso es nuestro ScrollView del activity_main.xml
        setContentView(binding.root)

        // asignar un evento click al datepicker del formulario
        binding.etDateBirth.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener { timeInMilliseconds ->
                val dateStr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .apply {
                        timeZone = TimeZone.getTimeZone("UTC")
                    }
                    .format(timeInMilliseconds)

                binding.etDateBirth.setText(dateStr)
            }

            picker.show(supportFragmentManager, picker.toString())
        }

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
            if(validFields()) {
                // Forma tradicional usando findViewById
                val name: String =
                    findViewById<TextInputEditText>(R.id.etName).text.toString().trim()
                // Forma nueva con viewbinding
                val surname: String = binding.etSurname.text.toString().trim()
                val height: String = binding.etHeight.text.toString().trim()
                val dateBirth: String = binding.etDateBirth.text.toString().trim()
                val country: String = binding.actvCountries.text.toString().trim()
                val placeBirth: String = binding.etPlaceBirth.text.toString().trim()
                val notes: String = binding.etNotes.text.toString().trim()
                // Uso del toast
                //Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_title))
                builder.setMessage(joinData(name, surname, height, dateBirth, country, placeBirth, notes))
                // dos parámetros, el mensaje y el evento, que ahora fue recortado a dialogInterface
                // dentro de las llaves metemos el Toast para probar el evento
                // boton limpiar formulario
                builder.setPositiveButton(getString(R.string.dialog_ok), { dialogInterface, i ->
                    //Toast.makeText(this, "positive button", Toast.LENGTH_SHORT).show()
                    with(binding){
                        etName.text?.clear()
                        etSurname.text?.clear()
                        etHeight.text?.clear()
                        etDateBirth.text?.clear()
                        actvCountries.text?.clear()
                        etPlaceBirth.text?.clear()
                        etNotes.text?.clear()
                    }
                })
                // Ahora el botón negative, con la opción por defecto
                // también es posible usar null en vez del click listener
                /*
                builder.setNegativeButton(getString(R.string.dialog_cancel), DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "negative button", Toast.LENGTH_LONG).show()
                })
                */
                builder.setNegativeButton(getString(R.string.dialog_cancel), null)

                // Necesitamos construir ese dialog
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun joinData(vararg fields:String):String {
        var result = ""
        fields.forEach { item ->
            if(item.isNotEmpty()) {
                result += "$item\n"
            }
        }
        return result
    }

    private fun validFields(): Boolean {
        var isValid = true

        if(binding.etName.text.isNullOrEmpty()) {
            binding.tilName.run {
                error = getString(R.string.common_mandatory_hint)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if(binding.etSurname.text.isNullOrEmpty()) {
            binding.tilSurname.run {
                error = getString(R.string.common_mandatory_hint)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilSurname.error = null
        }

        if(binding.etHeight.text.isNullOrEmpty()) {
            binding.tilHeight.run {
                error = getString(R.string.common_mandatory_hint)
                requestFocus()
            }
            isValid = false
        } else if(binding.etHeight.text.toString().toInt() < 50) {
            binding.tilHeight.run {
                error = getString(R.string.helper_height)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilHeight.error = null
        }

        return isValid
    }

}