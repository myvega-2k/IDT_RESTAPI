package com.asianaidt.myrestapi.common;

import java.util.HashMap;
import java.util.Map;

import com.asianaidt.myrestapi.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class DefaultExceptionAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleException(ResourceNotFoundException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        //result.put("message", e.getMessage());
        result.put("message", "요청하신 Resource 가 존재하지 않습니다.");
        result.put("httpStatus", HttpStatus.NOT_FOUND);

        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }


    //숫자타입에 문자열이 입력된 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleException(HttpMessageNotReadableException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        //result.put("message", e.getMessage());
        result.put("message", "숫자 타입의 값만 가능합니다.");
        result.put("httpStatus", HttpStatus.BAD_REQUEST.value());

        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<Object> handleException(ResourceAccessException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "[연결 오류] 서버와 연결에 실패했습니다.");
        result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());

        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<String, Object>();
        ResponseEntity<Object> ret = null;

        String msg = "예상치 못한 문제가 발생했습니다.\n관리자에게 연락 하시기 바랍니다.";
        result.put("message", msg);
        result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
        ret = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        e.printStackTrace();

        LOGGER.error(e.getMessage(), e);

        return ret;
    }
}