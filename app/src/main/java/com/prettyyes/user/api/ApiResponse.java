package com.prettyyes.user.api;

/**
 * Created by Administrator on 2016/7/1.
 */
public class ApiResponse<T> {
	private String code;
	private T content;
	private String res;
	private String message;


	public ApiResponse(String paramString1, String paramString2)
	{
		this.code = paramString1;
		this.res = paramString2;
	}

	public String getCode()
	{
		return this.code;
	}

	public T getContent()
	{
		return this.content;
	}

	public String getRes()
	{
		return this.res;
	}

	public boolean isSuccess()
	{
		return this.res.equals("success");
	}

	public void setCode(String paramString)
	{
		this.code = paramString;
	}

	public void setContent(T paramT)
	{
		this.content = paramT;
	}

	public void setRes(String paramString)
	{
		this.res = paramString;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
