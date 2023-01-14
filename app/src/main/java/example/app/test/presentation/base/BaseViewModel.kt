package example.app.test.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import example.app.test.R

open class BaseViewModel : ViewModel() {
    var scope = viewModelScope
    var errorLiveData = MutableLiveData<String>()

    val navBuilder = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in).setExitAnim(R.anim.slide_out)
        .setPopEnterAnim(R.anim.slide_in).setPopExitAnim(R.anim.slide_out)
}
