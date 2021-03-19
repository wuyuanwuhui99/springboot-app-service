package com.player.music.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.player.common.entity.ResultEntity;
import com.player.common.utils.HttpUtils;
import com.player.music.service.IQQMusicService;
import com.player.music.utils.RedisUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class QQMusicService implements IQQMusicService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author: wuwenqiang
     * @methodsName: getDiscList
     * @description: 获取推荐音乐数据, 请求地地址
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getDiscList() {
        String url = "https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_diss_by_tag.fcg?g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=json&platform=yqq&hostUin=0&sin=0&ein=29&sortId=5&needNewCode=0&categoryId=10000000";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result =  HttpUtils.doGet(url+"&rnd="+Math.random());
            return RedisUitls.getRedis(redisTemplate,url, result, "获取推荐音乐数据失败");
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getLyric
     * @description: 根据歌曲mid查询歌词
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getLyric(String songmid) {
        String url = "https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg?g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=json&songmid="+songmid+"&platform=yqq&hostUin=0&needNewCode=0&categoryId=10000000";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(StringUtils.isEmpty(result)){
            result = HttpUtils.doGet(url+"&pcachetime=" + System.currentTimeMillis());
            JSONObject jsonObject = JSON.parseObject(result);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getSingerList
     * @description: 获取歌手列表
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getSingerList() {
        String url = "https://c.y.qq.com/v8/fcg-bin/v8.fcg?jsonpCallback=getSingerList&g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&channel=singer&page=list&key=all_all_all&pagesize=100&pagenum=1&hostUin=0&needNewCode=0&platform=yqq";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result =  result.replaceAll("getSingerList\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getHotKey
     * @description: 获取热门推荐
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getHotKey() {
        String url = "https://c.y.qq.com/splcloud/fcgi-bin/gethotkey.fcg?g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&uin=0&needNewCode=1&platform=h5&jsonpCallback=getHotKey";
        String result = (String) redisTemplate.opsForValue().get(url);        
        if(StringUtils.isEmpty(result)){
            result = HttpUtils.doGet(url);
            result =  result.replaceAll("getHotKey\\(|\\)$", "");
            return RedisUitls.getRedis(redisTemplate,url, result, "获取推荐数据失败");
        }
        return JSON.parseObject(result, ResultEntity.class);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: search
     * @description: 搜索
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity search(String catZhida,String p,String n, String w) {
        String url = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.center&searchid=37276201631470540&t=0&aggr=1&cr=1&lossless=0&flag_qc=0&loginUin=0&hostUin=0&platform=yqq&needNewCode=1&jsonpCallback=search&catZhida="+catZhida+"&p="+p+"&n="+n+"&w="+w;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(StringUtils.isEmpty(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("search\\(|\\)$", "");
            return RedisUitls.getRedis(redisTemplate,url,result,"获取搜索数据失败");
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getSingerDetail
     * @description: 获取歌手的歌曲
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getSingerDetail(String singermid) {
        String url = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?jsonpCallback=getSingerDetail&g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&hostUin=0&needNewCode=0&platform=yqq&order=listen&begin=0&num=80&songstatus=1&singermid=" + singermid;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(StringUtils.isEmpty(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getSingerDetail\\(|\\)$", "");
            return RedisUitls.getRedis(redisTemplate,url,result,"获取歌手的歌曲");
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    /**
     * @author: getSingerDetail
     * @methodsName: getRecommend
     * @description: 获取推荐列表
     * @return: String
     * @date: 2020-08-02 22:25
     */
    @Override
    public ResultEntity getRecommend() {
        String url = "https://c.y.qq.com/musichall/fcgi-bin/fcg_yqqhomepagerecommend.fcg?jsonpCallback=getRecommend&g_tk=5381&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&platform=h5&uin=0&needNewCode=1";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getRecommend\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    @Override
    public ResultEntity getSongList(String disstid) {
        String url = "https://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg?jsonpCallback=getSongList&type=1&json=1&utf8=1&onlysong=0&disstid=" + disstid + "&g_tk=5381&loginUin=0&hostUin=0&platform=yqq&needNewCode=0&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getSongList\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    @Override
    public ResultEntity getTopList() {
        String url = "https://c.y.qq.com/v8/fcg-bin/fcg_myqq_toplist.fcg?jsonpCallback=getTopList&g_tk=5381&loginUin=0&hostUin=0&platform=yqq&needNewCode=0&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&uin=0&needNewCode=1&platform=h5";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getTopList\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    @Override
    public ResultEntity getMusicList(String topid) {
        String url = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_toplist_cp.fcg?jsonpCallback=getMusicList&g_tk=5381&loginUin=0&hostUin=0&platform=yqq&needNewCode=0&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&topid=" + topid + "&needNewCode=1&uin=0&tpl=3&page=detail&type=top&platform=h5&needNewCode=1";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result =result.replaceAll("getMusicList\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    @Override
    public ResultEntity getAudioUrl(String mid, String filename) {
        String url = "https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?jsonpCallback=getAudioUrl&g_tk=5381&loginUin=0&hostUin=0&platform=yqq&needNewCode=0&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&cid=205361747&uin=0&songmid=" + mid + "&filename=" + filename + "&guid=3397254710";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getAudioUrl\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }

    @Override
    public ResultEntity getSingleSong(String mid) {
        String url = "https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?jsonpCallback=getSingleSong&-=getplaysongvkey5045356706297506&g_tk=5381&loginUin=275018723&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=%7B%22req%22:%7B%22module%22:%22CDN.SrfCdnDispatchServer%22,%22method%22:%22GetCdnDispatch%22,%22param%22:%7B%22guid%22:%222807659112%22,%22calltype%22:0,%22userip%22:%22%22%7D%7D,%22req_0%22:%7B%22module%22:%22vkey.GetVkeyServer%22,%22method%22:%22CgiGetVkey%22,%22param%22:%7B%22guid%22:%222807659112%22,%22songmid%22:[%22" + mid + "%22],%22songtype%22:[0],%22uin%22:%22275018723%22,%22loginflag%22:1,%22platform%22:%2220%22%7D%7D,%22comm%22:%7B%22uin%22:275018723,%22format%22:%22json%22,%22ct%22:24,%22cv%22:0%7D%7D";
        String result = (String) redisTemplate.opsForValue().get(url);
        if(result == null || !"".equals(result)){
            result = HttpUtils.doGet(url);
            result = result.replaceAll("getSingleSong\\(|\\)$", "");
            redisTemplate.opsForValue().set(url,result,1, TimeUnit.DAYS);
        }
        return JSON.parseObject(result,ResultEntity.class);
    }
}
