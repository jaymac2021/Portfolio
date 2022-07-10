package dvdlibrary.ui;

import dvdlibrary.dto.Dvd;
import java.util.List;

public class DvdLibraryView {

    private UserIO io;

    //method to use the IO from UserIOConsoleImpl
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    //provides user with menu to selcetion options from
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs in collection");
        io.print("2. View information about a DVD");
        io.print("3. Add a DVD to collection");
        io.print("4. Edit DVD information");
        io.print("5. Remove DVD from collection");
        io.print("6. Exit");

        return io.readInt("Please select from above choices.", 1, 6);
    }

    //asks user information to create Dvd
    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter title of DVD");
        String releaseDate = io.readString("Please enter the release date of movie");
        String movieRating = io.readString("Please enter the movie rating");
        String director = io.readString("Please enter the name of director for this movie");
        String studio = io.readString("Please enter the studio that produced this movie");
        String userRating = io.readString("Please enter rating or notes about this movie");

        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMovieRating(movieRating);
        currentDvd.setDirector(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    //allows user to see what selection they entered
    public void displayCreateDvdBanner() {
        io.print("");
        io.print("=== Create DVD ===");
    }

    //provide verification to user
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created, press enter to continue.");
    }

    //provide user with list of Dvds assmbled in a format
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            String dvdInfo = String.format("Title: %s | Year: %s | Director: %s | Studio: %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getDirector(),
                    currentDvd.getStudio());
            io.print(dvdInfo);
        }
        io.readString("Please press enter to continue.");
    }

    //allows user to see what selection they entered
    public void displayDisplayAllBanner() {
        io.print("");
        io.print("=== Display All DVDs ===");
    }

    //allows user to see what selection they entered
    public void displayDisplayDvdBanner() {
        io.print("");
        io.print("=== Display DVD ===");
    }

    //asks the user what dvd they are looking for
    public String getTitleChoice() {
        return io.readString("Please enter the title of DVD");
    }

    //provides full information about the Dvd
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("");
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Director: " + dvd.getDirector());
            io.print("Movie Rating: " + dvd.getMovieRating());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
            io.print("");
        } else {
            io.print("");
            io.print("No such DVD.");
            io.print("");
        }
        io.readString("Please hit enter to continue.");
    }

    //asks user which Dvd they want to edit and then asks for full information about dvd
    public Dvd getModifiedDvdInfo() {
        String title = io.readString("Please enter title of DVD you want to edit");
        String releaseDate = io.readString("Please enter the release date of movie");
        String movieRating = io.readString("Please enter the movie rating");
        String director = io.readString("Please enter the name of director for this movie");
        String studio = io.readString("Please enter the studio that produced this movie");
        String userRating = io.readString("Please enter rating or notes about this movie");

        Dvd presentDvd = new Dvd(title);
        presentDvd.setReleaseDate(releaseDate);
        presentDvd.setMovieRating(movieRating);
        presentDvd.setDirector(director);
        presentDvd.setStudio(studio);
        presentDvd.setUserRating(userRating);
        return presentDvd;
    }

    //allows user to see what selection they entered
    public void displayEditDvdBanner() {
        io.print("");
        io.print("=== Edit DVD ===");
    }

    //provide verification to user
    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited, press enter to continue.");
    }

    //allows user to see what selection they entered
    public void displayRemoveDvdBanner() {
        io.print("");
        io.print("=== Remove DVD ===");
    }

    //provide verification to user
    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("");
            io.print("DVD sucessfully removed.");
            io.print("");
        } else {
            io.print("");
            io.print("No such DVD.");
            io.print("");
        }
        io.readString("Please press enter to continue.");
    }

    //let user know program has ended
    public void displayExitBanner() {
        io.print("");
        io.print("Good Bye!!!");
    }

    //lets user know the system doesn't understand the command it was provided
    public void displayUnknownCommandBanner() {
        io.print("");
        io.print("Unknown Command");
        io.print("");
    }

    //provides user with error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
