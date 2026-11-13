package com.example.recibosmart

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NovoReciboActivity : AppCompatActivity() {

    // 1. Contrato da câmara: o que fazer quando a foto é tirada
    private val tirarFoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        if (bitmap != null) {
            // Encontra a ImageView e coloca lá a foto tirada
            val imgRecibo: ImageView = findViewById(R.id.imgRecibo)
            imgRecibo.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novo_recibo)

        // Código padrão do Android Studio para as margens
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Ligar o botão roxo à ação de abrir a câmara
        val btnFotografar: Button = findViewById(R.id.btnFotografar)
        btnFotografar.setOnClickListener {
            tirarFoto.launch(null) // Abre a câmara nativa
        }
    }
}