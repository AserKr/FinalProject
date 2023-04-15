package hi.HBV202G;

import java.util.ArrayList;

public class Omnibus implements Borrowable {
    private ArrayList<Book> books;



    String title;
    public Omnibus(String title, ArrayList<Book> books) {
        this.title=title;
        this.books = books;
    }


    public void addBook(String title, ArrayList<Author> authors) throws EmptyAuthorListException {
        Book book=new Book(title, authors);
        books.add(book);

    }
    public void removeBook (Book book){
            books.remove(book);
        }

    public ArrayList<Book> getBooks() {
        return books;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }



    public void borrowItem(LibrarySystem librarySystem, User user)  {
           for (Book book : this.getBooks()) {
               book.borrowItem(librarySystem, user);
           }
           librarySystem.getLendings().add(new Lending(this, user));


    }


    public void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException {
        boolean lendingExists = false;
        for (Lending lending : librarySystem.getLendings()) {
            if (lending.getBorrowable().getTitle().equals(this.getTitle())) {
                lendingExists = true;
                librarySystem.getLendings().remove(lending);
                for (Book book: this.getBooks()) {
                    book.returnItem(librarySystem, user);
                }
                break;
            }
        }
        if (!lendingExists) {
            throw new UserOrBookDoesNotExistException("Lending does not exist");
        }
    }
    public String getTitle() {
        return title;
    }


    public void extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException {

        for (User member : librarySystem.getUsers()) {
            if (member instanceof FacultyMember) {
                if (member.getName().equals(facultyMember.getName())) {
                    for (Lending lending : librarySystem.getLendings()) {
                        if (lending.getBorrowable() instanceof Omnibus && lending.getBorrowable() == this) {
                            for (Book book:getBooks()){
                                book.extendLending(facultyMember,librarySystem);
                        }
                            lending.setDueDate(lending.getDueDate().plusDays(10));
                            }

                        }
                    }

                }
            }

        }

    }



