package hi.HBV202G;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTest {


        private LibrarySystem librarySystem;
    private ArrayList<Borrowable> items;
    private ArrayList<Book> bookList;
    private ArrayList<User> users;
    private ArrayList<Lending> lendings;
    private Student student;
    private FacultyMember facultyMember;
    private Omnibus omnibus;
    private Book book1;
    private Book book2;
    private List<Author> authors;

    private Lending l1;
    private  Lending l2;
    private Lending l3;



        @Before
        public void setUp() throws EmptyAuthorListException {
            librarySystem = new LibrarySystem();
            bookList = new ArrayList<>(0);
            authors=new ArrayList<>(0);;
            authors.add(new Author("John Doe"));
            items = new ArrayList<>(0);
            users = new ArrayList<>(0);
            lendings = new ArrayList<>(0);
            student= new Student("John", true);
            facultyMember = new FacultyMember("Helmut", "Adminstrators");
            book1 = new Book("book1",authors );
            authors.add(new Author("Jane Doe"));
            book2 = new Book("book2",authors );
            bookList.add(book1);
            bookList.add(book2);
            omnibus=new Omnibus("OmnibusTest",bookList);
             l1=new Lending(omnibus, student);
             l2=new Lending(book1, student);
             l3=new Lending(book2,student);

        }

        @Test
        public void testBookName()  {
            Assert.assertEquals("book1",book1.getTitle());
        }
    @Test
    public void testOmnibusName()  {

        Assert.assertEquals("OmnibusTest",omnibus.getTitle());
    }
    @Test
    public void testAddBookWithTitleAndAuthorlist() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        librarySystem.addBookWithTitleAndAuthorlist("BookTest",authors);
        Borrowable b=librarySystem.findBorrowableByTitle("BookTest");
        assertTrue( b instanceof Book&&b.getTitle().equals("BookTest")&&((Book) b).getAuthors().equals(authors));
    }
    @Test
    public void testAddOmnibus() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        librarySystem.addOmnibus("omnibusTest", bookList);
        Borrowable b=librarySystem.findBorrowableByTitle("omnibusTest");
        assertTrue( b instanceof Omnibus&&b.getTitle().equals("omnibusTest")&&((Omnibus) b).getBooks().equals(bookList));
    }

    @Test
    public void testFindStudentUser() throws UserOrBookDoesNotExistException {
        librarySystem.addStudentUser("John", true);
        assertTrue(librarySystem.findUserByName("John") instanceof Student);
    }
    @Test
    public void testAddStudentUser() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("Helmut", "Adminstrators");
        librarySystem.addStudentUser("John", true);
        assertTrue(librarySystem.findUserByName("John") instanceof Student&&librarySystem.getUsers().get(1).getName().equals("John")
                &&((Student) librarySystem.findUserByName("John")).isFeePaid());
    }
    @Test
    public void testAddFacultyMemberUser() throws UserOrBookDoesNotExistException {
        librarySystem.addStudentUser("John", true);
        librarySystem.addFacultyMemberUser("Helmut", "Adminstrators");
        assertTrue(librarySystem.findUserByName("Helmut") instanceof FacultyMember&&librarySystem.getUsers().get(1).getName().equals("Helmut")
                && ((FacultyMember) librarySystem.findUserByName("Helmut")).getDepartment().equals("Adminstrators"));
    }
    @Test
    public void testFindFacultyMember() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("Helmut", "Adminstrators");
        assertTrue(librarySystem.findUserByName("Helmut") instanceof FacultyMember);
    }

    @Test
    public void testfindBorrowableByTitle() throws UserOrBookDoesNotExistException {


        librarySystem.getItems().add(book1);
        librarySystem.getItems().add(book2);
        librarySystem.getItems().add(omnibus);
        assertTrue(librarySystem.findBorrowableByTitle("OmnibusTest").equals(omnibus) && librarySystem.findBorrowableByTitle("book1").equals(book1) && librarySystem.findBorrowableByTitle("book2").equals(book2));
    }

@Test
    public void testBorrowBorrowableBook() throws UserOrBookDoesNotExistException {
            librarySystem.getItems().add(book1);
            librarySystem.borrowBorrowable(student, book1);
        assertTrue(librarySystem.getLendings().contains(librarySystem.findLending(book1)));
    }

    @Test
    public void testBorrowBorrowableOmnibus() throws UserOrBookDoesNotExistException {

        librarySystem.getItems().add(omnibus);
        librarySystem.getItems().add(book1);
        librarySystem.getItems().add(book2);
        librarySystem.borrowBorrowable(student, omnibus);
        assertTrue(librarySystem.getLendings().contains(librarySystem.findLending(omnibus))&&librarySystem.getLendings().contains(librarySystem.findLending(book1))&&librarySystem.getLendings().contains(librarySystem.findLending(book2)));
    }
    @Test
    public void testFindLending() throws UserOrBookDoesNotExistException {

        librarySystem.getLendings().add(l1);
        librarySystem.getLendings().add(l2);
        librarySystem.getLendings().add(l3);
        assertTrue(librarySystem.findLending(omnibus).equals(l1)&&librarySystem.findLending(book1).equals(l2)&&librarySystem.findLending(book2).equals(l3));;
    }

    @Test
    public void testReturnBorrowableBook() throws UserOrBookDoesNotExistException {
            Lending l=new Lending(book1, student);
            librarySystem.getLendings().add(l);
            librarySystem.returnItem(student, book1);
            assertTrue(librarySystem.getLendings().isEmpty());
    }
    @Test
    public void testReturnBorrowableOmnibus() throws UserOrBookDoesNotExistException {
        librarySystem.getLendings().add(l1);
        librarySystem.getLendings().add(l2);
        librarySystem.getLendings().add(l3);
        librarySystem.returnItem(student, omnibus);
        assertTrue(librarySystem.getLendings().isEmpty());
    }

    @Test
    public void testExtendLendingBook() throws UserOrBookDoesNotExistException {
        librarySystem.getUsers().add(facultyMember);
        librarySystem.getLendings().add(l2);
        LocalDate date1=l2.getDueDate();
        librarySystem.extendLending(facultyMember, book1);
        LocalDate date2=l2.getDueDate();
        assertTrue(date2.isEqual(date1.plusDays(10)));
    }
    @Test
    public void testExtendLendingOmnibus() throws UserOrBookDoesNotExistException {
        librarySystem.getUsers().add(facultyMember);
        librarySystem.getLendings().add(l1);
        librarySystem.getLendings().add(l2);
        librarySystem.getLendings().add(l3);
        LocalDate dateBook1Before=l2.getDueDate();
        LocalDate dateBook2Before=l3.getDueDate();
        LocalDate omnibussBefore=l1.getDueDate();
        librarySystem.extendLending(facultyMember, omnibus);
        LocalDate dateBook1After=l2.getDueDate();
        LocalDate dateBook2After=l3.getDueDate();
        LocalDate omnibussAfter=l1.getDueDate();
        assertTrue(dateBook1After.equals(dateBook1Before.plusDays(10))&& dateBook2After.equals(dateBook2Before.plusDays(10))&& omnibussAfter.equals(omnibussBefore.plusDays(10)));
    }




    }

