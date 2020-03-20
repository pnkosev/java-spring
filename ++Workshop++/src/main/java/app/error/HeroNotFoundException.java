package app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot find such a hero!")
public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException() {
    }

    public HeroNotFoundException(String message) {
        super(message);
    }
}
