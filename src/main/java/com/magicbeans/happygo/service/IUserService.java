package com.magicbeans.happygo.service;

import com.magicbeans.base.BaseService;
import com.magicbeans.base.Pages;
import com.magicbeans.happygo.dto.DistributionUser;
import com.magicbeans.happygo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2018/1/30 0030.
 */
public interface IUserService extends BaseService<User> {

    /**
     * 通过手机号 查询用户所有基础字段
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 获取该用户下的分销列表
     * @param userId
     * @return
     */
    Map<String,List<DistributionUser>> getDistributionUser(String userId,Integer pageNO,Integer pageSize);

    /**
     * 通过邀请码查询用户
     * @param shareCode 邀请码
     * @return
     */
    User getUserByShareCode(String shareCode);

    /**
     * 统计昨日数据
     * 积分(score)、欢喜券(bigDecimal)、单元总量(暂无)、转化率(parities)
     * @return
     */
    Map<String,Object> countLastDay();

    /**
     * 后台通过/拒绝申请成为代理商
     * @param userId 用户ID
     * @param status 1 通过  2 拒绝通过
     */
    void setAgent(String userId,Integer status) throws Exception;

    /**
     * 后台用户列表
     * @param pages
     * @param map
     * @return
     */
    Pages<User> list(Pages pages, Map<String ,Object> map);
}
