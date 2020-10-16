package com.galaxy.common.utils

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

public class SaveImage2Gallery {


    /**
     * saveImage2Gallery
     * @param context Context
     * @param bitmap Bitmap?
     * @return Uri?
     */
    fun saveImage2Gallery(context: Context, bitmap: Bitmap?): Uri? {
        var uri: Uri? = null
        if (bitmap == null) {
            return uri
        }
        val fileName = "dyncUrlInV6"
//        val sdCardDir = File(Environment.getExternalStorageDirectory(), "share")
        val sdCardDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        var file = File(sdCardDir, fileName + ".jpg")
        // delete previous bitmap
        deleteImage(context, file)

        // save file
        try {
            file.createNewFile()
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos)
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
            file.delete()
        }

        // update media store
        if (file.exists()) {
            val title: String = fileName
            val description = ""
            val width = bitmap.width
            val height = bitmap.height
            uri = insertImage(context, file, title, description, width, height)
        }
        return uri
    }

    /**
     *
     * @param context Context
     * @param file File
     * @return Int
     */
    fun deleteImage(context: Context, file: File): Int {
        var count = 0
        val resolver = context.contentResolver
        try {
            // 删除旧文件
            count = resolver.delete(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                MediaStore.Images.ImageColumns.DATA + "=?", arrayOf(file.absolutePath)
            )
        } catch (e: java.lang.Exception) {
        }
        return count
    }

    /**
     * MediaStore提供示例
     * @param context Context
     * @param file File
     * @param title String?
     * @param description String?
     * @param width Int
     * @param height Int
     * @return Uri?
     */
    fun insertImage(
        context: Context,
        file: File,
        title: String?,
        description: String?,
        width: Int,
        height: Int
    ): Uri? {
        var uri: Uri
        val values = ContentValues()
        val cr = context.contentResolver
        // insert to media store
        val time = file.lastModified()
        // media provider uses seconds for DATE_MODIFIED and DATE_ADDED, but milliseconds
        // for DATE_TAKEN
        val dateSeconds = time / 1000
        // mime-type
        val mimeType = "image/jpeg"
        values.put(MediaStore.Images.ImageColumns.TITLE, title)
        values.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, title)
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, description)
        values.put(MediaStore.Images.ImageColumns.MIME_TYPE, mimeType)
        values.put(MediaStore.Images.ImageColumns.WIDTH, width)
        values.put(MediaStore.Images.ImageColumns.HEIGHT, height)
        values.put(MediaStore.Images.ImageColumns.SIZE, file.length())
        values.put(MediaStore.Images.ImageColumns.DATA, file.absolutePath)
        values.put(MediaStore.Images.ImageColumns.DATE_TAKEN, time)
        values.put(MediaStore.Images.ImageColumns.DATE_ADDED, dateSeconds)
        values.put(MediaStore.Images.ImageColumns.DATE_MODIFIED, dateSeconds)
        uri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        // generate thumbnail
        val id = ContentUris.parseId(uri)
        // Wait until MINI_KIND thumbnail is generated.
        val miniThumb = MediaStore.Images.Thumbnails.getThumbnail(
            cr, id,
            MediaStore.Images.Thumbnails.MINI_KIND, null
        )
        miniThumb?.recycle()
        return uri
    }
}