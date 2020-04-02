package br.com.github.cursomc.controller.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timestamp) {
        super(status, message, timestamp);
    }

    public void addErrors(String fieldMessage, String message ) {
        errors.add(new FieldMessage(fieldMessage, message));
    }
}
