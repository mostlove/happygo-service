package com.magicbeans.happygo.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import com.magicbeans.happygo.util.CommonUtil;
import com.magicbeans.happygo.util.StatusConstant;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author null123
 * @since 2018-02-02
 */
@TableName("t_order")
public class Order extends BaseEntity<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 用户
     */
    private String userId;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 配送地址ID
     */
    private String addressId;
    /**
     * 订单总价(不包含运费)
     */
    private BigDecimal price;
    /**
     * 付款方式
     */
    private Integer payMethod;
    /**
     * 运费
     */
    private BigDecimal freight;

    /** 该订单对当前用户是否有效  0 无效 1 有效 */
    private Integer userIsValid;

    /** 上传的流水图 */
    private String bankImg;

    /**
     * 如果为线下支付，后台需要确认此订单已经支付，
     * 如果确认已经支付 更改值为1， 已确认支付
     * 如果不确认 则更改为 2 未支付
     * 初始化为 0 待确认支付
     */
    private Integer adminOk;

    /**
     * 操作此线下订单确认的后台管理者
     */
    private String adminId;

    /** 快递单号 */
    private String expressNumber;


    /*********************** 业务字段 ************************/

    /** 购买人 */
    @TableField(exist = false)
    private String nickName;

    /** 收货地址 */
    @TableField(exist = false)
    private String address;

    /** 后台操作人员 */
    @TableField(exist = false)
    private String username;


    public String getExpressNumber() {
        return expressNumber;
    }

    public Order setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
        return this;
    }

    public String getAdminId() {
        return adminId;
    }

    public Order setAdminId(String adminId) {
        this.adminId = adminId;
        return this;
    }

    public Integer getAdminOk() {
        return adminOk;
    }

    public Order setAdminOk(Integer adminOk) {
        this.adminOk = adminOk;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        "orderNumber=" + orderNumber +
        ", userId=" + userId +
        ", status=" + status +
        ", addressId=" + addressId +
        ", price=" + price +
        ", payMethod=" + payMethod +
        ", freight=" + freight +
        "}";
    }

    /** 获取 该订单对当前用户是否有效  0 无效 1 有效 */
    public Integer getUserIsValid() {
        return this.userIsValid;
    }

    /** 设置 该订单对当前用户是否有效  0 无效 1 有效 */
    public void setUserIsValid(Integer userIsValid) {
        this.userIsValid = userIsValid;
    }

    /** 获取 上传的流水图 */
    public String getBankImg() {
        return this.bankImg;
    }

    /** 设置 上传的流水图 */
    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }


    /** 获取 购买人 */
    public String getNickName() {
        return this.nickName;
    }

    /** 设置 购买人 */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /** 获取 收货地址 */
    public String getAddress() {
        return this.address;
    }

    /** 设置 收货地址 */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 获取 后台操作人员 */
    public String getUsername() {
        return this.username;
    }

    /** 设置 后台操作人员 */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取线下支付状态
     * @return
     */
    public String getAdminOkStr() {
        //支付方式不为空 并且为线下支付
        if (!CommonUtil.isEmpty(payMethod) && payMethod.equals(StatusConstant.PAY_METHOD_UNDER_LINE)) {
            if (!CommonUtil.isEmpty(adminOk)) {
                if (adminOk == 0) {
                    return "待确认支付";
                }
                if (adminOk == 2) {
                    return "未支付";
                }
                return "已确认支付";
            }
            return "未支付";
        }
        return "";
    }
    /**
     * 获取订单状态
     * @return
     */
    public String getStatusStr() {
        if (!CommonUtil.isEmpty(status)) {
            if (status.equals(StatusConstant.ORDER_WAITING_PAY)) { return "待支付";}
            if (status.equals(StatusConstant.ORDER_PAID)) { return "线下已支付-待后台确认"; }
            if (status.equals(StatusConstant.ORDER_WAITING_SEND)) { return "待发货"; }
            if (status.equals(StatusConstant.ORDER_SENT)) { return "已发货"; }
            if (status.equals(StatusConstant.ORDER_FINISHED)) { return "已完成"; }
            if (status.equals(StatusConstant.ORDER_CANCEL)) { return "已取消"; }
            if (status.equals(StatusConstant.ORDER_REFUND)) { return "申请退款中"; }
            if (status.equals(StatusConstant.ORDER_REFUSE_REFUND)) { return "拒绝退款"; }
            if (status.equals(StatusConstant.ORDER_AGREE_REFUND)) { return "已退款"; }
        }

        return "";
    }
    /**
     * 获取订单状态
     * @return
     */
    public String getPayMethodStr() {
        if (!CommonUtil.isEmpty(payMethod)) {
            if (payMethod.equals(StatusConstant.PAY_METHOD_ALIPAY)) { return "支付宝";}
            if (payMethod.equals(StatusConstant.PAY_METHOD_WECHAT)) { return "微信"; }
            if (payMethod.equals(StatusConstant.PAY_METHOD_HXQ)) { return "欢喜券"; }
            if (payMethod.equals(StatusConstant.PAY_METHOD_UNDER_LINE)) { return "线下"; }
        }

        return "";
    }
}
