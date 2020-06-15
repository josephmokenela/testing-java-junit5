package guru.springframework.sfgpetclinic.exceptions;

public class ValueNotFoundException extends RuntimeException {

    private final String message;


    public ValueNotFoundException(String message) {
        this.message = message;
    }
}
