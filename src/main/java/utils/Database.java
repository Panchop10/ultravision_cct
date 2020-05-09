package utils;

public enum Database {
    HOST("35.235.114.157"),
    PORT("3306"),
    USER("root"),
    PASSWORD("O6H3Lgul07aB9jfD"),
    SCHEMA("ultravision");

    public final String value;

    Database(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}