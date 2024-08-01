package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 */



public class Enrollment {

    private String courseCode;
    private String studentId;
    private String year;    
    private String semester;
    private String grade = "None";

    public Enrollment(String courseCode, String studentId, String year, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.year = year;
        this.semester = semester;
    }

    public String toString(){
        return courseCode + "|" + studentId + "|" + year + "|" + semester + "|" + grade;
    }

}