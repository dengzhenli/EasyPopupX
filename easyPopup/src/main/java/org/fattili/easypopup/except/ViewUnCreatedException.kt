package org.fattili.easypopup.except

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception

/**
 *  2021/3/30
 * @author dengzhenli
 */
class ViewUnCreatedException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

    constructor(exception: Exception) : super(exception.message, exception.cause)

    @RequiresApi(Build.VERSION_CODES.N)
    constructor(
        message: String?,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}