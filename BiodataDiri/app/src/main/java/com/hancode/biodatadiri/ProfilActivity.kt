package com.hancode.biodatadiri

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*


class ProfilActivity : AppCompatActivity() {

    companion object{
        val REQUEST_CODE =100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        //Memanggil Fungsi ambil data
        ambilData()
        //tombol klik listener ke tombol edit
        //jika tombol edit di klik maka akan menjalankan method nav ke edit profil
        btnEditNama.setOnClickListener { navigasiKeEditProfil() }
        //memberi click listener ke tomboll call
        btnCall.setOnClickListener { dialPhoneNumber(txtTelp.text.toString()) }
        btnMe.setOnClickListener{navigasiKeActivityAbout()}
    }
    private fun ambilData(){
        val bundle = intent.extras

        val nama = bundle!!.getString("nama")
        val gender = bundle.getString("gender")
        val email = bundle.getString("email")
        val telp = bundle.getString("telp")
        val alamat = bundle.getString("alamat")
        txtName.text = nama
        txtGender.text = gender
        txtEmail.text = email
        txtTelp.text = telp
        txtAddress.text = alamat
    }
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
// mengirimkan data dengan keyName "nama"
        val namaUser = txtName.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE)
    }
    //fungsi untuk menangkap hasil dari proses startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
//jika sukses maka data hasil edit kita tampikan ke extView txtName
                val result = data?.getStringExtra("nama")
                this.txtName.text = result
            }else{

                Toast.makeText(this, "Edit failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //fungsi untuk melakukan dial
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigasiKeActivityAbout(){
        val intent = Intent(this, AboutActivity::class.java)

        startActivity(intent)
    }

    }


