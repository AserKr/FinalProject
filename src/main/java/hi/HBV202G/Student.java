package hi.HBV202G;

public class Student  extends User{

    private boolean feePaid;

    public Student(String name, boolean feePaid) {
        this.feePaid = feePaid;
        this.name=name;
    }


    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }




}
