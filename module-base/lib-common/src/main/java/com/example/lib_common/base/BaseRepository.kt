package com.example.lib_common.base

import com.example.lib_common.http.romote.CommonService
import javax.inject.Inject

open class BaseRepository @Inject constructor() {
    @Inject
    lateinit var commonService: CommonService
}
