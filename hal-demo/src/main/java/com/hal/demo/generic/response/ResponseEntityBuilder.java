package com.hal.demo.generic.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseEntityBuilder {
	private ResponseEntityBuilder(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	private HttpStatus httpStatus;

	public static ResponseEntityBuilder getBuilder(HttpStatus status) {
		return new ResponseEntityBuilder(status);
	}

	public ResponseEntity<RestApiResponse> successResponse(String message, Object result) {
		return successResponse(httpStatus.value(), message, result);
	}
	
	public ResponseEntity<RestApiResponse> successResponse(String message) {
		return successResponse(httpStatus.value(), message, "");
	}

	public ResponseEntity<RestApiResponse> successResponse(int code, String message, Object result) {
		RestApiResponse response = RestApiResponse.buildSuccessResponse(code, message, result);

		return new ResponseEntity<>(response, httpStatus);
	}

	public ResponseEntity<RestApiResponse> errorResponse(String message) {
		return errorResponse(httpStatus.value(), message);
	}

	public ResponseEntity<RestApiResponse> errorResponse(String code, String message) {
		return errorResponse(code, message, null, null);
	}

	public ResponseEntity<RestApiResponse> errorResponse(String code, String message, String downStreamCode,
			String downStreamMessage) {
		RestApiResponse response = RestApiResponse.buildFailureResponse(code, message, downStreamCode,
				downStreamMessage);
		return new ResponseEntity<>(response, httpStatus);
	}

	public ResponseEntity<RestApiResponse> errorResponse(int code, String message) {
		return errorResponse(String.valueOf(code), message, null, null);
	}

	public ResponseEntity<RestApiResponse> errorResponse(int code, String message, String downStreamCode,
			String downStreamMessage) {
		RestApiResponse response = RestApiResponse.buildFailureResponse(String.valueOf(code), message, downStreamCode,
				downStreamMessage);
		return new ResponseEntity<>(response, httpStatus);
	}
}
