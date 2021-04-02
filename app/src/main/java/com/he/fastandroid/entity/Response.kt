package com.he.fastandroid.entity
import androidx.annotation.Keep


/**
 * Created by Liam.Zheng on 2021/1/22
 *
 * Des:
 */
@Keep
open class BaseResponse<Data> {
    val code: Int? = null
    val msg: String? = null
    val data: Data? = null
    fun isOk(): Boolean {
        return code == 200 || code == 1
    }
}
@Keep
data class RandPwdResponse<Data>(val password: String) : BaseResponse<Data>()
@Keep
data class RandPortraitResponse<Data>(val pic_url: String) : BaseResponse<Data>()
@Keep
class VideoList : ArrayList<VideoList.VideoItem>(){
    @Keep
    data class VideoItem(
        val cover: String? = null, // http://vimg3.ws.126.net/image/snapshot/2017/5/J/F/VCKOETEJF.jpg
        val description: String? = null, // 小清新美女爱跳舞，夜里房间优雅独舞
        val length: Int? = null, // 147
        val m3u8Hd_url: String? = null, // http://flv.bn.netease.com/videolib3/1705/30/aYNXb0812/HD/movie_index.m3u8
        val m3u8_url: String? = null, // http://flv.bn.netease.com/videolib3/1705/30/aYNXb0812/SD/movie_index.m3u8
        val mp4Hd_url: String? = null, // http://flv2.bn.netease.com/videolib3/1705/30/aYNXb0812/HD/aYNXb0812-mobile.mp4
        val mp4_url: String? = null, // http://flv2.bn.netease.com/videolib3/1705/30/aYNXb0812/SD/aYNXb0812-mobile.mp4
        val playCount: Int? = null, // 0
        val playersize: Int? = null, // 1
        val ptime: String? = null, // 2017-05-30 10:21:30
        val replyBoard: String? = null, // video_bbs
        val replyCount: Int? = null, // 0
        val replyid: String? = null, // BKOD7PO4050835RB
        val sectiontitle: String? = null,
        val sizeHD: Int? = null, // 14700
        val sizeSD: Int? = null, // 11025
        val sizeSHD: Int? = null, // 22050
        val title: String? = null, // 小清新美女爱跳舞，夜里房间优雅独舞
        val topicDesc: String? = null, // 让生活慢下来，品味阅读，享受人生，活到老学到老，教育大家，心灵美好，我们一起携手共创美好世界。
        val topicImg: String? = null, // http://vimg3.ws.126.net/image/snapshot/2017/5/9/B/VCKI1SH9B.jpg
        val topicName: String? = null, // 韬哥学霸哥
        val topicSid: String? = null, // VCKI1SH8S
        val vid: String? = null, // VBKOD7PO4
        val videoTopic: VideoTopic? = null,
        val videosource: String? = null, // 新媒体
        val votecount: Int? = null // 0
    ) {
        @Keep
        data class VideoTopic(
            val alias: String? = null, // 多彩人生，你我共同编织
            val ename: String? = null, // T1494085453258
            val tid: String? = null, // T1494085453258
            val tname: String? = null, // 韬哥学霸哥
            val topic_icons: String? = null // http://dingyue.nosdn.127.net/tHYrjgCtrdmQP3Nvygaa75m3BMOxdY5ZJ8RVPGgMj9Njt1494085452329.jpg
        )
    }
}
@Keep
class NewsList : ArrayList<NewsList.NewsItem>(){
    @Keep
    data class NewsItem(
        val alias: String? = null, // Military
        val boardid: String? = null, // news_junshi_bbs
        val cid: String? = null, // C1348647991705
        val commentStatus: Int? = null, // 1
        val daynum: String? = null, // 18709
        val digest: String? = null, // 【环球网军事报道】俄罗斯卫星通讯社3月23日报道，根据英国军队现代化战略，英国军队的人数将减少一万人。报道称，英国国防部于3月22日公布这一战略，它将介绍英国将
        val docid: String? = null, // G5P4FHL6000181KT
        val downTimes: Int? = null, // 0
        val ename: String? = null, // junshi
        val extraShowFields: String? = null,
        val hasAD: Int? = null, // 1
        val hasCover: Boolean? = null, // false
        val hasHead: Int? = null, // 1
        val hasIcon: Boolean? = null, // true
        val hasImg: Int? = null, // 1
        val imgsrc: String? = null, // http://cms-bucket.ws.126.net/2021/0323/7ebb751cj00qqeiqe0021c000rs00f1c.jpg
        val lmodify: String? = null, // 2021-03-23 10:55:03
        val ltitle: String? = null, // 外媒：英军将削减上万兵力，转向无人机和其它技术
        val mtime: String? = null, // 2021-03-23 10:55:03
        val order: Int? = null, // 1
        val pixel: String? = null, // 1000*541
        val postid: String? = null, // G5P4FHL6000181KT
        val priority: Int? = null, // 80
        val ptime: String? = null, // 2021-03-23 10:54:47
        val quality: Int? = null, // 80
        val replyCount: Int? = null, // 0
        val riskLevel: Int? = null, // 1
        val source: String? = null, // 环球网
        val sourceId: String? = null, // T1348648141035
        val subtitle: String? = null,
        val template: String? = null, // recommend
        val title: String? = null, // 外媒：英军将削减上万兵力，转向无人机和其它技术
        val tname: String? = null, // 军事
        val topic_background: String? = null, // http://cms-bucket.ws.126.net/2020/0422/1e44993bp00q966r20011c000u000a0c.png
        val topicid: String? = null, // 000181KT
        val upTimes: Int? = null, // 0
        val url: String? = null, // https://3g.163.com/news/article/G5P4FHL6000181KT.html
        val url_3w: String? = null,
        val votecount: Int? = null // 0
    )
}