
package com.example.lib_common

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.example.lib_common.ext.application

internal class AppInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    application = context as Application
  }

  override fun dependencies() = emptyList<Class<Initializer<*>>>()
}
