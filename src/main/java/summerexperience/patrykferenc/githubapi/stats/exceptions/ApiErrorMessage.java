package summerexperience.patrykferenc.githubapi.stats.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime time;
    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public ApiErrorMessage(LocalDateTime time, HttpStatus status, String message, List<String> errors) {
        this.time = time;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

}
