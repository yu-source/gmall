package com.atguigu.gmall.ums.dao;

import com.atguigu.gmall.ums.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zhijunYu
 * @email zhijun@yu.com
 * @date 2020-03-07 20:12:28
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
