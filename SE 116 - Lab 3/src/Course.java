import java.lang.reflect.Array;
import java.util.ArrayList;
public class Course {
    private String courseName;
    private String courseCode;
    private ArrayList <Student> enrolledStudents;

    public void addStudent(Student s){
        if(enrolledStudents.size()==10){
            System.out.println(" Course Full: Cannot enroll more than 3 students.");
        return;
        }
        else{enrolledStudents.add(s);}
    }
    public void removeStudentByID(long id){
        for(Student a : enrolledStudents){
            if(a.getStudentID()==id){
                enrolledStudents.remove(a);
                System.out.println("Student with "+id+" ID has been removed from the course. ");
                return;
            }
        }
        System.out.println("Error: Student not found in this course.");
    }
    public void displayEnrolledStudents(){
        for(Student a : enrolledStudents){
            a.printInfo();
        }
    }

    public Course(String courseCode,String courseName) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.enrolledStudents=new ArrayList<Student>();

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
