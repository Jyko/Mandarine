package fr.pepitruc.mandarine.service.csv;

public class CSVGenerationException extends Throwable {

    public CSVGenerationException() {
    }

    public CSVGenerationException(String message) {
        super(message);
    }

    public CSVGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVGenerationException(Throwable cause) {
        super(cause);
    }

}
