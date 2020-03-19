package app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Cannot find such a user!")
public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException() {
    }

    public HeroNotFoundException(String message) {
        super(message);
    }
}
