package com.ceph.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HaoCangServerResponse<T> implements Serializable {

	private int status;
	private String msg;
	private T data;

	private HaoCangServerResponse(int status) {
		this.status = status;
	}

	private HaoCangServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	private HaoCangServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	private HaoCangServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResultStatusEnum.SUCCESS.getCode();
	}

	public static <T> HaoCangServerResponse<T> createSuccess() {
		return new HaoCangServerResponse<T >(ResultStatusEnum.SUCCESS.getCode(),
				ResultStatusEnum.SUCCESS.getProductDescription());
	}

	public static <T > HaoCangServerResponse<T > createSuccess(String msg) {
		return new HaoCangServerResponse<T >(ResultStatusEnum.SUCCESS.getCode(), msg);
	}

	public static <T> HaoCangServerResponse<T > createSuccess(T data) {
		return new HaoCangServerResponse<T>(ResultStatusEnum.SUCCESS.getCode(),
				ResultStatusEnum.SUCCESS.getProductDescription(), data);
	}

	public static <T > HaoCangServerResponse<T > createSuccess(String msg, T data) {
		return new HaoCangServerResponse<T >(ResultStatusEnum.SUCCESS.getCode(), msg, data);
	}

	public static <T > HaoCangServerResponse<T > createSuccess(int code, T data) {
		return new HaoCangServerResponse<T >(code, data);
	}

	public static <T > HaoCangServerResponse<T > createSuccess(int code, String msg, T data) {
		return new HaoCangServerResponse<T >(code, msg, data);
	}
	public static <T > HaoCangServerResponse<T > createError() {
		return new HaoCangServerResponse<T >(ResultStatusEnum.ERROR.getCode(),
				ResultStatusEnum.ERROR.getProductDescription());
	}

	public static <T > HaoCangServerResponse<T > createError(String msg) {
		return new HaoCangServerResponse<T >(ResultStatusEnum.ERROR.getCode(), msg);
	}

	public static <T > HaoCangServerResponse<T > createError(String msg, T data) {
		return new HaoCangServerResponse<T >(ResultStatusEnum.ERROR.getCode(), msg, data);
	}

	public static <T > HaoCangServerResponse<T > createError(int code) {
		return new HaoCangServerResponse<T >(code);
	}

	public static <T > HaoCangServerResponse<T > createError(int code, String msg) {
		return new HaoCangServerResponse<T >(code, msg);
	}
}
