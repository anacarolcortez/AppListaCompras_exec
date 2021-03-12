package com.dio.listadecomprasv2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity: AppCompatActivity()  {

    val	COD_IMAGE =	101
    var	imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnCadastrar.setOnClickListener {
            val produto = txtProduto.text.toString()
            val quantidade = txtQtde.text.toString()
            val preco = txtPreco.text.toString()

            if (produto.isNotEmpty() && quantidade.isNotEmpty() && preco.isNotEmpty()) {
                val produtoCadastrado = Produto(produto, quantidade.toInt(), preco.toDouble(), imageBitMap)
                produtosGlobal.add(produtoCadastrado)
                txtProduto.text.clear()
                txtQtde.text.clear()
                txtPreco.text.clear()
                imageBitMap = null
            } else {
                txtProduto.error = if (txtProduto.text.isEmpty()) "Informe o nome do produto" else null
                txtQtde.error = if (txtQtde.text.isEmpty()) "Informe a quantidade" else null
                txtPreco.error = if (txtPreco.text.isEmpty()) "Informe o preço" else null
            }
        }

        imgProduto.setOnClickListener {
            abrirGaleria()
        }
    }

    fun	abrirGaleria(){
        val	intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type	= "image/*"
        startActivityForResult(Intent.createChooser(intent,	"Selecione	uma	imagem"),	COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)	{
        super.onActivityResult(requestCode,	resultCode,	data)
        if	(requestCode ==	COD_IMAGE && resultCode	== Activity.RESULT_OK)	{
            if	(data !=	null)	{
                val	inputStream	= data.data?.let { contentResolver.openInputStream(it) };
                imageBitMap	=	BitmapFactory.decodeStream(inputStream)
                imgProduto.setImageBitmap(imageBitMap)
            }
        }
    }



}