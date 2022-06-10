package myapp.uitest.puzzlejigsaw

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scores.*
import myapp.uitest.puzzlejigsaw.R
import myapp.uitest.puzzlejigsaw.adapter.NumbersAdapter

class ScoresActivity : AppCompatActivity(), NumbersAdapter.OnNumberInteract {
    lateinit var globalPreferences: GlobalPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        globalPreferences = GlobalPreferences(this)
        highScores.setShadowLayer(4f, 4f, 4f, Color.BLACK)
        Log.e("TAG", "onCreate: " + globalPreferences.score)
        var list = globalPreferences.score as List<Int>
        list = list.sortedBy { it }
        scores.adapter = NumbersAdapter(list, this)
    }

    override fun onNumberClicked(number: Int) {

    }
}