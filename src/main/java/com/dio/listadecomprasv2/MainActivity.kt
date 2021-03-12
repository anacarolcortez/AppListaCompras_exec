package com.dio.listadecomprasv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dio.listadecomprasv2.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ProdutoAdapter(this)
        list_view_produtos.adapter = produtosAdapter

        list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            true
        }

        btnAdicionar.setOnClickListener {
            val	intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume()	{
        super.onResume()
        val	adapter	= list_view_produtos.adapter as	ProdutoAdapter
        adapter.clear() //para não duplicar a lista
        adapter.addAll(produtosGlobal)

        val	soma = produtosGlobal.sumByDouble {	it.preco * it.qtde }
        val	f =	NumberFormat.getCurrencyInstance(Locale("pt","br"))
        txtTotal.text = "TOTAL: ${f.format(soma)}"

    }

}