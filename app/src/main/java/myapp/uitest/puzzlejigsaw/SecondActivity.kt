package myapp.uitest.puzzlejigsaw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setting.setOnClickListener { startActivity(Intent(this, SettingActivity::class.java)) }
        start.setOnClickListener { startActivity(Intent(this, PuzzleActivity::class.java)) }

    }
}