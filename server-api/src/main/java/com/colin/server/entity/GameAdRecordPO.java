/**
 * 项目名：********
 * 包名：com.qiumi.base.game.biz.business.entity.po;
 * 文件名：TGameAdRecordPO
 * 版本信息：1.0.0
 * 日期：2019-12-19 13:45:32
 * Copyright (c) 北京博乐互动科技有限公司
 * 
 */
package com.colin.server.entity;

import java.util.Date;


/**
 * 表名：T_GAME_AD_RECORD
 * 备注：游戏广告记录表
 * 创建时间:
 * @author ticket 
 *
 */
public class GameAdRecordPO implements java.io.Serializable {

	private static final long serialVersionUID = 8591144076167447658L;

	/**
	 * 字段名称：ID
	 * 字段类型: NUMBER	 
	 * 字段默认值：
	 * 字段备注: 记录ID
	 */
	private Long id ;
/**
	 * 字段名称：AD_ID
	 * 字段类型: VARCHAR2	 
	 * 字段默认值：
	 * 字段备注: 广告ID
	 */
	private String adId ;
/**
	 * 字段名称：PLATFORM_ID
	 * 字段类型: NUMBER	 
	 * 字段默认值：
	 * 字段备注: 用户平台ID
	 */
	private Long platformId ;
/**
	 * 字段名称：SLW_ID
	 * 字段类型: NUMBER	 
	 * 字段默认值：
	 * 字段备注: 店铺ID
	 */
	private Long slwId ;
/**
	 * 字段名称：NUMBER_ID
	 * 字段类型: NUMBER	 
	 * 字段默认值：
	 * 字段备注: 用户店铺级ID
	 */
	private Long numberId ;
/**
	 * 字段名称：RECORD_TIME
	 * 字段类型: DATE	 
	 * 字段默认值：
	 * 字段备注: 记录时间
	 */
	private Date recordTime ;
/**
	 * 字段名称：STATE
	 * 字段类型: NUMBER	 
	 * 字段默认值：
	 * 字段备注: 是否完成0未完成，1已完成
	 */
	private Integer state ;
       
	/** 
	 * 默认构造函数
	 */
	public GameAdRecordPO() {
	}		
	
public void setId (Long id){
		this.id=id;
	}
	
	public Long getId (){
		return this.id;
	}
public void setAdId (String adId){
		this.adId=adId;
	}
	
	public String getAdId (){
		return this.adId;
	}
public void setPlatformId (Long platformId){
		this.platformId=platformId;
	}
	
	public Long getPlatformId (){
		return this.platformId;
	}
public void setSlwId (Long slwId){
		this.slwId=slwId;
	}
	
	public Long getSlwId (){
		return this.slwId;
	}
public void setNumberId (Long numberId){
		this.numberId=numberId;
	}
	
	public Long getNumberId (){
		return this.numberId;
	}
public void setRecordTime (Date recordTime){
		this.recordTime=recordTime;
	}
	
	public Date getRecordTime (){
		return this.recordTime;
	}
public void setState (Integer state){
		this.state=state;
	}
	
	public Integer getState (){
		return this.state;
	}




}