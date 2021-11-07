package com.example.criadordecores

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.criadordecores.Cor

class EscolherCorActivity : AppCompatActivity() {

    private var cor: Cor = Cor(0,0,0)
    private lateinit var view: View
    private lateinit var tvNomeDaCor: TextView
    private lateinit var seekBarTOP: SeekBar
    private lateinit var seekBarMid: SeekBar
    private lateinit var seekBarBot: SeekBar
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolher_cor)

        this.view = findViewById(R.id.view)
        this.tvNomeDaCor = findViewById(R.id.textView)
        this.seekBarTOP = findViewById(R.id.seekBarTOP)
        this.seekBarMid = findViewById(R.id.seekBarMid)
        this.seekBarBot = findViewById(R.id.seekBarBot)
        this.btSalvar = findViewById(R.id.btSalvar)
        this.btCancelar = findViewById(R.id.btCancelar)

        this.seekBarTOP.setOnSeekBarChangeListener(OnChange())
        this.seekBarMid.setOnSeekBarChangeListener(OnChange())
        this.seekBarBot.setOnSeekBarChangeListener(OnChange())

        this.btCancelar.setOnClickListener{ finish() }

       this.btSalvar.setOnClickListener({salvar()})

    }

    private fun salvar() {
        val vermelho = this.seekBarTOP.progress
        val verde = this.seekBarMid.progress
        val azul = this.seekBarBot.progress

        val cor = Cor(vermelho, verde, azul)

        val intent = Intent().apply {
            putExtra("Cor", cor)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    inner class OnChange:SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this@EscolherCorActivity.view.setBackgroundColor(Color.rgb(this@EscolherCorActivity.seekBarTOP.progress,
                seekBarMid.progress, seekBarBot.progress))

            this@EscolherCorActivity.tvNomeDaCor.setText("#" + Integer.toHexString(
                Color.rgb(
                    this@EscolherCorActivity.seekBarTOP.progress,
                    seekBarMid.progress,
                    seekBarBot.progress
                )
            ).toString().uppercase().substring(2))

        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

}