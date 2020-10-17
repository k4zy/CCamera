package app.kazy.ccamera.system

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kazy.ccamera.R
import app.kazy.ccamera.scene.main.MainFragment

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment()).commit()
    }
}
