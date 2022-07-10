package dvdlibrary.dao;

import dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    //state what is the file used for marshalling and unmarshalling
    public static final String COLLECTION_FILE = "collection.txt";

    //state what is used as the delimiter
    public static final String DELIMITER = "::";

    //create a hashmap
    private Map<String, Dvd> dvds = new HashMap<>();

    //code to unmarshall file
    private Dvd unmarshallDvd(String dvdAsText) {
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Star Wars::1983::Family::Lucas::Lucas Arts::Great movie
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ____________________________________________________
        // |         |    |      |     |          |           |
        // |Star Wars|1983|Family|Lucas|Lucas Arts|Great movie|
        // |         |    |      |     |          |           |
        // ----------------------------------------------------
        //  [0]       [1]   [2]    [3]     [4]         [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new Dvd object to satisfy
        // the requirements of the Dvd constructor.
        Dvd dvdFromFile = new Dvd(title);

        // However, there are 5 remaining tokens that need to be set into the
        // new dvd object. Do this manually by using the appropriate setters.
        // Index 1 - ReleaseDate
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - MovieRating
        dvdFromFile.setMovieRating(dvdTokens[2]);

        // Index 3 - Director
        dvdFromFile.setDirector(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - UserRating
        dvdFromFile.setUserRating(dvdTokens[5]);

        // We have now created a dvd! Return it!
        return dvdFromFile;
    }

    //code to load the file
    private void loadCollection() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(COLLECTION_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load collection data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through COLLECTION_FILE line by line, decoding each line into a 
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    //code to save information into a file
    private String marshallDvd(Dvd aDvd) {
        // We need to turn a Dvd object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Star Wars::1983::Family::Lucas::Lucas Arts::Great movie

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        // ReleaseDate
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // MovieRating
        dvdAsText += aDvd.getMovieRating() + DELIMITER;

        // Director
        dvdAsText += aDvd.getDirector() + DELIMITER;

        // Studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // UserRating - don't forget to skip the DELIMITER here.
        dvdAsText += aDvd.getUserRating();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all dvd in the collection out to a COLLECTION_FILE. See
     * loadCollection for file format.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeCollection() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the collection file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // turn a Dvd into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadCollection();
        Dvd newDvd = dvds.put(title, dvd);
        writeCollection();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadCollection();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadCollection();
        return dvds.get(title);
    }

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadCollection();
        Dvd modifiedDvd = dvds.put(title, dvd);
        writeCollection();
        return modifiedDvd;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadCollection();
        Dvd removedDvd = dvds.remove(title);
        writeCollection();
        return removedDvd;
    }

}
