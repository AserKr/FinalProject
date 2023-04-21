package hi.HBV202G;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;

    private Borrowable borrowable;
    private User user;

    public Lending(Borrowable item, User user) {
        LocalDate now = LocalDate.now();
        this.dueDate = now.plusDays(30);
        this.borrowable = item;
        this.user = user;
    }


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

    public Borrowable getBorrowable() {
        return borrowable;
    }



    public void setBorrowable(Borrowable borrowable) {
        this.borrowable = borrowable;
    }

}
