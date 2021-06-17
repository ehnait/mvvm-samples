package com.he.http.downloader


internal interface OnDownloadStatusListener {

    fun downloadStatusChange(status: DownloadStatus, error: Throwable? = null)
}