package hi.HBV202G;

public class Student  extends User{

    private boolean feePaid;

    public Student(String name, boolean feePaid) {
        super(name);
        this.feePaid = feePaid;
    }


    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }


    public boolean isFeePaid() {
        return feePaid;
    }
}
