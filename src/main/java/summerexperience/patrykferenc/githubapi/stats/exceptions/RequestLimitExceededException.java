package summerexperience.patrykferenc.githubapi.stats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RequestLimitExceededException extends RuntimeException {

    public RequestLimitExceededException(String message) {
        super(message);
    }

}
