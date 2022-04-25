package summerexperience.patrykferenc.githubapi.stats.exceptions;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    private ResponseEntityBuilder() {
    }

    public static ResponseEntity<Object> build(ApiErrorMessage apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
