package com.player.common.entity;


import com.player.common.entity.ResultEntity;
import com.player.common.utils.ResultCode;

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
        resultEntity.setStatus(ResultCode.SUCCESS);
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
        resultEntity.setStatus(ResultCode.SUCCESS);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: success
     * @description: 成功的返回数据
     * @return: Object返回数据的data
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity success(Object object, Long total) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setTotal(total);
        resultEntity.setStatus(ResultCode.SUCCESS);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: success
     * @description: 成功的返回数据
     * @return: Object返回数据的data
     * @date: 2020-07-25 8:26
     */
    public static ResultEntity success(Object object, String msg, String token) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setToken(token);
        resultEntity.setMsg(msg);
        resultEntity.setStatus(ResultCode.SUCCESS);
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
        resultEntity.setStatus(ResultCode.FAIL);
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
        resultEntity.setStatus(ResultCode.FAIL);
        resultEntity.setMsg(msg);
        return resultEntity;
    }

    public static ResultEntity fail(Object object, String msg, String status) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setData(object);
        resultEntity.setStatus(status);
        resultEntity.setMsg(msg);
        return resultEntity;
    }
}
