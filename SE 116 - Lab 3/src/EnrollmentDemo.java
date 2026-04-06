public class EnrollmentDemo {


    public static void main(String[] args) {
        Student student1=new Student("Halid Ahmet Demir",2.35,20230602021L);
        Student student2=new Student("Aleyna Kılınç",3.35,20230614022L);
        Student student3=new Student("Medine Kandemir",4.23,20002615422L);
        Student student4=new Student("Nagehan Kocaman",4.23,20002615422L);
        Student student5=new Student("Arzu Kılınç",1.23,20002615422L);
        Student student6=new Student("Selim ışık",2.23,20002615422L);
        Student student7=new Student("Şükrü Saraçoğlu",3.53,200026125422L);

        Course course1 = new Course("SE116", "Intro to Programming II");


        course1.addStudent(student1);
        course1.addStudent(student2);
        course1.addStudent(student3);
        course1.addStudent(student4);
        course1.removeStudentByID(20002615422L);
        course1.displayEnrolledStudents();
        System.out.println("The number of the student in the course : "+course1.getEnrolledStudents().size());
    }
}
