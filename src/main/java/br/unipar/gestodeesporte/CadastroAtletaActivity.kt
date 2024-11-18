package br.unipar.gestodeesporte

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastroAtletaActivity : AppCompatActivity() {
    private lateinit var etNomeAtleta: EditText
    private lateinit var etIdade: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atleta)

        etNomeAtleta = findViewById(R.id.et_nome_atleta)
        etIdade = findViewById(R.id.et_idade)

        val btnSalvarAtleta = findViewById<Button>(R.id.btn_salvar)

        btnSalvarAtleta.setOnClickListener {
            val nomeAtleta = etNomeAtleta.text.toString()
            val idade = etIdade.text.toString()

            if (nomeAtleta.isNotEmpty() && idade.isNotEmpty()) {
                vibrateDevice()
                val resultIntent = Intent().apply {
                    putExtra("novoAtleta", nomeAtleta)
                    putExtra("idade", idade)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun vibrateDevice() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Verifica se o dispositivo suporta vibração
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                println("TESTE")

                vibrator.vibrate(VibrationEffect.createOneShot(10000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                println("TESTE")
                vibrator.vibrate(10000)
            }
        }
    }
}
