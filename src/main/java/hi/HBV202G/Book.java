package hi.HBV202G;

import java.util.List;

public class Book{
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




}
