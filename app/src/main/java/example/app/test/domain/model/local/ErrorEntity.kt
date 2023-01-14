package example.app.test.domain.model.local

enum class ErrorEntity(val id: Int, val message: String) {
    TokenFailed(401, "برای ادامه دوباره وارد شوید"),
    Network(
        400,
        "خطای درون شبکه ای لطفا چند دقیقه دیگر امتحان نمایید ."
    ),
    NotFound(
        404,
        "درخواست شما با مشخصات یاد شده یافت نشد لطفا اطلاعات را اصلاح نمایید ."
    ),
    AccessDenied(403, "عدم دسترسی مجاز به اطلاعات درخواست شده "),
    ServiceUnavailable(500, "خطای درون شبکه ای لطفا چند لحظه بعد دوباره تلاش نمایید"),
    Unknown(
        300,
        "خطای ناشناخته چند دقیقه بعد دوباره امتحان کنید ."
    )
}
