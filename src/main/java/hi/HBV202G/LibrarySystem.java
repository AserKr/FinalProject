package hi.HBV202G;

import java.util.ArrayList;
import java.util.List;


public class LibrarySystem {


    private ArrayList<Borrowable> items;

    public ArrayList<User> getUsers() {
        return users;
    }

    private ArrayList<User> users;
    private ArrayList<Lending> lendings;



    public LibrarySystem() {
        items = new ArrayList<>(0);
        users = new ArrayList<>(0);
        lendings = new ArrayList<>(0);
    }


    public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list empty");
        }
        items.add(new Book(title, authors));
    }

    public void addOmnibus(String title, ArrayList<Book> book) throws EmptyAuthorListException {
        for (Book book1 : book) {
            if (book1.getAuthors().isEmpty())
            {
                throw new EmptyAuthorListException("Author list empty");
            }
            items.add(book1);
        }
        items.add(new Omnibus(title, book));
    }
    public void addStudentUser(String name, boolean feePaid) {
        Student user = new Student(name, feePaid);
        users.add(user);
    }

    public void addFacultyMemberUser(String name, String department) {
        FacultyMember user = new FacultyMember(name, department);
        users.add(user);


    }





    public Borrowable findBorrowableByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Borrowable item : items) {
            /*if (item instanceof Book) {
                if (((Book) item).getTitle().equals(title)) {
                    return (Book) item;
                } else if (item instanceof Omnibus) {
                    for (Book book : ((Omnibus) item).getBooks()) {
                            if (book.getTitle().equals(title)) {
                                return (Omnibus) item;
                            }
                        }
                    }
                }*/
            if (item.getTitle().equals(title)) {
                return item;
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



    public void borrowBorrowable(User user, Borrowable item) throws UserOrBookDoesNotExistException {
        if (findBorrowableByTitle(item.getTitle()) == null) {
            throw new UserOrBookDoesNotExistException("Item does not exist");
        } else if (findLending(item)!=null) {
            throw new UserOrBookDoesNotExistException("Item is not available");
        } else {
            findBorrowableByTitle(item.getTitle()).borrowItem(this, user);
        }

    }

    public ArrayList<Lending> getLendings() {
        return lendings;
    }



    public void extendLending(FacultyMember facultyMember, Borrowable item) throws UserOrBookDoesNotExistException {
      item.borrowItem(this, facultyMember);
    }




    /**
     * finds lending by book title
     * @param item
     * @return
     */
    public Lending findLending(Borrowable item) {
        for (Lending lending : lendings) {
            if (lending.getBorrowable().getTitle().equals(item.getTitle())) {
                return lending;
            }
        }
        return null;
    }


    public void returnItem(User user, Borrowable item) throws UserOrBookDoesNotExistException {
        item.returnItem(this, user);
    }



}
