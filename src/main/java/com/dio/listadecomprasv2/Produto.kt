package com.dio.listadecomprasv2

import android.graphics.Bitmap

data class Produto (
    val nome: String,
    val qtde: Int,
    val preco: Double,
    val img: Bitmap? = null
){

}