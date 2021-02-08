package com.ingenieria.voz_arduino

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ingenieria.jhr.bluetoothjhr.BluetoothJhr
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*


class MainActivity2 : AppCompatActivity() {

   lateinit var blue:BluetoothJhr
    private val SPEECH_REQUEST_CODE = 0

    var stateConexion = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        blue = BluetoothJhr(this,MainActivity::class.java)


        btn_a.setOnClickListener {
            blue.mTx("a")
        }
        btn_b.setOnClickListener {
            blue.mTx("b")
        }
        btn_c.setOnClickListener {
            blue.mTx("c")
        }
        btn_d.setOnClickListener {
            blue.mTx("d")
        }

        btn_reconoce.setOnClickListener {
            activityReconoce()
        }

    }



    ///////////////////Voz a texto/////////////////////////

    // Crear un intent que pueda iniciar la actividad de Speech Recognizer
    private fun activityReconoce() {
        //EXTRA_LANGUAGE_MODEL = Informa al reconocedor qué modelo de voz preferir al realizar la interpretación
        //LANGUAGE_MODEL_FREE_FORM = Utilice un modelo de lenguaje basado en el reconocimiento de voz de forma libre.
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        // Inicie la actividad, la intención se completará con el texto de voz
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }


    // Esta devolución de llamada se invoca cuando vuelve Speech Recognizer.
    //   Aquí es donde procesa la intención y extrae el texto de voz de la intención.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //RESULT_OK =  operación realizada correctamente.
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val textoSpeech: String? =
                data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results!![0]
                }
            textview.text = textoSpeech
            sendBluetooth(textoSpeech)
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun sendBluetooth(stringTx: String?) {
        when(stringTx){
            "amarillo"->blue.mTx("a")
            "azul"->blue.mTx("b")
            "rojo"->blue.mTx("c")
            "verde"->blue.mTx("d")
        }
    }

    /////////////////fin voz a texto/////////////////////////





    override fun onResume() {
        super.onResume()
        if (!stateConexion){
            blue.conectaBluetooth()
            stateConexion = true
        }

        //blue.mTx("a")
    }

    override fun onDestroy() {
        super.onDestroy()
        blue.exitConexion()
    }

}