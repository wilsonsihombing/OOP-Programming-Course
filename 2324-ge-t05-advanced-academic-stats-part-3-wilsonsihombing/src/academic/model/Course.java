package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */
public class Course {

    private String course_id;
    private String course_name;
    private int sks;
    private String grade;
    private String dosen;

    public Course(String course_id, String course_name, int sks, String grade,String dosen) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.sks = sks;
        this.grade = grade;
        this.dosen = dosen;
    }

    
    
    public String getCourse_id() {
        return this.course_id;
    }

    public String getCourse_name() {
        return this.course_name;
    } 

    public int getSks() {
        return this.sks;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getDosen() {
        return this.dosen;
    }


}