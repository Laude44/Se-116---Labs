public class Student {
    private String name;
    private double gpa;
    private long studentID;

    public Student(String name,double gpa,long studentID){
        this.gpa=gpa;
        this.name=name;
        this.studentID=studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }
    public void printInfo(){
        System.out.println("Name of the student is : "+ name);
        System.out.println("Student ID : "+studentID);
        System.out.println("Student GPA : "+gpa);
    }
}
