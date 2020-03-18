package com.atguigu.gmall.oms.dao;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zhijunYu
 * @email zhijun@yu.com
 * @date 2020-03-12 17:08:08
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
