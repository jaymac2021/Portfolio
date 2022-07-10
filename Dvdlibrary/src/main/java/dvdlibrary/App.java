package dvdlibrary;

import dvdlibrary.controller.DvdLibraryController;
import dvdlibrary.dao.DvdLibraryDao;
import dvdlibrary.dao.DvdLibraryDaoFileImpl;
import dvdlibrary.ui.DvdLibraryView;
import dvdlibrary.ui.UserIO;
import dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {

        //allows different parts of the program use the constructor and then run program
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();

    }

}
