package com.example.criadordecores

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    private lateinit var btNovaCor: Button
    private lateinit var mainLayout: ConstraintLayout
    private var vermelho: Int = 0
    private var verde: Int = 0
    private var azul: Int = 0


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btNovaCor = findViewById(R.id.btNovaCor)

        val formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

            if (it.resultCode == RESULT_OK){
                val cor = it.data?.getSerializableExtra("PLANO") as Cor
                this.vermelho = cor.vermelho
                this.verde = cor.verde
                this.azul = cor.azul
                this.mainLayout.setBackgroundColor(Color.rgb(vermelho, verde, azul))
            }else{
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
            }
        }

        this.btNovaCor.setOnClickListener{
            val intent = Intent(this, EscolherCorActivity::class.java)
            formResult.launch(intent)
        }
    }
}