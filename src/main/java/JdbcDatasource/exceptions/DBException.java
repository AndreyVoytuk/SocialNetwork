package JdbcDatasource.exceptions;

public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(String error) {
        super(error);
    }

    public DBException(String error, Throwable cause) {
        super(error, cause);
    }
}
