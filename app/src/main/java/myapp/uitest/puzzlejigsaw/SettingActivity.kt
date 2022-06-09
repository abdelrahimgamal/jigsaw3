package myapp.uitest.puzzlejigsaw

import MinutesBS
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*



class SettingActivity : AppCompatActivity(), MinutesBS.OnNumberInteract {

    lateinit var globalPreferences: GlobalPreferences
    lateinit var minutesBS: MinutesBS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        globalPreferences = GlobalPreferences(this)
        checkTimeStatus()

        timer.setOnClickListener {
            changeTimerStatus()
        }
        gameTime.text = "GAME TIME: ${globalPreferences.minutes} MIN"
        gameTime.setOnClickListener {
            minutesBS = MinutesBS(this)
            minutesBS.show(supportFragmentManager, "TAGG")
        }
        mainMenu.setOnClickListener { this.finish() }

    }

    private fun checkTimeStatus() {

        if (globalPreferences.timeStatus)
            timer.text = "TIMER: ON"
        else
            timer.text = "TIMER: OFF"

    }

    private fun changeTimerStatus() {
        globalPreferences.setTimeStatus(!globalPreferences.timeStatus)
        checkTimeStatus()

    }

    override fun onResume() {
        super.onResume()
        gameTime.text = "GAME TIME: ${globalPreferences.minutes} MIN"

    }

    override fun onNumberClicked(number: Int) {
        globalPreferences.setTimeMinutes(number)
        gameTime.text = "GAME TIME: ${globalPreferences.minutes} MIN"
        minutesBS.dismiss()


    }
}