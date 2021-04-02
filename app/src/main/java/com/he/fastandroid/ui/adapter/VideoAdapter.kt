package com.he.fastandroid.ui.adapter

import android.view.View
import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.he.fastandroid.R
import com.he.fastandroid.entity.VideoList
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer


/**
 * Created by Liam.Zheng on 3/24/21
 *
 * Des:
 */
class VideoAdapter : BaseQuickAdapter<VideoList.VideoItem, BaseViewHolder>(R.layout.item_video) {
    companion object {
        const val TAG = "VideoAdapter"
    }

    override fun convert(holder: BaseViewHolder, item: VideoList.VideoItem) {
        holder.setText(R.id.item_video_title, item.title.toString())
        val videoPlayer = holder.getView<StandardGSYVideoPlayer>(R.id.video_player)
        videoPlayer.setUpLazy(item.mp4_url.toString(), false, null, null, "")

        //增加title
        videoPlayer.titleTextView.visibility = View.GONE
        //设置返回键
        videoPlayer.backButton.visibility = View.GONE
        //增加封面
        videoPlayer.thumbImageViewLayout.visibility = View.VISIBLE
        val imageView = ImageView(context).apply {
            this.scaleType = ImageView.ScaleType.CENTER_CROP
            this.load(item.cover)
        }
        videoPlayer.thumbImageView = imageView

        //设置全屏按键功能
        videoPlayer.fullscreenButton.setOnClickListener {
            videoPlayer.startWindowFullscreen(context, false, true)
        }
        //防止错位设置
        videoPlayer.tag = TAG
        videoPlayer.playPosition = holder.layoutPosition;
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        videoPlayer.isAutoFullWithSize = true;
        //音频焦点冲突时是否释放
        videoPlayer.isReleaseWhenLossAudio = false;
        //全屏动画
        videoPlayer.isShowFullAnimation = true;
        //小屏时不触摸滑动
        videoPlayer.setIsTouchWiget(false);
    }
}