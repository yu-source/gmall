package com.atguigu.gmall.pms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * spu信息
 * 
 * @author zhijunYu
 * @email zhijun@yu.com
 * @date 2020-02-18 21:33:40
 */
@ApiModel
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "商品id")
	private Long id;
	/**
	 * 商品名称
	 */
	@ApiModelProperty(name = "spuName",value = "商品名称")
	private String spuName;
	/**
	 * 商品描述
	 */
	@ApiModelProperty(name = "spuDescription",value = "商品描述")
	private String spuDescription;
	/**
	 * 所属分类id
	 */
	@ApiModelProperty(name = "catalogId",value = "所属分类id")
	private Long catalogId;
	/**
	 * 品牌id
	 */
	@ApiModelProperty(name = "brandId",value = "品牌id")
	private Long brandId;
	/**
	 * 上架状态[0 - 下架，1 - 上架]
	 */
	@ApiModelProperty(name = "publishStatus",value = "上架状态[0 - 下架，1 - 上架]")
	private Integer publishStatus;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(name = "createTime",value = "创建时间")
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(name = "uodateTime",value = "更新时间")
	private Date uodateTime;

}
