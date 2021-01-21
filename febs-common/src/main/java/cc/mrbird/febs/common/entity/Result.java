package cc.mrbird.febs.common.entity;

import cc.mrbird.febs.common.contstants.ResultCodeEnum;
import lombok.Data;

/**
 * @author helen
 * @since 2019/6/24
 */
@Data
public class Result<T> {

	private Boolean success;
	private Integer code;
	private String message;
	private T data ;

	private Result(){}

	public static Result ok(){
		Result result = new Result();
		result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
		result.setCode(ResultCodeEnum.SUCCESS.getCode());
		result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
		return result;
	}

	public static Result error(){
		return error(ResultCodeEnum.UNKNOWN_REASON.getMessage());
	}

	public  static Result error(String message){
		Result result = new Result();
		result.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
		result.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
		result.setMessage(message);
		return result;
	}
	public Result data(T t){
		this.setData(t);
		return this;
	}



	public Result message(String message){
		this.setMessage(message);
		return this;
	}

	public Result code(Integer code){
		this.setCode(code);
		return this;
	}

	public Result success(Boolean success){
		this.setSuccess(success);
		return this;
	}

	public static Result setResult(ResultCodeEnum resultCodeEnum){
		Result result = new Result();
		result.setSuccess(resultCodeEnum.getSuccess());
		result.setCode(resultCodeEnum.getCode());
		result.setMessage(resultCodeEnum.getMessage());
		return result;
	}
}
