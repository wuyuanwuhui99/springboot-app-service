package com.player.music.controller;

        import com.player.common.entity.ResultEntity;
        import com.player.common.utils.HttpUtils;
        import com.player.music.service.IMyMusicService;
        import com.player.music.service.IQQMusicService;
        import feign.Param;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpServletRequest;

@RestController

@RequestMapping("/service")
public class MyMusicController {
    @Autowired
    private IMyMusicService myMusicService;

    @ApiOperation("获取搜索框默认推荐音乐")
    @GetMapping("/myMusic/getKeywordMusic")
    public ResultEntity getKeywordMusic(HttpServletRequest request) {
        return myMusicService.getKeywordMusic(HttpUtils.getPath(request));
    }

    @ApiOperation("获取音乐分类")
    @GetMapping("/myMusic/getMusicClassify")
    public ResultEntity getClassifyMusic(HttpServletRequest request) {
        return myMusicService.getMusicClassify(HttpUtils.getPath(request));
    }

    @ApiOperation("获取推荐音乐列表")
    @GetMapping("/myMusic/getMusicByClassifyName")
    public ResultEntity getMusicByClassifyName(HttpServletRequest request, String classifyName, int pageNum, int pageSize) {
        return myMusicService.getMusicByClassifyName(HttpUtils.getPath(request),classifyName, pageNum, pageSize);
    }
}
