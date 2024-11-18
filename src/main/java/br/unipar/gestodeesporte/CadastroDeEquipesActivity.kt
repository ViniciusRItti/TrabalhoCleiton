package br.unipar.gestodeesporte

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

class CadastroDeEquipesActivity : AppCompatActivity() {
    private lateinit var etNomeEquipe: EditText
    private lateinit var etCidade: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_equipes)

        etNomeEquipe = findViewById(R.id.et_nome_equipe)
        etCidade = findViewById(R.id.et_cidade)

        val btnSalvarEquipe = findViewById<Button>(R.id.btn_salvar_equipe)

        btnSalvarEquipe.setOnClickListener {
            val nomeEquipe = etNomeEquipe.text.toString()
            val cidade = etCidade.text.toString()

            if (nomeEquipe.isNotEmpty() && cidade.isNotEmpty()) {
                vibrateDevice()
                val resultIntent = Intent().apply {
                    putExtra("novaEquipe", nomeEquipe)
                    putExtra("cidade", cidade)
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
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }
        }
    }
}
