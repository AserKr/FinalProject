package hi.HBV202G;

import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LibrarySystemApplication {
public static void readBookList(String fileName, LibrarySystem librarySystem) throws EmptyAuthorListException {
    In books = new In(fileName);
    while (books.hasNextLine()) {
        String[] words = books.readLine().split(",");
        for (int i = 0; i < words.length; i += 2) {
            List<Author> authors = new ArrayList<>();
            authors.add(new Author(words[i + 1]));
            Book book = new Book(words[i], authors);
            librarySystem.addBookWithTitleAndAuthorlist(book.getTitle(), book.getAuthors());
            System.out.println(book.getTitle() + " " + book.getAuthors().get(0).getName() + " ");
        }
    }
}
public static void adminCase(LibrarySystem librarySystem, Scanner scanner) throws EmptyAuthorListException {
    System.out.println("What is your name?");
    String name = scanner.nextLine();
    try {
        librarySystem.findUserByName(name);
    } catch (UserOrBookDoesNotExistException e) {
        FacultyMember admin = new FacultyMember(name, "Adminstrators");
        librarySystem.addFacultyMemberUser(admin.getName(), admin.getDepartment());
    }
    System.out.println("Welcome " + name + ", what is the title of the book that you would like to add?");
    String title = scanner.next();
    scanner.nextLine();
    System.out.println("and what is the name of the author/s? (seperate names with a comma)");
    List<Author> authors = new ArrayList<>();
    String authorInput = scanner.nextLine();
    String[] authorNames = authorInput.split(",");
    for (String authorName : authorNames) {
        authors.add(new Author(authorName.trim()));
    }
    librarySystem.addBookWithTitleAndAuthorlist(title, authors);
    System.out.println("thank you for adding a book");

}
    public static void studentCase(LibrarySystem librarySystem, Scanner scanner) throws UserOrBookDoesNotExistException {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        try {
            librarySystem.findUserByName(name);
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Have you paid the fee? (Type 'yes' or 'no')");
            String feePaid = scanner.next();
            boolean feePaidBoolean = feePaid.equals("yes");
            while (!feePaidBoolean) {
                System.out.println("Please Pay the fee!");
                System.out.println("Have you paid the fee? (Type 'yes' or 'no')");
                feePaid = scanner.next();
                if (feePaid.equals("yes")) {
                    feePaidBoolean = true;
                } else if (feePaid.equals("no")) {
                    feePaidBoolean = false;
                } else {
                    System.out.println("Invalid input, Type 'yes' or 'no'.");
                }
            }
            Student student = new Student(name, feePaidBoolean);
            librarySystem.addStudentUser(student.getName(), student.isFeePaid());

        }

        System.out.println("Welcome " + name + " what book would you like to borrow?");
        scanner.nextLine();
        String bookTitle = scanner.nextLine();
        try {
            if (librarySystem.findLendingByBook(librarySystem.findBookByTitle(bookTitle))!=null) {
                System.out.println("The book is not available");
            } else {
                librarySystem.findBookByTitle(bookTitle);
                librarySystem.borrowBook(librarySystem.findUserByName(name), librarySystem.findBookByTitle(bookTitle));
                System.out.println("The return DueDate for the lending is: " + librarySystem.getLendings().get(0).getDueDate());
            }

        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Sorry, that book does not exist");
        }
        System.out.println("Would you like to extend a lending? (Type 'yes' or 'no')");
        String extendLending = scanner.next();
        boolean extendLendingBoolean = extendLending.equals("yes");
        if (extendLendingBoolean == true) {
            boolean userBookBoolean=false;
            while (userBookBoolean == false) {
                System.out.println("What is the name of the book?");
                scanner.nextLine();
                String bookName = scanner.nextLine();
                System.out.println("What is the name of the faculty member?");
                String facultyMemberName = scanner.next();
                try {
                    User user=librarySystem.findUserByName(facultyMemberName);
                    librarySystem.findBookByTitle(bookName);
                    if (user instanceof FacultyMember ){
                        librarySystem.extendLending((FacultyMember) librarySystem.findUserByName(facultyMemberName), librarySystem.findBookByTitle(bookName));
                        System.out.println("Your lending has been extended by 10 days!");
                        System.out.println();
                        userBookBoolean=true;
                    }
                    else throw new UserOrBookDoesNotExistException("Sorry, the user is not a faculty member");
                } catch (UserOrBookDoesNotExistException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please try again!");
                    userBookBoolean=false;
                }
            }
        }
        System.out.println("Would you like to return a book? (Type 'yes' or 'no')");
        String answer = scanner.next();
        if (answer.equals("yes"))
        {
            System.out.println("What is the name of the book?");
            scanner.nextLine();
            String book = scanner.nextLine();
            try {
                librarySystem.returnBook(librarySystem.findUserByName(name), librarySystem.findBookByTitle(book));

            }catch (UserOrBookDoesNotExistException e){
                System.out.println(e.getMessage());
            }
        }

    }
    public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem librarySystem = new LibrarySystem();
       readBookList("file:src\\main\\java\\hi\\HBV202G\\Booklist.txt", librarySystem);
       librarySystem.addFacultyMemberUser("Helmut", "Adminstrators");
        while (true) {
            System.out.println("Are you an admin or a student? (Type 'quit' to exit)");
            String input = scanner.next();
            scanner.nextLine();
            if (input.equals("admin")) {
                adminCase(librarySystem, scanner);
            } else if (input.equals("student")) {
             studentCase(librarySystem, scanner);
            } else if (input.equals("quit")) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }

    }


}