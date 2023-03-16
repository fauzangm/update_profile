package com.example.updateprofile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.updateprofile.Notification.showToast
import com.example.updateprofile.databinding.ActivityEditProfileBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private var jenisKelamin = false
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var binding : ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        initAction()
    }

    private fun initAction() {
        binding.btnUpdateProfile.setOnClickListener {
            binding.btnUpdateProfile.startAnimation()
            if (binding.etNamaLengkap.text!!.isBlank()){
                binding.etNamaLengkap.error = "Wajib terisi"
                showToast(this, "Harap Mengisi Form Nama")
                stopAnimatioin()
                return@setOnClickListener
            }
            if (binding.etAlamat.text!!.isBlank()){
                binding.etAlamat.error = "Wajib terisi"
                showToast(this, "Harap Mengisi Form Alamat")
                stopAnimatioin()
                return@setOnClickListener
            }
            if (binding.etUmur.text!!.isBlank()){
                binding.etUmur.error = "Wajib terisi"
                showToast(this, "Harap Mengisi Form Umur")
                stopAnimatioin()
                return@setOnClickListener
            }
            if (binding.etMail.text!!.isBlank()){
                binding.etMail.error = "Wajib terisi"
                showToast(this, "Harap Mengisi Form Email")
                stopAnimatioin()
                return@setOnClickListener
            }
            if (binding.etNoTelp.text!!.isBlank()){
                binding.etNoTelp.error = "Wajib terisi"
                showToast(this, "Harap Mengisi Form No Telpon")
                stopAnimatioin()
                return@setOnClickListener
            }

            val rgJenisKelamin = binding.rgJenisKelamin
            val cekrgJenisKelamin = rgJenisKelamin.checkedRadioButtonId
            val edtrgJenisKelamin = findViewById<RadioButton>(cekrgJenisKelamin)
            if (edtrgJenisKelamin != null) {
                Log.e("rgJenisKelamin", "${edtrgJenisKelamin.text}")
                jenisKelamin = edtrgJenisKelamin.text.toString() != "Perempuan"
            } else {
                Toast.makeText(
                    applicationContext, "Harap Mengisi Form Jenis Kelamin",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            handler.postDelayed({
                initialSucces()
            },2000)

        }
    }

    private fun stopAnimatioin() {
        binding.btnUpdateProfile.revertAnimation {
            binding.btnUpdateProfile.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.bg_btn_accent_rounded
            )
        }
    }

    private fun initialSucces() {
        stopAnimatioin()
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog_succes)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val imgClose: ImageView = dialog.findViewById(R.id.imgCloseKuesioner)
        val btnClose: Button = dialog.findViewById(R.id.btnClose)
        dialog.show()

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        imgClose.setOnClickListener {
            dialog.dismiss()
        }
    }
}