package hi.HBV202G;

public interface Borrowable {
    void borrowItem(LibrarySystem librarySystem, User user);
    void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException;
    void  extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException ;

    String getTitle();
}
