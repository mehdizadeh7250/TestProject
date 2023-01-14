package example.app.test.utils

class ViewProgressHandler(
    private val onShowProgress: (() -> Unit)?,
    private val onDismissProgress: (() -> Unit)?
) : ProgressHandler {

    override fun showProgress() {
        onShowProgress?.invoke()
    }

    override fun dismissProgress() {
        onDismissProgress?.invoke()
    }
}
