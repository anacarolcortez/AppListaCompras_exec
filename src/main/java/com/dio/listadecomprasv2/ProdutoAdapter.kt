package com.dio.listadecomprasv2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class ProdutoAdapter (context: Context) : ArrayAdapter<Produto>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        if (convertView != null){
            view = convertView
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
            val	item = getItem(position)
            val	formatar = NumberFormat.getCurrencyInstance(Locale("pt","br"))

            var produto = view.findViewById<TextView>(R.id.txtNomeCard)
            var qtde = view.findViewById<TextView>(R.id.txtQtdeCard)
            var preco = view.findViewById<TextView>(R.id.txtValorCard)
            var imagem = view.findViewById<ImageView>(R.id.imgProdutoCard)

            produto.text = item!!.nome.toString()
            qtde.text = item!!.qtde.toString()
            preco.text = formatar.format(item.preco)

            if (item.img != null){
                imagem.setImageBitmap(item.img)
            }

        }

        return view
    }
}