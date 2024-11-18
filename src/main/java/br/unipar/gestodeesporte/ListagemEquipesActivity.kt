package br.unipar.gestodeesporte

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListagemEquipesActivity : AppCompatActivity() {

    private lateinit var listViewEquipes: ListView
    private lateinit var equipes: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_equipes)

        listViewEquipes = findViewById(R.id.list_view_equipes)
        val btnCadastrarEquipes = findViewById<Button>(R.id.btn_cadastrar_equipes)

        equipes = intent.getStringArrayListExtra("equipes")?.toMutableList() ?: mutableListOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipes)
        listViewEquipes.adapter = adapter

        btnCadastrarEquipes.setOnClickListener {
            val intent = Intent(this, CadastroDeEquipesActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CADASTRO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CADASTRO && resultCode == Activity.RESULT_OK) {
            val novaEquipe = data?.getStringExtra("novaEquipe")
            val cidade = data?.getStringExtra("cidade")
            if (novaEquipe != null && cidade != null) {
                equipes.add("$novaEquipe, Cidade: $cidade")
                (listViewEquipes.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_CADASTRO = 2
    }
}
