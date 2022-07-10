package dvdlibrary.dao;

public class DvdLibraryDaoException extends Exception {

    //provides exception message to user
    public DvdLibraryDaoException(String message) {
        super(message);
    }

    //provides exception message to user with throwable cause
    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
