package com.magicbeans.happygo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magicbeans.happygo.entity.Admin;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
@CacheNamespaceRef(AdminMapper.class)
public interface AdminMapper extends BaseMapper<Admin> {

    Admin findByUsername(@Param("username") String username);

}