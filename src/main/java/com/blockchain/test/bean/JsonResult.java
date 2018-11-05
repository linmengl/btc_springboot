package com.blockchain.test.bean;

import com.blockchain.test.Constants;

import java.io.Serializable;


public class JsonResult implements Serializable {

	private static final long serialVersionUID = -1L;

	private static final Long SUCCESS_CODE = 0L;

	private static final Long ERROR_STATE_CODE = 1L;

	private String msg = "";
	/**
	 * 0成功，1失败
	 */
	private String code = "0000";

	private Object data = null;

	public JsonResult() {
	}

	public JsonResult(String msg, Long code, Object data) {
		this.msg = msg;
		this.code = String.format(Constants.ZERO_FILL_TEMPLATE, code);
		this.data = data;
	}

	/**
	 * 构造成功的Response
	 *
	 * @param msg  String
	 * @param data Object
	 * @return String
	 */
	public static JsonResult buildSuccessResult(String msg, Object data) {
		return new JsonResult(msg, SUCCESS_CODE, data);
	}

	/**
	 * 构造状态不正确的Response
	 *
	 * @param msg  String
	 * @param data Object
	 * @return String
	 */

	public static JsonResult buildErrorResult(String msg, Object data) {
		return new JsonResult(msg, ERROR_STATE_CODE, data);
	}

	public static JsonResult buildResult(String msg, Long code, Object data) {
		return new JsonResult(msg, code, data);
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
