package dvdlibrary.dao;

import dvdlibrary.dto.Dvd;
import java.util.List;

public interface DvdLibraryDao {

    /**
     * Adds the given Dvd to the collection and associates it with the given
     * title. If there is already a dvd associated with the given title it will
     * return that dvd object, otherwise it will return null.
     *
     * @param title with which dvd is to be associated
     * @param dvd dvd to be added to the collection
     * @return the Dvd object previously associated with the given title if it
     * exists, null otherwise
     * @throws DvdLibraryDaoException
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a List of all dvds in the collection.
     *
     * @return List containing all dvds in the collection.
     * @throws DvdLibraryDaoException
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the dvd object associated with the given title. Returns null if
     * no such dvd exists
     *
     * @param title of the dvd to retrieve
     * @return the Dvd object associated with the given title, null if no such
     * dvd exists
     * @throws DvdLibraryDaoException
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;

    /**
     * Edits the given Dvd in the collection and associates it with the given
     * title. If there is already a dvd associated with the given title it will
     * return that dvd object, otherwise it will return null.
     *
     * @param title with which dvd is to be associated
     * @param dvd dvd to edit dvd within the collection
     * @return the Dvd object previously associated with the given title if it
     * exists, null otherwise
     * @throws DvdLibraryDaoException
     */
    Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Removes from the collection the dvd associated with the given title.
     * Returns the dvd object that is being removed or null if there is no dvd
     * associated with the given title
     *
     * @param title of dvd to be removed
     * @return Dvd object that was removed or null if no dvd was associated with
     * the given title
     * @throws DvdLibraryDaoException
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;

}
