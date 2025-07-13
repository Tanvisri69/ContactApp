package com.example.contactapp1.presentation.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun ImageCompress(imageDate : ByteArray) : ByteArray{
    val bitMap = BitmapFactory.decodeByteArray(imageDate, 0, imageDate.size)
    val outputstream = ByteArrayOutputStream()
    bitMap.compress(Bitmap.CompressFormat.JPEG, 50, outputstream)
    return outputstream.toByteArray()
}