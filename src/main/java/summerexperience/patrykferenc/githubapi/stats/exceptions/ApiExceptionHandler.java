package summerexperience.patrykferenc.githubapi.stats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        var err = new ApiErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "User not found",
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(RequestLimitExceededException.class)
    public ResponseEntity<Object> handleRequestLimitExceededException(RequestLimitExceededException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        var err = new ApiErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Rate of requests exceeded",
                details);

        return ResponseEntityBuilder.build(err);
    }

}
