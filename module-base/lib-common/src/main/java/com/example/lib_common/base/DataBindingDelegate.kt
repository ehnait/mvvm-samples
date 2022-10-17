package com.example.lib_common.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

/**
 * Retrieves a data binding handle in a Fragment. The property should not be accessed before
 * [Fragment.onViewCreated].
 *
 * ```
 *     private val binding by dataBindings(HomeFragmentBinding::bind)
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         // Use the binding.
 *     }
 * ```
 */
inline fun <reified BindingT : ViewDataBinding> Fragment.dataBindings(
    crossinline bind: (View) -> BindingT
) = object : Lazy<BindingT> {

    private var cached: BindingT? = null

    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            cached = null
        }
    }

    override val value: BindingT
        get() = cached ?: bind(requireView()).also {
            it.lifecycleOwner = viewLifecycleOwner
            viewLifecycleOwner.lifecycle.addObserver(observer)
            cached = it
        }

    override fun isInitialized() = cached != null
}
