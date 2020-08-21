package com.arcanjo.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private long timestap;
	
	public StandardError(Integer status, String msg, long timestap) {
		this.status = status;
		this.msg = msg;
		this.timestap = timestap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTimestap() {
		return timestap;
	}

	public void setTimestap(long timestap) {
		this.timestap = timestap;
	}
	
	
	
	
}
