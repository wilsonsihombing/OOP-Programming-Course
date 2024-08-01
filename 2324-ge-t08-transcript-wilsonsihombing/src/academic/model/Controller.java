package academic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */

public class Controller {
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
    ArrayList<Enrollment> temp_enrollments = new ArrayList<Enrollment>();
    ArrayList<Lecture> lectures = new ArrayList<Lecture>();
    StringBuilder sb = new StringBuilder();
    ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();

    //adding student
    public void addStudent(String[] tokens) {
        String id_student = tokens[1];
        String name_student = tokens[2];
        String year = tokens[3];
        String prodi = tokens[4];
                
        boolean cek_student = true;
        for(Student student : students){
            if(student.getId().equals(id_student)){
                cek_student = false;
                break;
            }
        }

        if(cek_student){
            Student student = new Student(id_student, name_student, year, prodi);
            students.add(student);
        }

    }

    //adding course
    public void  addCourse(String[] tokens){
        String id_course = tokens[1];
        String name_course = tokens[2];
        int sks = 0;
        try{
            sks = Integer.parseInt(tokens[3]);
        }catch(NumberFormatException e){
            System.out.println("Error: Invalid input");
        }
        
        String grade = tokens[4];

        Course course = new Course(id_course, name_course, sks, grade);
        courses.add(course);
    }    

    //adding enrollment
    public void addEnrollment(String[] tokens){
        String id_course = tokens[1];
        String id_student = tokens[2];
        String year = tokens[3];
        String semester = tokens[4];

        boolean cek_enrollment = true;
        for(Enrollment enrollment : enrollments){
            if(enrollment.getId_course().equals(id_course) && enrollment.getId_student().equals(id_student) && enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)){
                cek_enrollment = false;
                break;
            }
        }

        if(cek_enrollment){
            Enrollment enrollment = new Enrollment(id_student, id_course, year, semester);
            enrollments.add(enrollment);
            temp_enrollments.add(enrollment);
        }
    }

    //adding lecture
    public void addLecture(String[] tokens){
        String id = tokens[1];
        String name = tokens[2];
        String initial = tokens[3];
        String email = tokens[4];
        String study_program = tokens[5];
 
        boolean cek_lecture = true;
        for(Lecture lecture : lectures){
            if(lecture.getId().equals(id)){
                cek_lecture = false;
                break;
            }
        }

        if(cek_lecture){
            Lecture lecture = new Lecture(id, name, initial, email, study_program);
            lectures.add(lecture);
        }
    }

    //adding enrollment grade
    public void Add_EnrGrade(String[] tokens){
        String course_id = tokens[1];
        String std_id = tokens[2];
        String year = tokens[3];
        String semester = tokens[4];
        String grade = tokens[5];

        for(Enrollment enr : enrollments){
            if(enr.getId_course().equals(course_id) && enr.getId_student().equals(std_id) && enr.getYear().equals(year) && enr.getSemester().equals(semester)){
                enr.setStatus(grade);
            }
        }

        for(Enrollment enrs : temp_enrollments){
            if(enrs.getId_course().equals(course_id) && enrs.getId_student().equals(std_id) && enrs.getYear().equals(year) && enrs.getSemester().equals(semester)){
                enrs.setStatus(grade);
            }
        }
    }

    //student details
    public void Students_detail(String[] tokens){
        String id_std = tokens[1];
        int total_sks = 0;

        Deleteduplicate(id_std);

        total_sks = CalculateSks(id_std, total_sks);

        double calculate_result = 0.00;
        calculate_result = CalculateGpa(id_std, total_sks);

        double total_seluruh = calculate_result / total_sks;
        double total_seluruh1 = 0.00;

        for(Student std : students){
            if(std.getId().equals(id_std)){
                if(total_sks == 0){
                    System.out.println(std.getId() + "|" + std.getName() + "|" + std.getAddress() + "|" + std.getProdi() + "|" + (String.format("%.2f", total_seluruh1) + "|" + total_sks));
                }else{
                    System.out.println(std.getId() + "|" + std.getName() + "|" + std.getAddress() + "|" + std.getProdi() + "|" + (String.format("%.2f", total_seluruh) + "|" + total_sks));
                }
            } 
        }

    }

    //delete duplicate
    public void Deleteduplicate(String id_std){
        for(int i = 0;i<enrollments.size();i++){
            if(enrollments.get(i).getId_student().equals(id_std)){
                for(int j= i+1;j<enrollments.size();j++){
                    if(enrollments.get(j).getId_student().equals(id_std)){
                        if(enrollments.get(i).getId_course().equals(enrollments.get(j).getId_course())){
                            enrollments.remove(i);
                        }
                    }
                }
            }
        }
    }

    //calculate sks
    public int CalculateSks(String id_std,int total_sks){
        for(Enrollment enr : enrollments){
            if(enr.getId_student().equals(id_std)){
                for(Course crs : courses){
                    if(enr.getId_course().equals(crs.getCourse_id())){
                        total_sks = total_sks + crs.getSks();
                    }
                }
            }
        }
        return total_sks;
    }

    //calculate gpa
    public double CalculateGpa(String id_std,int total_sks){
        boolean cek = false;
        double calculate_result = 0.00;
        for(Enrollment enr : enrollments){
            double result = 0.00;
            if(enr.getId_student().equals(id_std)){
                if(enr.getStatus().equals("None" )){
                    if(!cek){
                        total_sks = 0;
                    }else{
                        break;
                    }
                }else{
                    String grade_now = enr.getStatus();
                    int sks_now = 0;
                    cek = true;
                    for(Course crs : courses){
                    if(enr.getId_course().equals(crs.getCourse_id()) ){
                        sks_now = crs.getSks();
                        if(grade_now.equals("A")){
                            result = 4.00 * sks_now ;
                        }else if(grade_now.equals("AB")){
                            result = 3.50 * sks_now;
                        }else if(grade_now.equals("B")){
                            result = 3 * sks_now;
                        }else if(grade_now.equals("BC")){
                            result = 2.50 * sks_now;
                        }else if(grade_now.equals("C")){
                            result = 2.00 * sks_now;
                        }else if(grade_now.equals("D")){
                            result = 1.00 * sks_now;
                        }else if(grade_now.equals("E")){
                            result = 0.00 * sks_now;
                        }
                        
                        calculate_result = calculate_result + result;
                        
                    }
                    
                    }
                } 
            }
        
        }
        return calculate_result;
    }

    //enrollment remedial
    public void Enrollment_remedial(String[] tokens){
        String course_id = tokens[1];
        String id_std = tokens[2];
        String year = tokens[3];
        String semester = tokens[4];
        String grade = tokens[5];

        StdRemedial(course_id, id_std, year, semester, grade);
    }

    //student remedial
    public void StdRemedial(String course_id, String id_std,String year, String semester, String grade){
        for(Enrollment enrols : enrollments){
            if (enrols.getId_course().equals(course_id) && enrols.getId_student().equals(id_std) && enrols.getYear().equals(year) && enrols.getSemester().equals(semester) && enrols.getStatus() != "None") {
                if(enrols.getStatusRemedial().equals("")){
                    String temp_grade = enrols.getStatus();
                    for(Enrollment enrs : temp_enrollments){
                        if(enrs.getId_course().equals(course_id) && enrs.getId_student().equals(id_std) && enrs.getYear().equals(year) && enrs.getSemester().equals(semester)){
                            enrs.setGradeBefore(temp_grade);
                        }
                    }
                    enrols.setStatus(grade);
                    enrols.setStatusRemedial("Has Remedial");
                }
                
            }
        }        
        
    }

    //course open
    public void Course_open(String[] tokens){
        String course_id = tokens[1];
        String year = tokens[2];
        String semester = tokens[3];
        String guardian = tokens[4];

        CourseOpening courseOpening = new CourseOpening(course_id, year, semester, guardian);
        courseOpenings.add(courseOpening);
    }

    //course history
    public void Course_history(String[] tokens){
        String course_id = tokens[1];
                
        sortingCrsOpening();

        for(Course crs : courses){
            if(crs.getCourse_id().equals(course_id)){
                for(CourseOpening crs_open : courseOpenings){
                    if(crs_open.getCourse_id().equals(crs.getCourse_id())){
                        String[] token_initial = crs_open.getGuardian().split(",");
                        for(String token : token_initial){

                            build_strbuilder(token_initial, token);

                            System.out.println(crs.getCourse_id() + "|" + crs.getCourse_name() + "|" + crs.getSks() + "|" + crs.getGrade() + "|" + crs_open.getYear() + "|" + crs_open.getSemester() + "|" + sb.toString());
                            sb.setLength(0);

                            for(Enrollment enr : enrollments){
                                if(enr.getId_course().equals(crs.getCourse_id()) && enr.getYear().equals(crs_open.getYear()) && enr.getSemester().equals(crs_open.getSemester())){
                                    if(enr.getGradeBefore().equals("")){
                                        System.out.println(enr.getId_course() + "|" + enr.getId_student() + "|" + enr.getYear() + "|" + enr.getSemester() + "|" + enr.getStatus());
                                    }else{
                                        System.out.println(enr.getId_course() + "|" + enr.getId_student() + "|" + enr.getYear() + "|" + enr.getSemester() + "|" + enr.getStatus() + "(" + enr.getGradeBefore() + ")");
                                    }
                                }
                            }

                        }
                                    
                    }
                        
                }
            }
        }
    }

    //sorting course opening
    public void sortingCrsOpening(){
        Collections.sort(courseOpenings, new Comparator<CourseOpening>(){
            public int compare(CourseOpening c1, CourseOpening c2){
                return c2.getSemester().compareTo(c1.getSemester());
            }
        });
    }

    //build string builder
    public void build_strbuilder(String[] token_initial, String token){
        for(Lecture lecture : lectures){
            if(lecture.getInitial().equals(token)){
                sb.append(lecture.getInitial() + " (" + lecture.getEmail() + ")");
                if(token_initial.length > 1 && token_initial[token_initial.length-1] != token){
                    sb.append(";"); 
                }else{
                    sb.append("");
                }
                        
            }
                        
        }
    }

    //delete old transcript
    public void delete_OldTranscript(String[] tokens){
        String id_std = tokens[1];
        for(int i = 0 ; i < enrollments.size();i++){
            for(int j = i+1;j < enrollments.size();j++){
                if(enrollments.get(i).getId_student().equals(id_std) && enrollments.get(j).getId_student().equals(id_std) && enrollments.get(i).getId_course().equals(enrollments.get(j).getId_course())) {
                    if(enrollments.get(i).getYear().compareTo(enrollments.get(j).getYear()) > 0){
                        enrollments.remove(j);
                    }else{
                        enrollments.remove(i);
                    }
                }
            }
            
        }
    }

    //print transcript
    public void print_transcript(String[] tokens){
        String id_std = tokens[1];
        for(Enrollment enr : enrollments){
            if(enr.getId_student().equals(id_std)){
                if(enr.getGradeBefore().equals("")){
                    System.out.println(enr.getId_course() + "|" + enr.getId_student() + "|" + enr.getYear() + "|" + enr.getSemester() + "|" + enr.getStatus());
                }else{
                    System.out.println(enr.getId_course() + "|" + enr.getId_student() + "|" + enr.getYear() + "|" + enr.getSemester() + "|" + enr.getStatus() + "(" + enr.getGradeBefore() + ")");
                }
            }
        }
    }

    //print everything details
    public void print_EverythingDetails(){
        printLecture();
        printCourse();
        printStudent();
        printEnrollment();
    }

    //konsep inner class
    public class printEverythingDetail{
        public void printEverythingDetails(){
            printLecture();
            printCourse();
            printStudent();
            printEnrollment();
        }
    }

    //mengakses inner class
    public void use_printEverythingDetails(){
        printEverythingDetail print_detail = new printEverythingDetail();
        print_detail.printEverythingDetails();
    }

    //print lecture
    public void printLecture(){
        for(Lecture lecture : lectures){
            System.out.println(lecture.getId() + "|" + lecture.getName() + "|" + lecture.getInitial() + "|" + lecture.getEmail() + "|" + lecture.getProdi());
        }
    }

    //print course
    public void printCourse(){
        for(Course course : courses){
            System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade());
        }
    }

    //print enrollment
    public void printEnrollment(){
        for(Enrollment enrollment : temp_enrollments){

            if(enrollment.getGradeBefore().equals("")){
                System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
            }else{
                System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus() + "(" + enrollment.getGradeBefore() + ")");
            }
            
        }
    }

    //print student
    public void printStudent() {
        SortingStudent();
        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }
    }

    //sorting student
    public void SortingStudent(){
        Collections.sort(students, new Comparator<Student>(){
            public int compare(Student s1, Student s2){
                return s1.getId().compareTo(s2.getId());
            }
        });
    }
    
    @Override
    public String toString(){
        printLecture();
        printCourse();
        printStudent();
        printEnrollment();
        
        return "";
    }
}


