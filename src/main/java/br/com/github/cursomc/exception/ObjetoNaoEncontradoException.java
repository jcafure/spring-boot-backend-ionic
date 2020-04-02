package br.com.github.cursomc.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {

    public ObjetoNaoEncontradoException(String s) {
        super(s);
    }

    public ObjetoNaoEncontradoException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
