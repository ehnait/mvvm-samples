package com.galaxy.common.logger;

import timber.log.Timber;

/**
 * 示例：
 * Timber.plant(new ConstTagTree().setTag("ConstTag"));
 * Timber.v("watch the tag"); //tag == "ConstTag"
 * Timber.v("watch the tag haha");//tag == "ConstTag"
 * Timber.v("watch the tag hehe");//tag == "ConstTag"
 */
public class ConstTagTree extends Timber.DebugTree {

    private static final int CALL_STACK_INDEX = 6;
    private String mConstTag;

    public ConstTagTree setTag(final String tag) {
        mConstTag = tag;
        return this;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        //tag was originally come from Timber.tag(),so we just ignore.
        StackTraceElement element = callingElement();
        String newTag = mConstTag != null ? mConstTag : getCallingClassName(element);
        newTag += ":" + element.getLineNumber();
        super.log(priority, newTag, message, t);
    }

    private StackTraceElement callingElement() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        return stackTrace[CALL_STACK_INDEX];
    }

    private String getCallingClassName(StackTraceElement element) {
        return createStackElementTag(element);
    }
}