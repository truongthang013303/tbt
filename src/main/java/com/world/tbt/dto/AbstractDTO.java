package com.world.tbt.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class AbstractDTO<T> 
{
	
	private Long id;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String alert;
	private String message;
}
