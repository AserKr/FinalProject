package hi.HBV202G;

import java.util.ArrayList;
import java.util.List;


public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<FacultyMember> facultyMembers;
    private ArrayList<User> students;
    private ArrayList<Lending> lendings;


    public LibrarySystem() {
        books = new ArrayList<>(0);
        users = new ArrayList<>(0);
        lendings = new ArrayList<>(0);
        facultyMembers = new ArrayList<>(0);
        students = new ArrayList<>(0);
    }


    public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list empty");
        }
        books.add(new Book(title, authors));
    }

    public void addStudentUser(String name, boolean feePaid) {
        Student user = new Student(name, feePaid);
        users.add(user);
        students.add(user);
    }

    public void addFacultyMemberUser(String name, String department) {
        FacultyMember user = new FacultyMember(name, department);
        users.add(user);
        facultyMembers.add(user);


    }

    public ArrayList<FacultyMember> getFacultyMembers() {
        return facultyMembers;
    }

    public ArrayList<User> getStudents() {
        return students;
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

    /**
     * new function, to find user by type rather than name
     *
     * @param name
     * @param type
     * @return
     * @throws UserOrBookDoesNotExistException
     */
    public User findUserByType(String name, String type) throws UserOrBookDoesNotExistException {
        if (type.equals("student")) {
            for (User user : students) {
                if (user.getName().equals(name)) {
                    return user;
                }
            }
        } else if (type.equals("Adminstrators")) {
            for (FacultyMember user : facultyMembers) {
                if (user.getName().equals(name)) {
                    return user;
                }
            }
        }
        throw new UserOrBookDoesNotExistException("User does not exist");

    }


    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        try {
            findBookByTitle(book.getTitle());
            lendings.add(new Lending(book, user));
        } catch (UserOrBookDoesNotExistException e) {
            throw new UserOrBookDoesNotExistException("Book does not exist");
        }


    }

    public ArrayList<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(ArrayList<Lending> lendings) {
        this.lendings = lendings;
    }

    /**
     * deleted newDueDate as it is created within the extension dependent on each lending current dueDate, and the facultyMember type is changed
     * to user
     *
     * @param facultyMember
     * @param book
     * @throws UserOrBookDoesNotExistException
     */
    public void extendLending(FacultyMember facultyMember, Book book) throws UserOrBookDoesNotExistException {
        for (User member : facultyMembers) {
            if (member.getName().equals(facultyMember.getName())) {
                for (Lending lending : lendings) {
                    if (lending.getBook().getTitle().equals(book.getTitle())) {
                        lending.setDueDate(lending.getDueDate().plusDays(10));
                    } else throw new UserOrBookDoesNotExistException("Lending does not exist");

                }

            }

        }
    }

    /**
     * finds lending by book title
     * @param book
     * @return
     */
    public Lending findLendingByBook(Book book) {
        for (Lending lending : lendings) {
            if (lending.getBook().getTitle().equals(book.getTitle())) {
                return lending;
            }
        }
        return null;
    }


    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        for (Lending lending : lendings) {
            if (lending.getBook().getTitle().equals(book.getTitle()) && lending.getUser().getName().equals(user.getName())) {
                lendings.remove(lending);
                break;
            } else throw new UserOrBookDoesNotExistException("Lending does not exist");

        }
    }



}
