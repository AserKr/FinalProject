package hi.HBV202G;

import java.util.List;

public  class Book implements Borrowable{
    private List<Author> authors;
    private String title;

    public Book(String name, List<Author> authors) throws EmptyAuthorListException {
        this.title = name;
        this.authors = authors;
        if(this.authors.isEmpty()){
            throw new EmptyAuthorListException("Author list is empty");
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public List<Author> getAuthors() throws EmptyAuthorListException {
        if(this.authors.isEmpty()){
            throw new EmptyAuthorListException("Author list is empty");
        }
        return authors;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException{
        if(this.authors.isEmpty()){
            throw new EmptyAuthorListException("Author list is empty");
        }
        this.authors = authors;
    }


    public void borrowItem(LibrarySystem librarySystem, User user) {
        librarySystem.getLendings().add(new Lending(this, user));
    }
    public void returnItem(LibrarySystem librarySystem, User user) throws UserOrBookDoesNotExistException {
        for (Lending lending : librarySystem.getLendings()) {
            if (lending.getBorrowable().getTitle().equals(this.getTitle())) {
                librarySystem.getLendings().remove(lending);
                break;
            } else {
                throw new UserOrBookDoesNotExistException("Lending does not exist");
            }
        }
    }


    public void extendLending(FacultyMember facultyMember, LibrarySystem librarySystem) throws UserOrBookDoesNotExistException {
        for (User member : librarySystem.getUsers()) {
            if (member instanceof FacultyMember) {
                if (member.getName().equals(facultyMember.getName())) {
                    for (Lending lending : librarySystem.getLendings()) {
                        if (lending.getBorrowable().getTitle().equals(this.getTitle())) {
                            lending.setDueDate(lending.getDueDate().plusDays(10));
                        } else throw new UserOrBookDoesNotExistException("Lending does not exist");

                    }

                }
            }

        }
    }



}
