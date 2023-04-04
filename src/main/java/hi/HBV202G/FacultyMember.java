package hi.HBV202G;

public class FacultyMember extends User{
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String department;
    public void FacultyMember(String name, String department){
        this.name=name;
        this.department=department;
    }
}
