package tr.com.bacompany.bacrm.data.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

    @Override
    @JsonIgnore
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
