package academic.model;

import java.util.ArrayList;

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
 
    public static void addStrBuilder(ArrayList<Course> courses, ArrayList<Lecture> lectures, StringBuilder sb){
        for(Course course : courses){
            String[] tokens = course.getDosen().split(",");
            for(String token : tokens){
                for(Lecture lecture : lectures){
                    if(lecture.getInitial().equals(token)){
                        sb.append(lecture.getInitial() + " (" + lecture.getEmail() + ")");
                        if(tokens.length > 1 && tokens[tokens.length-1] != token){
                            sb.append(";"); 
                        }else{
                            sb.append("");
                        }
                        
                    }
                    
                }
            }
            System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade() + "|" + sb.toString());
            sb.setLength(0);
    }
    }


}