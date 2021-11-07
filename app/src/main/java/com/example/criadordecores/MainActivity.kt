package com.example.criadordecores

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var btNovaCor: Button
    private lateinit var view: View
    private lateinit var texto: TextView
    private var vermelho: Int = 0
    private var verde: Int = 0
    private var azul: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btNovaCor = findViewById(R.id.btNovaCor)
        this.texto = findViewById(R.id.tvNomeDaCor)
        this.view = findViewById(R.id.view)

        val resultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if (it.resultCode == RESULT_OK){
            val cor = it.data?.getSerializableExtra("Cor") as Cor

            this.vermelho = cor.vermelho
            this.verde = cor.verde
            this.azul = cor.azul

            this.view.setBackgroundColor(Color.rgb(vermelho,verde, azul))
            this.texto.setText("#" + Integer.toHexString(Color.rgb(vermelho, verde, azul)).toString().uppercase().substring(2))
        }else{
            Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
        }
    }

        this.btNovaCor.setOnClickListener{
            val intent = Intent(this, EscolherCorActivity::class.java)
            resultado.launch(intent)
        }
    }
}