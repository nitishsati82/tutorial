package com.swagger.demo.generic.response;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class RestApiResponse {
	private RestApiResponse() {
	}

	private Status status;
	private Object result;

	static RestApiResponse buildSuccessResponse(int code, String message, Object result) {
		RestApiResponse response = new RestApiResponse();
		Status status = new Status();
		status.setCode(code);
		status.setMessage(message);
		status.setTimeStamp(new Date());
		response.setStatus(status);
		response.setResult(result);
		return response;
	}

	static RestApiResponse buildFailureResponse(String code, String message, String downStreamCode,
			String downStreamMessage) {
		RestApiResponse restApiResopnse = new RestApiResponse();
		Status status = new Status();
		status.setCode(code);
		status.setMessage(message);
		status.setDownstreamCode(downStreamCode);
		status.setDownstreamMessage(downStreamMessage);
		restApiResopnse.setStatus(status);
		status.setTimeStamp(new Date());
		return restApiResopnse;
	}

	public static RestApiResponse buildFailureResponse(String code, String message) {
		RestApiResponse restApiResopnse = new RestApiResponse();
		Status status = new Status();
		status.setCode(code);
		status.setMessage(message);
		restApiResopnse.setStatus(status);
		status.setTimeStamp(new Date());
		return restApiResopnse;
	}

	public String getMessageFromStatus(Status status) {
		return !StringUtils.hasLength(status.getDownstreamMessage()) ? status.getDownstreamMessage()
				: status.getMessage();
	}

	public String getCodeFromStatus(Status status) {
		return status.getCode();
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Getter
	@Setter
	@ToString
	static class Status {
		private String code;
		private String message;
		private String downstreamCode;
		private String downstreamMessage;
		private Date timeStamp;

		private void setCode(int code) {
			setCode(String.valueOf(code));
		}

		private void setCode(String code) {
			this.code = code;
		}
	}
}
