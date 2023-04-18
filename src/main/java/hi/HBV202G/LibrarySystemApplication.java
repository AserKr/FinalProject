package hi.HBV202G;

import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LibrarySystemApplication {
public static void readBookList(String fileName, LibrarySystem librarySystem) throws EmptyAuthorListException {
    In books = new In(fileName);
    System.out.println("\033[1m"+"BOOKS:"+ "\033[0m");
    while (books.hasNextLine()) {
        String[] words = books.readLine().split(",");
        for (int i = 0; i < words.length; i += 2) {
            List<Author> authors = new ArrayList<>();
            authors.add(new Author(words[i + 1]));
            Book book = new Book(words[i], authors);
            librarySystem.addBookWithTitleAndAuthorlist(book.getTitle(), book.getAuthors());
            System.out.println(" " + book.getTitle() + " " + book.getAuthors().get(0).getName() + " ");
        }
    }
}
    public static void readOmnibus(String fileName, LibrarySystem librarySystem) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        In books = new In(fileName);
        Pattern pattern = Pattern.compile("Name of the omnibus:(.*)");
        Pattern pattern1 = Pattern.compile("Author of the omnibus: (.*)");
        Pattern pattern2 = Pattern.compile("Number of Volumes: (.*)");
        Matcher matcher;
        Matcher matcher2;
        Matcher matcher3;
        String omnibusName = null;
        System.out.println("\033[1m"+"OMNIBUSES:"+ "\033[0m");;
        while (books.hasNextLine()) {
            String line = books.readLine();
            matcher = pattern.matcher(line);
            List<Author> authors = new ArrayList<>();
            ArrayList<Book> books1 = new ArrayList<>();
            int vol = 0;
            if (matcher.matches()) {
                omnibusName = matcher.group(1).trim();
                String line1 = books.readLine();
                matcher2 = pattern1.matcher(line1);
                System.out.println("\033[1m"+"OMNIBUSE NAME: "+ omnibusName+ "\033[0m");

                if (matcher2.matches()) {
                    String[] authorsNames = matcher2.group(1).split(",");
                    for (int i = 0; i < authorsNames.length; i++) {
                        authors.add(new Author(authorsNames[i].trim()));
                    }
                    String line2 = books.readLine();
                    matcher3 = pattern2.matcher(line2);
                    if (matcher3.matches()) {
                        vol = Integer.parseInt(matcher3.group(1));
                        for (int i = 0; i < vol; i++) {
                            String line3 = books.readLine();
                            books1.add(new Book(line3.trim(), authors));
                            System.out.println(" " + books1.get(i).getTitle().trim() + " ");
                        }
                        librarySystem.addOmnibus(omnibusName, books1);
                    }

                }


            }

        }

    }
public static void adminCase(LibrarySystem librarySystem, Scanner scanner) throws EmptyAuthorListException {
    System.out.println("\033[1m"+"What is your name?"+ "\033[0m");
    String name = scanner.nextLine();
    try {
        librarySystem.findUserByName(name);
    } catch (UserOrBookDoesNotExistException e) {
        FacultyMember admin = new FacultyMember(name, "Adminstrators");
        librarySystem.addFacultyMemberUser(admin.getName(), admin.getDepartment());
    }
    System.out.println("\033[1m"+"Welcome " + name + ", what is the title of the book that you would like to add?"+ "\033[0m");
    String title = scanner.next();
    scanner.nextLine();
    System.out.println("\033[1m"+"and what is the name of the author/s? (seperate names with a comma)"+ "\033[0m");
    List<Author> authors = new ArrayList<>();
    String authorInput = scanner.nextLine();
    String[] authorNames = authorInput.split(",");
    for (String authorName : authorNames) {
        authors.add(new Author(authorName.trim()));
    }
    librarySystem.addBookWithTitleAndAuthorlist(title, authors);
    System.out.println("\033[1m"+"thank you for adding a book"+ "\033[0m");

}
    public static void studentCase(LibrarySystem librarySystem, Scanner scanner) throws UserOrBookDoesNotExistException {
        System.out.println("\033[1m"+"What is your name?"+ "\033[0m");
        String name = scanner.nextLine();
        try {
            librarySystem.findUserByName(name);
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("\033[1m"+"Have you paid the fee? (Type 'yes' or 'no')"+ "\033[0m");
            String feePaid = scanner.next();
            boolean feePaidBoolean = feePaid.equals("yes");
            while (!feePaidBoolean) {
                System.out.println("\033[1m"+"Please Pay the fee!"+ "\033[0m");
                System.out.println("\033[1m"+"Have you paid the fee? (Type 'yes' or 'no')"+ "\033[0m");
                feePaid = scanner.next();
                if (feePaid.equals("yes")) {
                    feePaidBoolean = true;
                } else if (feePaid.equals("no")) {
                    feePaidBoolean = false;
                } else {
                    System.out.println("\033[1m"+"Invalid input, Type 'yes' or 'no'."+ "\033[0m");
                }
            }
            Student student = new Student(name, feePaidBoolean);
            librarySystem.addStudentUser(student.getName(), student.isFeePaid());
            scanner.nextLine();
        }
        System.out.println("\033[1m"+"Welcome "+ name +" would you like to borrow a book? (Type 'yes' or 'no')"+ "\033[0m");
        String borrow = scanner.nextLine().trim();
        if (borrow.equals("yes")) {
            System.out.println("\033[1m"+"Welcome " + name + " what would you like to borrow?"+ "\033[0m");

            String bookTitle = scanner.nextLine();
            try {
                librarySystem.findBorrowableByTitle(bookTitle);
                librarySystem.borrowBorrowable(librarySystem.findUserByName(name), librarySystem.findBorrowableByTitle(bookTitle));
                System.out.println("\033[1m"+"The return DueDate for the lending is: " + librarySystem.getLendings().get(0).getDueDate()+ "\033[0m");

            } catch (UserOrBookDoesNotExistException e) {
                System.out.println("\033[1m"+e.getMessage()+ "\033[0m");
            }
        }
        System.out.println("\033[1m"+"Would you like to extend a lending? (Type 'yes' or 'no')"+ "\033[0m");
        String extendLending = scanner.next();
        boolean extendLendingBoolean = extendLending.equals("yes");
        if (extendLendingBoolean == true) {
            boolean userBookBoolean=false;
            while (userBookBoolean == false) {
                System.out.println("\033[1m"+"What is the name of the book?"+ "\033[0m");
                scanner.nextLine();
                String bookName = scanner.nextLine();
                System.out.println("\033[1m"+"What is the name of the faculty member?"+ "\033[0m");
                String facultyMemberName = scanner.next();
                try {
                    User user=librarySystem.findUserByName(facultyMemberName);
                    librarySystem.findBorrowableByTitle(bookName);
                    if (user instanceof FacultyMember ){
                        librarySystem.extendLending((FacultyMember) librarySystem.findUserByName(facultyMemberName), librarySystem.findBorrowableByTitle(bookName));
                        System.out.println("\033[1m"+"Your lending has been extended by 10 days!"+ "\033[0m");

                        userBookBoolean=true;
                    }
                    else throw new UserOrBookDoesNotExistException("Sorry, the user is not a faculty member");
                } catch (UserOrBookDoesNotExistException e) {
                    System.out.println("\033[1m"+e.getMessage()+ "\033[0m");
                    System.out.println("\033[1m"+"Please try again!"+ "\033[0m");
                    userBookBoolean=false;
                }
            }
        }
        System.out.println("\033[1m"+"Would you like to return a book? (Type 'yes' or 'no')"+ "\033[0m");
        String answer = scanner.next();
        if (answer.equals("yes"))
        {
            System.out.println("\033[1m"+"What is the name of the book?"+ "\033[0m");
            scanner.nextLine();
            String book = scanner.nextLine();
            try {
                librarySystem.returnItem(librarySystem.findUserByName(name), librarySystem.findBorrowableByTitle(book));
                System.out.println("\033[1m"+"Thank you for your return!"+ "\033[0m");

            }catch (UserOrBookDoesNotExistException e){
                System.out.println("\033[1m"+e.getMessage()+ "\033[0m");
            }
        }

    }
    public static void main(String[] args) throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem librarySystem = new LibrarySystem();
        System.out.println("\033[1m"+"Welcome to the library system!"+ "\033[0m");
        System.out.println("\033[1m"+"My name is Helmut, I am the main admin here"+ "\033[0m");
        System.out.println("\033[1m"+"You can see our collections of books and omnibuses below"+ "\033[0m");
        readOmnibus("file:src\\main\\java\\hi\\HBV202G\\Omnibus.txt", librarySystem);
       readBookList("file:src\\main\\java\\hi\\HBV202G\\Booklist.txt", librarySystem);
       librarySystem.addFacultyMemberUser("Helmut", "Adminstrators");
        while (true) {
            System.out.println("\033[1m"+"Are you an admin or a student? (Type 'quit' to exit)"+ "\033[0m");
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
        System.out.println(librarySystem.getLendings().toString());
    }


}