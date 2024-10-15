package lat.pam.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        val spinner: Spinner = findViewById(R.id.city_spinner)

        // Membuat adapter untuk menghubungkan data spinner dengan widget Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items, // Array dari strings.xml
            android.R.layout.simple_spinner_item // Layout standar untuk item Spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Layout dropdown item
            spinner.adapter = adapter // Menghubungkan adapter dengan Spinner
        }

        // Menambahkan listener ke Spinner untuk menangkap item yang dipilih
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCity = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@OrderActivity, "Selected city: $selectedCity", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton) .isChecked
        when (view.getId()) {
            R.id.sameday -> if (checked)   // same day service
                displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday -> if (checked)   // same day service
                displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked)   // same day service
                displayToast(getString(R.string.pick_up))
            else -> {}
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText (
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }
}