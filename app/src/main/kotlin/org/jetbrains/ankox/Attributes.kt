package org.jetbrains.ankox

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import org.jetbrains.anko.AnkoContext

/**
 * Anko extensions to allow using R.attr.* values in the layouts
 *
 * Created by drew on 6/22/16.
 */

internal fun Context.resolveAttr(attribute: Int): TypedValue {
    val outValue = TypedValue()
    this.theme.resolveAttribute(attribute, outValue, true);
    return outValue;
}

fun Context.dimenAttr(attribute: Int): Int {
    return TypedValue.complexToDimensionPixelSize(resolveAttr(attribute).data, this.resources.displayMetrics)
}

fun Context.colorAttr(attribute: Int): Int {
    val outValue = TypedValue()
    return if (this.theme.resolveAttribute(attribute, outValue, true)) {
        if (outValue.type >= TypedValue.TYPE_FIRST_INT && outValue.type <= TypedValue.TYPE_LAST_INT) {
            outValue.data;
        } else if (outValue.type == TypedValue.TYPE_STRING) {
            ContextCompat.getColor(this, outValue.resourceId);
        } else {
            throw IllegalArgumentException("Attribute has no data");
        }
    } else {
        throw AssertionError("Failed to resolve attribute");
    }
}

inline fun AnkoContext<*>.dimenAttr(attribute: Int): Int = ctx.dimenAttr(attribute)
inline fun AnkoContext<*>.colorAttr(attribute: Int): Int = ctx.colorAttr(attribute)
