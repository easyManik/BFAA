package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myintentapp.model.person

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //pengenalan textview dan button dari move for result
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil: $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //memperkenalkan button yang sudah ditambahkan di layout
        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        //untuk menambahkan event onclick pada button
        btnMoveActivity.setOnClickListener(this)

        //inisialisasi button yang ditambahkan
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        //kenalkan pada method onclick
        btnMoveWithDataActivity.setOnClickListener(this)

        //inisialisasi intent dengan parcelable
        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        //inisialisasi implisit intent
        val btnDialPhone: Button = findViewById(R.id.btn_dial_button)
        btnDialPhone.setOnClickListener(this)

        //inisialisasi move for result activity
        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    //eror merah pada (this) akan menambahkan method onclick
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_move_activity -> {
                //fungsi untuk koneksi pada hasil ketika tombol di klik
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_data -> {
                //object intent
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Easy Manik")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_activity_object -> {
                val Person = person(
                    "DicodingAcademy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, Person)
                startActivity(moveWithObjectIntent)
            }

            //action untuk implisit intent
            R.id.btn_dial_button ->{
                val phoneNumber = "081234567890"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}