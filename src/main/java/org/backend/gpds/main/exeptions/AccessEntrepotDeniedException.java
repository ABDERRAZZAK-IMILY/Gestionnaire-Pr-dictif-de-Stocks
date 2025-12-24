package org.backend.gpds.main.exeptions;

public class AccessEntrepotDeniedException extends RuntimeException {
    public AccessEntrepotDeniedException(String message) {
        super(message);
    }
}
