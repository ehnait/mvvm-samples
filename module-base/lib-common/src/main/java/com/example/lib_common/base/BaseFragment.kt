package com.example.lib_common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
        startObserve()
    }

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)
    protected abstract fun startObserve()

    override fun onDetach() {
        //KeyboardUtils.hideSoftInput(requireActivity().window)
        super.onDetach()
    }
}