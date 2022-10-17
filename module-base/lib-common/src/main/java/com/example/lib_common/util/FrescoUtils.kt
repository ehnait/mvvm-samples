package com.example.lib_common.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSources
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.image.CloseableBitmap
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequestBuilder
    /**
     * 使用Fresco获取指定uri的Bitmap
     */
    fun fetchDecodedImage(context:Context,url: String) : Bitmap?{
        val uri =  Uri.parse(url)
        val imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build()
        val dataSource = Fresco.getImagePipeline().fetchDecodedImage(imageRequest, context)
        try {
            val result = DataSources.waitForFinalResult<CloseableReference<CloseableImage>>(dataSource)
            try {
                if (result != null) {
                    return (result.get() as CloseableBitmap).underlyingBitmap
                }
            } finally {
                CloseableReference.closeSafely(result)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            dataSource.close();
        }
        return null
    }
