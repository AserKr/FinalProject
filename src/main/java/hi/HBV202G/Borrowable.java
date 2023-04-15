package hi.HBV202G;

public interface Borrowable {
    public void borrowItem(LibrarySystem librarySystem, User user);
    public void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException;
    public void  extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException ;

    public String getTitle();
}
