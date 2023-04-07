package hi.HBV202G;
import edu.princeton.cs.algs4.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        In books=new In("file:E:\\HBV\\semester 2\\Hugbunadar\\Projects\\FinalProject\\src\\main\\java\\hi\\HBV202G\\Booklist.txt");
        while (books.hasNextLine()){
            String [] words =books.readLine().split(",");
            for(int i=0; i<words.length; i++){
                System.out.println(words[i]);
            }
        }
        while (scanner.hasNext()){
            System.out.println();
        }
        System.out.println("Are you an administrator or a student?");

            switch (scanner.next()) {
                case "admin":
                    System.out.println("Hello admin");

                case "student":
                    System.out.println("Hello student");

                case "end":
                    break;

        }
    }


}