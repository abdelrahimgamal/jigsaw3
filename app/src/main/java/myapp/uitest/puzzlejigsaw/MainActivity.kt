package myapp.uitest.puzzlejigsaw

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    final val link =
        "https://docs.google.com/document/d/1CW8PSHJzZ49hbgKQj39n7weaWTuIq2P7gmMDF_QRqew"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accept.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
        decline.setOnClickListener { this.finish() }
        mainText.setShadowLayer(4f, 4f, 4f, Color.BLACK)

        val text = "Please accept our privacy policy to use an app"

        val ss = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(browserIntent)
            }
        }
        ss.setSpan(clickableSpan, 18, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        policy.setText(ss);
        policy.setMovementMethod(LinkMovementMethod.getInstance());

    }
}