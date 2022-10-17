package com.example.lib_common.binding

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.example.lib_common.R
import com.example.lib_common.ext.dp
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

@BindingAdapter(
    "imageUrl",
    "placeholder",
    "failure",
    "cornersRadius",
    requireAll = false
)
fun SimpleDraweeView.loadImage(
    url: String,
    placeholderId: Int = R.drawable.ic_baseline_image_24,
    failureId: Int = R.drawable.ic_baseline_broken_image_24,
    radius: Float = 16f,
) {
    if (hierarchy == null) {
        hierarchy = GenericDraweeHierarchyBuilder(resources).build()
    }
    hierarchy.apply {
        setFailureImage(placeholderId, ScalingUtils.ScaleType.CENTER_CROP)
        setFailureImage(failureId, ScalingUtils.ScaleType.CENTER_CROP)
        actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
        roundingParams = RoundingParams().apply {
            if (radius >= 90f) {
                roundAsCircle = true
            } else {
                setCornersRadius(radius.dp)
            }
        }
    }
    controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(
            ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                //.setImageDecodeOptions(ImageDecodeOptions.newBuilder().setBitmapConfig(Bitmap.Config.RGB_565).build())
                //.setProgressiveRenderingEnabled(true) //渐进
                .build()
        )
        .setOldController(controller)
        .build()
}