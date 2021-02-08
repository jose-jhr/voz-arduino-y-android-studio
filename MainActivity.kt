package com.ingenieria.voz_arduino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val blue = BluetoothJhr(this,listBluetooth,MainActivity2::class.java)

        blue.onBluetooth()

        listBluetooth.setOnItemClickListener { parent, view, position, id ->
                blue.bluetoothSeleccion(position)
        }


    }
}