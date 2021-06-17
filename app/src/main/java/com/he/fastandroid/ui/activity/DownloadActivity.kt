package com.he.fastandroid.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.he.common.base.BaseActivity
import com.he.fastandroid.R
import com.he.http.downloader.DownloadListener
import com.he.http.downloader.DownloadManager
import com.he.http.downloader.DownloadStatus
import kotlinx.android.synthetic.main.activity_download.*

/**
 * Created by Liam.Zheng on 2020/10/16
 *
 * Des:
 */
class DownloadActivity : BaseActivity() {
    private companion object {
        const val REQUEST_PERMISSIONS_CODE = 0x0001
    }

    private val downloadStatus by lazy {
        MutableLiveData<DownloadStatus>().apply {
            value = DownloadStatus.READY_TO_DOWNLOAD
        }
    }

    override fun getlayoutId(): Int {
        return R.layout.activity_download
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    override fun onStart() {
        super.onStart()
        download.setOnClickListener {
            val needPermissions = listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
            ).filter {
                ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }

            if (needPermissions.isEmpty()) {
                startDownloadTest()
            } else {
                ActivityCompat.requestPermissions(this, needPermissions.toTypedArray(), REQUEST_PERMISSIONS_CODE)
            }
        }

        download.setOnLongClickListener {
            DownloadManager.cancelDownload()
            true
        }

        downloadStatus.observe(this) {
            when (downloadStatus.value) {
                DownloadStatus.DOWNLOADING -> {
                    pause_resume_down.visibility = View.VISIBLE
                    pause_resume_down.text = "暂停下载"
                    pause_resume_down.setOnClickListener {
                        println("开始暂停下载")
                        DownloadManager.pauseDownload()
                    }
                }
                DownloadStatus.DOWNLOAD_PAUSE -> {
                    pause_resume_down.visibility = View.VISIBLE
                    pause_resume_down.text = "继续下载"
                    pause_resume_down.setOnClickListener {
                        DownloadManager.resumeDownLoad()
                    }
                }
                else -> {
                    pause_resume_down.visibility = View.GONE
                }
            }
        }
    }

    private fun startDownloadTest() {
        val downloadUrl = "http://down.qq.com/qqweb/QQ_1/android_apk/Androidqq_8.4.10.4875_537065980.apk"
        val externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val saveFilePath = "$externalFilesDir/测试.apk"

        DownloadManager.startDownLoad(
            this.application, downloadUrl,
            saveFilePath = saveFilePath,
            downloadListener = object : DownloadListener() {
                override fun downloadIng(progress: String) {
                    this@DownloadActivity.displayInfo.text = "下载中$progress%"

                    downloadStatus.value = DownloadStatus.DOWNLOADING
                }

                override fun downloadError(error: Throwable?) {
                    this@DownloadActivity.displayInfo.text = "下载出错:${error?.message}"

                    downloadStatus.value = DownloadStatus.DOWNLOAD_ERROR
                }

                override fun downloadComplete(downLoadFilePath: String) {
                    this@DownloadActivity.displayInfo.text = "下载完成--->$downLoadFilePath"

                    downloadStatus.value = DownloadStatus.DOWNLOAD_COMPLETE
                }

                override fun downloadPause() {
                    this@DownloadActivity.displayInfo.text = "下载暂停"

                    downloadStatus.value = DownloadStatus.DOWNLOAD_PAUSE
                }

                override fun downloadCancel() {
                    this@DownloadActivity.displayInfo.text = "下载取消"

                    downloadStatus.value = DownloadStatus.DOWNLOAD_CANCEL
                }
            }
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            val noGrantResult = grantResults.filter { it != PackageManager.PERMISSION_GRANTED }
            if (noGrantResult.isEmpty()) {
                startDownloadTest()
            } else {
                val noGrantPermissionIndex = mutableListOf<Int>()
                grantResults.forEachIndexed { index, result ->
                    if (result in noGrantResult) noGrantPermissionIndex.add(index)
                }
                val noGrantPermissions = permissions.filterIndexed { index, _ ->
                    index in noGrantPermissionIndex
                }
                Toast.makeText(this, "$noGrantPermissions 这些权限未授予", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
