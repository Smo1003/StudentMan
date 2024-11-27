package vn.edu.hust.studentman

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var btnCancel: Button
    private lateinit var btnOK: Button

    private lateinit var etName: EditText
    private lateinit var etId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        init()
    }

    private fun init() {
        btnCancel = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            finish()
        }

        btnOK = findViewById(R.id.btn_ok)
        btnOK.setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", etName.text.toString())
            intent.putExtra("id", etId.text.toString())

            val pos : Int = this.intent.getIntExtra("pos", -1)
            if (pos != -1) intent.putExtra("pos", pos)

            setResult(Activity.RESULT_OK, intent)

            finish()
        }

        etName = findViewById(R.id.et_name)
        if (intent.getStringExtra("name") != null) {
            etName.setText(intent.getStringExtra("name"))
        }

        etId = findViewById(R.id.et_id)
        if (intent.getStringExtra("id") != null) {
            etId.setText(intent.getStringExtra("id"))
        }
    }
}