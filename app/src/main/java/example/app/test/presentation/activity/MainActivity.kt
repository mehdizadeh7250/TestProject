package example.app.test.presentation.activity

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import example.app.test.R
import example.app.test.databinding.ActivityMainBinding
import example.app.test.presentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
