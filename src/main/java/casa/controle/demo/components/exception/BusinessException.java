package casa.controle.demo.components.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception para regras de negócio violadas
 * Retorna HTTP 422 (UNPROCESSABLE_ENTITY) por padrão
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {

    private final String code; // Código de erro customizável
    private final Object[] args; // Argumentos para mensagem

    public BusinessException(String message) {
        this(null, message, null);
    }

    public BusinessException(String code, String message) {
        this(code, message, null);
    }

    public BusinessException(String code, String message, Object[] args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}