package com.example.assignmentnicolas

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView



class camera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)


        val camerabutton =findViewById<Button>(R.id.camerabutton)
        val gallerybutton=findViewById<Button>(R.id.gallery)
        val pictureview=findViewById<ImageView>(R.id.pictureview)

        camerabutton.setOnClickListener {
            var intent = Intent (MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)

        }
        gallerybutton.setOnClickListener {
            uploadImageGallery()
        }

    }

    private fun uploadImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val imageView = findViewById<ImageView>(R.id.pictureview)
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(takenImage)
        } else if (requestCode == 100 && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}