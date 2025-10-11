package programming.lab3_2.exceptions;

public class IsNotDoingSmthException extends Exception {
    private final String name;

    public IsNotDoingSmthException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return name + " is not doing something";
    }
}
