
package com.example.lib_common.ext

import android.os.Environment

inline val cacheDirPath: String
  get() = if (isExternalStorageWritable || !isExternalStorageRemovable)
    externalCacheDirPath.orEmpty()
  else
    internalCacheDirPath

inline val externalCacheDirPath: String?
  get() = application.externalCacheDir?.absolutePath

inline val externalFilesDirPath: String?
  get() = application.getExternalFilesDir(null)?.absolutePath

inline val externalPicturesDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath

inline val externalMoviesDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath

inline val externalDownloadsDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath

inline val externalDocumentsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_DOCUMENTS)?.absolutePath

inline val externalMusicDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath

inline val externalPodcastsDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_PODCASTS)?.absolutePath

inline val externalRingtonesDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_RINGTONES)?.absolutePath

inline val externalAlarmsDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_ALARMS)?.absolutePath

inline val externalNotificationsDirPath: String?
  get() = application.getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS)?.absolutePath

inline val internalCacheDirPath: String
  get() = application.cacheDir.absolutePath

inline val internalFileDirPath: String
  get() = application.filesDir.absolutePath

inline val internalPicturesDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_PICTURES)?.absolutePath

inline val internalMoviesDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_MOVIES)?.absolutePath

inline val internalDownloadsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_DOWNLOADS)?.absolutePath

inline val internalDocumentsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_DOCUMENTS)?.absolutePath

inline val internalMusicDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_MUSIC)?.absolutePath

inline val internalPodcastsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_PODCASTS)?.absolutePath

inline val internalRingtonesDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_RINGTONES)?.absolutePath

inline val internalAlarmsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_ALARMS)?.absolutePath

inline val internalNotificationsDirPath: String?
  get() = application.getFileStreamPath(Environment.DIRECTORY_NOTIFICATIONS)?.absolutePath

/**
 * Checks if a volume containing external storage is available for read and write.
 */
inline val isExternalStorageWritable: Boolean
  get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * Checks if a volume containing external storage is available to at least read.
 */
inline val isExternalStorageReadable: Boolean
  get() = Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)

inline val isExternalStorageRemovable: Boolean
  get() = Environment.isExternalStorageRemovable()
