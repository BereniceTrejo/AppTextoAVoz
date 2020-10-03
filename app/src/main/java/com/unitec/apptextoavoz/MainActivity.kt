package com.unitec.apptextoavoz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    //Este objeto es el intermediario entre nuestra app y TextToSpeech
    private var tts:TextToSpeech?=null
    /*El siguiente codigo de peticion es un entero que nos va a ayudar a garantizar el objeto TextToSpeech
    * se inicio completamente*/
    private val CODIGO_PETICION=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Iniciamos ahora si la variable tts para que no este en null
        tts=TextToSpeech(this,this)

        //Vamos a escuchar esa vocesita de android, de bienvenida
        Timer("Bienvenida",false).schedule(1000){
            tts!!.speak(
                    "Hola, bienvenido a mi aplicación mis chavos de UNITEC, espero les encante!!, oprime el botón para que te escuche",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    ""
            )
        }
    }

    override fun onInit(estado: Int) {
        /*Este metodo o funcion sirve para que se inicialize la configuración al arrancar la app.(idioma)*/
        if (estado==TextToSpeech.SUCCESS){
            //Si el if se cumplio la ejecucion seguira aqui dentro
            val local=Locale("spa","MEX")
            //La siguiente variable es para que internamente nosotros sepamos que la app va bien
            val resultado=tts!!.setLanguage(local)
            if(resultado==TextToSpeech.LANG_MISSING_DATA){
                Log.i("Malo","NOOOOOO,NO FUNCIONO EL LENGUAJE ALGO ANDA MAL")
            }
        }
    }
}