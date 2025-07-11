package com.codingtu.cooltu.lib4j.exception;

public class ZipFailException extends RuntimeException {

    public ZipFailException() {
        this("压缩打包失败");
    }

    public ZipFailException(String message) {
        super(message);
    }
}
