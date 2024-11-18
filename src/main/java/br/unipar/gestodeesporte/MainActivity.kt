package br.unipar.gestodeesporte

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val atletas = mutableListOf<String>()
    private val equipes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnListagemAtletas = findViewById<Button>(R.id.btn_listagem_atletas)
        val btnListagemEquipes = findViewById<Button>(R.id.btn_listagem_equipes)

        btnListagemAtletas.setOnClickListener {
            val intent = Intent(this, ListagemAtletaActivity::class.java)
            intent.putStringArrayListExtra("atletas", ArrayList(atletas))
            startActivity(intent)
        }

        btnListagemEquipes.setOnClickListener {
            val intent = Intent(this, ListagemEquipesActivity::class.java)
            intent.putStringArrayListExtra("equipes", ArrayList(equipes))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_ATLETAS -> {
                    // Recebe a lista atualizada de atletas
                    val novosAtletas = data.getStringArrayListExtra("novosAtletas")
                    if (novosAtletas != null) {
                        atletas.clear() // Limpa a lista antes de adicionar novos
                        atletas.addAll(novosAtletas)
                        Toast.makeText(this, "Lista de atletas atualizada!", Toast.LENGTH_SHORT).show()
                    }
                }
                REQUEST_CODE_EQUIPES -> {
                    // Recebe a lista atualizada de equipes
                    val novasEquipes = data.getStringArrayListExtra("novasEquipes")
                    if (novasEquipes != null) {
                        equipes.clear() // Limpa a lista antes de adicionar novas
                        equipes.addAll(novasEquipes)
                        Toast.makeText(this, "Lista de equipes atualizada!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ATLETAS = 1
        private const val REQUEST_CODE_EQUIPES = 2
    }
}
