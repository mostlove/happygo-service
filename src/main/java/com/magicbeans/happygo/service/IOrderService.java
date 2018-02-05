package com.magicbeans.happygo.service;

import com.magicbeans.happygo.entity.Order;
import com.magicbeans.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author null123
 * @since 2018-02-02
 */
public interface IOrderService extends BaseService<Order> {


    void addOrder(Order order, List<String> shopCarIdList);

    /**
     * 统计订单的总价格
     * @param orderId
     * @return
     */
    BigDecimal countOrderPrice(String orderId);


    /**
     * 订单支付成功处理业务
     * 线上支付回调业务处理
     * @param payMethod 支付方式 StatusConstant 查询
     * @param orderId 订单ID
     * @param price
     */
    void paySuccess(Integer payMethod,Integer orderId,BigDecimal price);

    /**
     * 线下支付业务处理
     * 不处理 订单状态，权限判断
     * 此业务不处理 银行流水上传，申请为线下支付时，锁定订单相关状态，等待上传流水业务
     *
     * @param order 订单
     */
    void underPay(Order order);

    /**
     * 订单上传流水证明
     * @param orderId 订单ID
     */
    void uploadBankImg(String orderId,String img) throws Exception;


    /**
     *  后台管理员确认此线下订单的银行流水信息
     * @param orderId 订单ID
     * @param currentUserId 当前操作者ID
     * @param status 确认支付 更改值为 1，如果不确认 则更改为 2
     */
    void confirmUnderOrder(String orderId,String currentUserId,Integer status);

}