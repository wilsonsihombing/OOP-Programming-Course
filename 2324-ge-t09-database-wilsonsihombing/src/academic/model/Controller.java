package academic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Statement; 

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */

public class Controller{
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
    ArrayList<Enrollment> temp_enrollments = new ArrayList<Enrollment>();
    ArrayList<Lecture> lectures = new ArrayList<Lecture>();
    StringBuilder sb = new StringBuilder();
    ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();

    // public Controller(String url, String username, String password) throws SQLException{
    //     super(url, username, password);
    // }

    protected void createTables() throws SQLException{
        String StudentDDL = "CREATE TABLE IF NOT EXISTS Students_tbl ("
            + "Nim VARCHAR(30) NOT NULL,"
            + "Name VARCHAR(100) NOT NULL,"
            + "Year VARCHAR(10) NOT NULL,"
            + "Prodi VARCHAR(100) NOT NULL,"
            + "PRIMARY KEY(Nim)"
            + ")";
    
        String CourseDDL = "CREATE TABLE IF NOT EXISTS course_tbl ("
            + "course_id VARCHAR(100) PRIMARY KEY,"
            + "course_name VARCHAR(100) NOT NULL,"
            + "sks INTEGER NOT NULL,"
            + "grade VARCHAR(5) NOT NULL"
            + ")";
    
        String lectureDDL = "CREATE TABLE IF NOT EXISTS lecture_tbl (" 
            + "lecture_id VARCHAR(30),"
            + "name VARCHAR(100) NOT NULL,"
            + "initial VARCHAR(10) NOT NULL,"
            + "email VARCHAR(100) NOT NULL,"
            + "study_program VARCHAR(100) NOT NULL"
            + ")";
    
        String EnrollmentDDL = "CREATE TABLE IF NOT EXISTS Enrollment_tbl(" 
            + "id_student VARCHAR(20),"
            + "id_course VARCHAR(20),"
            + "year VARCHAR(10),"
            + "semester VARCHAR(10),"
            + "status VARCHAR(5),"
            + "grade_before VARCHAR(5),"
            + "status_remedial VARCHAR(50)"
            + ")";

        String EnrollmentDDLH = "CREATE TABLE IF NOT EXISTS EnrollmentHistory_tbl(" 
            + "id_student VARCHAR(20),"
            + "id_course VARCHAR(20),"
            + "year VARCHAR(10),"
            + "semester VARCHAR(10),"
            + "status VARCHAR(5),"
            + "grade_before VARCHAR(5)"
            + ")";

        String CourseOpenDDL = "CREATE TABLE IF NOT EXISTS CourseOpening_tbl(" 
            + "id_course VARCHAR(20),"
            + "year VARCHAR(10),"
            + "semester VARCHAR(10),"
            + "guardian VARCHAR(20)"
            + ")";
    

        //Statement statement = this.getConection().createStatement();
    
        // statement.execute(StudentDDL);
        // statement.execute(CourseDDL);
        // statement.execute(lectureDDL);
        // statement.execute(EnrollmentDDL);
        // statement.execute(EnrollmentDDLH);
        // statement.execute(CourseOpenDDL);
    

        // statement.close();
    }

    // protected void seedTables() throws SQLException{
    //     String cleanUpSQL[] = {
    //         "DELETE FROM Students_tbl",
    //         "DELETE FROM course_tbl",
    //         "DELETE FROM lecture_tbl",
    //         "DELETE FROM Enrollment_tbl",
    //         "DELETE FROM EnrollmentHistory_tbl"

    //     };

    //     Statement statement = this.getConection().createStatement();

    //     for(String sql : cleanUpSQL){
    //         int affected = statement.executeUpdate(sql);
    //         statement.execute(sql);
    //     }
    // }

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

        PreparedStatement checkStatement = null; 
        ResultSet resultSet = null; 
    
        // try {
        //     connection = getConection(); 
            
        //     String checkStudentSQL = "SELECT * FROM Students_tbl WHERE Nim = ?"; 
        //     checkStatement = connection.prepareStatement(checkStudentSQL);
        //     checkStatement.setString(1, id_student);
            
        //     resultSet = checkStatement.executeQuery(); 
            
        //     if (resultSet.next()) { 
        //         System.out.println("Mahasiswa dengan ID " + id_student + " sudah ada dalam database.");
        //     } else {
        //         String insertSQL = "INSERT INTO Students_tbl (Nim, Name, Year, Prodi) VALUES (?, ?, ?, ?)";
        //         PreparedStatement statement = connection.prepareStatement(insertSQL); 
        //         statement.setString(1, id_student); 
        //         statement.setString(2, name_student); 
        //         statement.setString(3, year);
        //         statement.setString(4, prodi); 
    
        //         statement.executeUpdate();
                
        //     }
        // } catch (SQLException e) {
        //     System.out.println("Error: " + e.getMessage()); 
        // }

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


        PreparedStatement checkStatement = null; 
        ResultSet resultSet = null;

        // try {
        //     connection = getConection();
            
        //     String checkCourseSQL = "SELECT * FROM course_tbl WHERE course_id = ?";
        //     checkStatement = connection.prepareStatement(checkCourseSQL); 
        //     checkStatement.setString(1, id_course);
            
        //     resultSet = checkStatement.executeQuery(); 
            
        //     if (resultSet.next()) { 
                 
        //     } else {
        //         String insertSQL = "INSERT INTO course_tbl (course_id, course_name, sks, grade) VALUES (?, ?, ?, ?)"; 
        //         PreparedStatement statement = connection.prepareStatement(insertSQL);
        //         statement.setString(1, id_course);
        //         statement.setString(2, name_course);
        //         statement.setInt(3, sks);
        //         statement.setString(4, grade);
    
        //         statement.executeUpdate();
                
        //     }

        // } catch (SQLException e) { 
        //     System.out.println("Error: " + e.getMessage());
        // }

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


        // PreparedStatement checkStatement = null; 
        // PreparedStatement checkStatementHistory = null; 
        // ResultSet resultSet = null; 
        // ResultSet resultSetHistory = null;


        // try {
        //     connection = getConection();
            
        //     String checkEnrollmentSQL = "SELECT * FROM Enrollment_tbl WHERE id_student = ? AND id_course = ? AND year = ? AND semester = ?";
        //     String checkEnrollmentSQLHistory = "SELECT * FROM EnrollmentHistory_tbl WHERE id_student = ? AND id_course = ? AND year = ? AND semester = ?";

        //     checkStatement = connection.prepareStatement(checkEnrollmentSQL);
        //     checkStatementHistory = connection.prepareStatement(checkEnrollmentSQLHistory);

        //     checkStatement.setString(1, id_student);
        //     checkStatement.setString(2, id_course);
        //     checkStatement.setString(3, year);
        //     checkStatement.setString(4, semester);

        //     checkStatementHistory.setString(1, id_student);
        //     checkStatementHistory.setString(2, id_course);
        //     checkStatementHistory.setString(3, year);
        //     checkStatementHistory.setString(4, semester);

        //     resultSet = checkStatement.executeQuery(); 
        //     resultSetHistory = checkStatementHistory.executeQuery();
            
        //     if (resultSet.next() && resultSetHistory.next()) { 
                
        //     } else {
        //         String insertSQL = "INSERT INTO Enrollment_tbl (id_student, id_course, year, semester) VALUES (?, ?, ?, ?)";
        //         String insertSQLHistory = "INSERT INTO EnrollmentHistory_tbl (id_student, id_course, year, semester) VALUES (?, ?, ?, ?)";

        //         PreparedStatement statement = connection.prepareStatement(insertSQL);
        //         PreparedStatement statementHistory = connection.prepareStatement(insertSQLHistory);

        //         statement.setString(1, id_student);
        //         statement.setString(2, id_course);
        //         statement.setString(3, year);
        //         statement.setString(4, semester);

        //         statementHistory.setString(1, id_student);
        //         statementHistory.setString(2, id_course);
        //         statementHistory.setString(3, year);
        //         statementHistory.setString(4, semester);


        //         statement.executeUpdate();
        //         statementHistory.executeUpdate();
        //     }
        // } catch (SQLException e) { 
        //     System.out.println("Error: " + e.getMessage());
        // }

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

        // PreparedStatement checkStatement = null; 
        // ResultSet resultSet = null; 

        // try {
        //     connection = getConection(); 
            
        //     String checkLectureSQL = "SELECT * FROM lecture_tbl WHERE lecture_id = ?";
        //     checkStatement = connection.prepareStatement(checkLectureSQL);
        //     checkStatement.setString(1, id);
            
        //     resultSet = checkStatement.executeQuery();
            
        //     if (resultSet.next()) { 
        //         System.out.println("Dosen dengan ID " + id + " sudah ada dalam database.");
        //     } else {
        //         String insertSQL = "INSERT INTO lecture_tbl (lecture_id, name, initial, email, study_program) VALUES (?, ?, ?, ?, ?)"; 
        //         PreparedStatement statement = connection.prepareStatement(insertSQL); 

        //         statement.setString(1, id);
        //         statement.setString(2, name);
        //         statement.setString(3, initial);
        //         statement.setString(4, email); 
        //         statement.setString(5, study_program); 
    
        //         statement.executeUpdate();
        //     }
        //     } catch (SQLException e) {
        //         System.out.println("Error: " + e.getMessage());
        //     }
        
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


        // PreparedStatement updateStatement = null; 
        // PreparedStatement updateStatementHistory = null;
    
        // try {
        //     connection = getConection(); 
    
        //     String updateEnrollmentSQL = "UPDATE Enrollment_tbl SET status = ? WHERE id_course = ? AND id_student = ? AND year = ? AND semester = ?"; 
        //     String updateEnrollmentSQLHistory = "UPDATE EnrollmentHistory_tbl SET status = ? WHERE id_course = ? AND id_student = ? AND year = ? AND semester = ?"; 

        //     updateStatement = connection.prepareStatement(updateEnrollmentSQL); 
        //     updateStatementHistory = connection.prepareStatement(updateEnrollmentSQLHistory);
    
        //     updateStatement.setString(1, grade);
        //     updateStatement.setString(2, course_id);
        //     updateStatement.setString(3, std_id);
        //     updateStatement.setString(4, year);
        //     updateStatement.setString(5, semester);


        //     updateStatementHistory.setString(1, grade);
        //     updateStatementHistory.setString(2, course_id);
        //     updateStatementHistory.setString(3, std_id);
        //     updateStatementHistory.setString(4, year);
        //     updateStatementHistory.setString(5, semester);

        //     updateStatement.executeUpdate();
        //     updateStatementHistory.executeUpdate();
        // } catch (SQLException e) {
        //     System.out.println("Error: " + e.getMessage());
        // }

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


        // PreparedStatement statement = null; 
        // ResultSet resultSet = null; 

        // try {
        //     connection = getConection();

        //     String query = "SELECT * FROM Students_tbl WHERE Nim = ?"; 
        //     statement = connection.prepareStatement(query); 
        //     statement.setString(1, id_std); 

        //     resultSet = statement.executeQuery();

        //     while (resultSet.next()) { 
        //         String id = resultSet.getString("Nim"); 
        //         String name = resultSet.getString("Name"); 
        //         String year = resultSet.getString("Year");
        //         String prodi = resultSet.getString("Prodi");

        //         // if(total_sks == 0){
        //         //     System.out.println(id + "|" + name + "|" + year + "|" + prodi + "|" + (String.format("%.2f", total_seluruh1) + "|" + total_sks));
        //         // }else{
        //         //     System.out.println(id + "|" + name + "|" + year + "|" + prodi + "|" + (String.format("%.2f", total_seluruh) + "|" + total_sks));
        //         // }
        //     }
        // } catch (SQLException e) {
        //     System.out.println("Error: " + e.getMessage());
        // }

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
        
        
        // PreparedStatement selecStatement = null; 
        // PreparedStatement insertStatement = null; 
        // PreparedStatement updategrade = null; 

        // try{
        //     connection = getConection(); 

        //     String selectgrade = "SELECT status FROM Enrollment_tbl WHERE id_course = ? AND id_student = ? AND year = ? AND semester = ?  AND status != 'None'"; 

        //     selecStatement = connection.prepareStatement(selectgrade); 

        //     selecStatement.setString(1, course_id); 
        //     selecStatement.setString(2, id_std); 
        //     selecStatement.setString(3, year); 
        //     selecStatement.setString(4, semester); 
            
        //     ResultSet resultSet = selecStatement.executeQuery(); 

        //     if(resultSet.next()){
        //         String grade_before = resultSet.getString("status");
                
        //         String updateEnrollmentHistory = "UPDATE EnrollmentHistory_tbl SET grade_before = ? , status = ? WHERE id_course = ? AND id_student = ? AND year = ? AND semester = ? AND status != 'None' " ; 

        //         insertStatement = connection.prepareStatement(updateEnrollmentHistory);
        //         insertStatement.setString(1, grade_before);
        //         insertStatement.setString(2, grade);
        //         insertStatement.setString(3, course_id);
        //         insertStatement.setString(4, id_std);
        //         insertStatement.setString(5, year);
        //         insertStatement.setString(6, semester);

        //         insertStatement.executeUpdate();
        //     }

        //     String grade_before = resultSet.getString("status");

        //     String updateEnrollmentSQL = "UPDATE Enrollment_tbl SET  grade_before = ?, status = ?, status_remedial = ? WHERE id_course = ? AND id_student = ? AND year = ? AND semester = ? AND status != 'None'" ; 

        //     updategrade = connection.prepareStatement(updateEnrollmentSQL); 

        //     updategrade.setString(1, grade_before);
        //     updategrade.setString(2, grade);   
        //     updategrade.setString(3, "Has remedial");  
        //     updategrade.setString(4, course_id);
        //     updategrade.setString(5, id_std);
        //     updategrade.setString(6, year);
        //     updategrade.setString(7, semester);

        //     updategrade.executeUpdate();
        // }catch(SQLException e){
        //     System.out.println("Error: " + e.getMessage());
        // }
        
    }

    //course open
    public void Course_open(String[] tokens){
        String course_id = tokens[1];
        String year = tokens[2];
        String semester = tokens[3];
        String guardian = tokens[4];

        CourseOpening courseOpening = new CourseOpening(course_id, year, semester, guardian);
        courseOpenings.add(courseOpening);


        // Connection connection = null; 
        // PreparedStatement checkStatement = null; 
        // ResultSet resultSet = null; 

        // try{
        //     connection = getConection(); 

        //     String checkCourseOpeningSQL = "SELECT * FROM CourseOpening_tbl WHERE id_course = ? AND year = ? AND semester = ? AND guardian = ?";
        //     checkStatement = connection.prepareStatement(checkCourseOpeningSQL); 
        //     checkStatement.setString(1, course_id); 
        //     checkStatement.setString(2, year); 
        //     checkStatement.setString(3, semester); 
        //     checkStatement.setString(4, guardian);

        //     resultSet = checkStatement.executeQuery(); 
            
        //     if (resultSet.next()) { 

        //     } else {
        //         String insertSQL = "INSERT INTO CourseOpening_tbl (id_course, year, semester, guardian) VALUES (?, ?, ?, ?)"; 
        //         PreparedStatement statement = connection.prepareStatement(insertSQL); 
        //         statement.setString(1, course_id); 
        //         statement.setString(2, year); 
        //         statement.setString(3, semester); 
        //         statement.setString(4, guardian); 
    
        //         statement.executeUpdate(); 
        //     }
        // }catch (SQLException e) { 
        //     System.out.println("Error: " + e.getMessage()); 
        // }
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


