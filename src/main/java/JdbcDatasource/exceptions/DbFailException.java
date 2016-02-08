package JdbcDatasource.exceptions;

public class DbFailException extends DBException {
    public DbFailException() {
        super();
    }
    public DbFailException(String error) {
        super(error);
    }

    public DbFailException(String error, Throwable cause) {
        super(error, cause);
    }
}
