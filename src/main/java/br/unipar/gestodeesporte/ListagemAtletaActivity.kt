package br.unipar.gestodeesporte

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListagemAtletaActivity : AppCompatActivity() {

    private lateinit var listViewAtletas: ListView
    private lateinit var atletas: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_atleta)

        listViewAtletas = findViewById(R.id.list_view_atletas)
        val btnCadastrarAtletas = findViewById<Button>(R.id.btn_cadastro_atletas)

        atletas = intent.getStringArrayListExtra("atletas")?.toMutableList() ?: mutableListOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, atletas)
        listViewAtletas.adapter = adapter

        btnCadastrarAtletas.setOnClickListener {
            val intent = Intent(this, CadastroAtletaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CADASTRO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CADASTRO && resultCode == Activity.RESULT_OK) {
            val novoAtleta = data?.getStringExtra("novoAtleta")
            val idade = data?.getStringExtra("idade")
            if (novoAtleta != null && idade != null) {
                atletas.add("$novoAtleta, Idade: $idade")
                (listViewAtletas.adapter as ArrayAdapter<String>).notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_CADASTRO = 2
    }
}
