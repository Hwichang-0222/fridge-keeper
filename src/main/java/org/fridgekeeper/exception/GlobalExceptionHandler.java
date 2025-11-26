package org.fridgekeeper.exception;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// 리소스가 없을 때 404 반환
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException ex) {
		logger.error("NoSuchElementException: {}", ex.getMessage(), ex);
		Map<String, Object> error = new HashMap<>();
		error.put("status", 404);
		error.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// 유효성 검사 실패 시 400 반환
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		logger.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	// 필수 파라미터 누락 시 400 반환
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handleMissingParamException(MissingServletRequestParameterException ex) {
		logger.error("MissingServletRequestParameterException: {}", ex.getMessage(), ex);
		Map<String, Object> error = new HashMap<>();
		error.put("status", 400);
		error.put("message", "필수 파라미터가 누락되었습니다: " + ex.getParameterName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// 권한 없음(AccessDeniedException) 발생 시 403 반환
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
		logger.error("AccessDeniedException: {}", ex.getMessage(), ex);
		Map<String, Object> error = new HashMap<>();
		error.put("status", 403);
		error.put("message", "권한이 없습니다.");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	// 기타 예외 발생 시 500 반환
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
		logger.error("Exception: {}", ex.getMessage(), ex);
		Map<String, Object> error = new HashMap<>();
		error.put("status", 500);
		error.put("message", "서버 내부 오류가 발생했습니다.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
