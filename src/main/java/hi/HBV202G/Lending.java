package hi.HBV202G;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public Lending(Book book, LocalDate dueDate) {
        this.book=book;
        this.dueDate = dueDate;
    }
}
