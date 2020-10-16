package com.galaxy.common.logger

import org.jetbrains.annotations.Nullable
import timber.log.Timber

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 * Timber.plant(new ConstTagTree().setTag("ConstTag"));
 * Timber.v("watch the tag"); //tag == "ConstTag"
 * Timber.v("watch the tag haha");//tag == "ConstTag"
 * Timber.v("watch the tag hehe");//tag == "ConstTag"
 */

class ConstTagTree : Timber.DebugTree() {
    companion object {
        private const val CALL_STACK_INDEX = 6
    }

    private var mConstTag: String? = null

    fun setTag(tag: String): ConstTagTree {
        mConstTag = tag
        return this
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        //tag was originally come from Timber.tag(),so we just ignore.
        val element = callingElement()
        var newTag = if (mConstTag != null) mConstTag else getCallingClassName(element)
        newTag += ":" + element.lineNumber
        super.log(priority, tag, message, t)
    }

    private fun callingElement(): StackTraceElement {
        val stackTrace =
            Throwable().stackTrace
        check(stackTrace.size > CALL_STACK_INDEX) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
        return stackTrace[CALL_STACK_INDEX]
    }

    private fun getCallingClassName(element: StackTraceElement): @Nullable String? {
        return createStackElementTag(element)
    }
}