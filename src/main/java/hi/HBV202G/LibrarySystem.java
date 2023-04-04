package hi.HBV202G;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Lending> lendings;


    public LibrarySystem() {
        books = new ArrayList<>(0);
        users = new ArrayList<>(0);
        lendings = new ArrayList<>(0);
    }

    public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list empty");
        }
        books.add(new Book(title, authors));
    }

    public void addStudentUser(String name, boolean feePaid) {

    }

    public void addFacultyMemberUser(String name, String department) {


    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book does not exist");
    }


    public User findUserByName(String name) throws UserOrBookDoesNotExistException {

        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User does not exist");
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {

    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {

    }

    public void returnBook(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {

    }
}
