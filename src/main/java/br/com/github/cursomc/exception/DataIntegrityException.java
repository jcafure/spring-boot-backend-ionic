package br.com.github.cursomc.exception;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String s) {
        super(s);
    }

    public DataIntegrityException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
