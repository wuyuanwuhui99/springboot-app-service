package com.player.music.utils;

import com.player.music.Entity.ResultEntity;

public class ResultUtil {
    /**
     * @author: wuwenqiang
     * @methodsName: success
     * @description: 成功的返回数据
     * @return: Object返回数据的data
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity success(Object object) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setStatus("success");
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: success
     * @description: 成功的返回数据
     * @return: Object返回数据的data
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity success(Object object, String msg) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setMsg(msg);
        resultEntity.setStatus("success");
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: fail
     * @description: 查询失败的返回数据
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity fail(Object object) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setStatus("fail");
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: fail
     * @description: 查询失败的返回数据
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity fail(Object object, String msg) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setStatus("fail");
        resultEntity.setStatus(msg);
        return resultEntity;
    }
}
