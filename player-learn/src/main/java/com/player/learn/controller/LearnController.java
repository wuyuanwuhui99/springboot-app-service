package com.player.learn.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.OperationType;
import com.player.learn.entity.ChapterLogEntity;
import com.player.learn.entity.CourseLogEntity;
import com.player.learn.service.IChapterService;
import com.player.learn.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/service/learn")
@Api(value = "查询课程和章节", description = "查询课程和章节")
public class LearnController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IChapterService chapterService;

    /**
     * @author: wuwenqiang
     * @methodsName: findAllByClassifyGroup
     * @description: 查询所有大分类
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @OperLog(message = "查询所有大分类", operation = OperationType.QUERY)
    @ApiOperation("查询所有大分类")
    @GetMapping("/findAllByClassifyGroup")
    public ResultEntity findAllByClassifyGroup() {
        return courseService.findAllByClassifyGroup();
    }

    /**
     * @param :大分类
     * @param :sort排序规则
     * @author: wuwenqiang
     * @methodsName: findAllByClassify
     * @description: 根据大分类查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @OperLog(message = "根据大分类查询课程", operation = OperationType.QUERY)
    @ApiOperation("根据大分类查询课程")
    @GetMapping("/findAllByClassify")
    public ResultEntity findAllByClassify(@RequestParam("classify") String classify,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        return courseService.findAllByClassify(classify,pageNum,pageSize);
    }

    /**
     * @param :category类别
     * @author: wuwenqiang
     * @methodsName: findAllByCategory
     * @description: 根据类别查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @ApiOperation("根据大分类查询课程")
    @OperLog(message = "根据大分类查询课程", operation = OperationType.QUERY)
    @GetMapping("/findAllByCategory")
    public ResultEntity findAllByCategory(@RequestParam("category") String category) {
        return courseService.findAllByCategory(category);
    }

    /**
     * @param :courseName课程名称
     * @param :sort排序规则
     * @author: wuwenqiang
     * @methodsName: findAllByCourseName
     * @description: 根据大分类查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @ApiOperation("根据大分类查询课程")
    @GetMapping("/findAllByCourseName")
    @OperLog(message = "根据大分类查询课程", operation = OperationType.QUERY)
    public ResultEntity findAllByCourseName(String courseName) {
        return ResultUtil.success(chapterService.findAllByCourseName(courseName));
    }

    /**
     * @param :userId用户账号
     * @param :password密码
     * @author: wuwenqiang
     * @methodsName: getUserData
     * @description: 登录
     * @return: ResultEntity
     * @date: 2021-01-09 11:36
     */
    @ApiOperation("登录校验")
    @PostMapping("/login")
    @OperLog(message = "登录校验", operation = OperationType.LOGIN)
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        String userId = userEntity.getUserId();
        String password = userEntity.getPassword();
        return courseService.login(userId, password);
    }

    /**
     * @author: wuwenqiang
     * @description: 查询用户数据
     * @date: 2021-01-09 11:21
     */
    @OperLog(message = "查询用户信息", operation = OperationType.QUERY)
    @ApiOperation("查询用户信息")
    @GetMapping("/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return courseService.getUserData(token);
    }

    /**
     * @param :courseLogEntity用户账号
     * @author: wuwenqiang
     * @methodsName: saveCourseLog
     * @description: 保存课程日志信息
     * @return: ResultEntity
     * @date: 2021-01-09 11:36
     */
    @OperLog(message = "保存课程日志信息", operation = OperationType.ADD)
    @ApiOperation("保存课程日志信息")
    @PostMapping("/saveCourseLog")
    public ResultEntity saveCourseLog(@RequestBody CourseLogEntity courseLogEntity,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return courseService.saveCourseLog(courseLogEntity,token);
    }

    /**
     * @param :courseLogEntity用户账号
     * @author: wuwenqiang
     * @methodsName: saveCourseLog
     * @description: 保存课程日志信息
     * @return: ResultEntity
     * @date: 2021-01-09 11:36
     */
    @OperLog(message = "保存观看记录", operation = OperationType.ADD)
    @ApiOperation("保存观看记录")
    @PostMapping("/saveChapterLog")
    public ResultEntity saveChapterLog(@RequestBody ChapterLogEntity chapterLogEntity, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return chapterService.saveChapterLog(chapterLogEntity,token);
    }
}
