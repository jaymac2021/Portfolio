package dvdlibrary.controller;

import dvdlibrary.dao.DvdLibraryDao;
import dvdlibrary.dao.DvdLibraryDaoException;
import dvdlibrary.dto.Dvd;
import dvdlibrary.ui.DvdLibraryView;
import java.util.List;


public class DvdLibraryController {
    
    private DvdLibraryDao dao;
    private DvdLibraryView view;
    
    //method to use the DvdLibraryDao and DvdLibraryView
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    //code to start program
    public void run() {
        
        //declare variables
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //create exceptions with try and catch. Also creates while loop to keep user in the program
        try {
        while (keepGoing) {
            
            //gets selection from user
            menuSelection = getMenuSelection();
            
            //uses input from user to decide which operation to perform
            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                    viewDvd();
                    break;
                case 3:
                    createDvd();
                    break;
                case 4:
                    editDvd();
                    break;
                case 5:
                    removeDvd();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                }
            }
        exitMessage();
        }
        catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    //method to get input from user
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //methods from view and dao to create Dvd
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    //methods from view to list Dvds
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    //methods from view and dao to view all Dvd information
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    //methods from view and dao to edit Dvd
    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        Dvd changeDvd = view.getModifiedDvdInfo();
        dao.editDvd(changeDvd.getTitle(), changeDvd);
        view.displayEditSuccessBanner();
    }
    
    //methods from view and dao to remove Dvd
        private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    
    //method from view to display to user    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    //method from view to display to user 
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    }
