package academic.driver;

import java.util.*;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.Lecture;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 12S22028 Tennov Pakpahan
 */

public class Driver1 {

    public static void main(String[] _args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        StringBuilder sb = new StringBuilder();

        while(true){
            String str = sc.nextLine();
            if(str.equals("---")){
                break;
            }

            String[] tokens = str.split("#");
            if(tokens[0].equals("student-add")){
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
            }else if(tokens[0].equals("course-add")){
                String id_course = tokens[1];
                String name_course = tokens[2];
                int sks = Integer.parseInt(tokens[3]);
                String grade = tokens[4];
                String dosen = tokens[5]; 
                Course course = new Course(id_course, name_course, sks, grade, dosen);
                courses.add(course);

            }else if(tokens[0].equals("enrollment-add")){
                String id_course = tokens[1];
                String id_student = tokens[2];
                String year = tokens[3];
                String semester = tokens[4];
                Enrollment enrollment = new Enrollment(id_student, id_course, year, semester);
                enrollments.add(enrollment);
                
            }else if(tokens[0].equals("lecturer-add")){
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
                
            }else if(tokens[0].equals("enrollment-grade")){
                String course_id = tokens[1];
                String std_id = tokens[2];
                String year = tokens[3];
                String semester = tokens[4];
                String grade = tokens[5];

                for(Enrollment enr : enrollments){
                    if(enr.getId_course().equals(course_id) && enr.getId_student().equals(std_id)){
                        enr.setStatus(grade);
                    }
                }
                

            }
        }

        for(Lecture lecture : lectures){
            System.out.println(lecture.getId() + "|" + lecture.getName() + "|" + lecture.getInitial() + "|" + lecture.getEmail() + "|" + lecture.getStudy_program());
        }


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
        

        // for(Course course : courses){
        //     System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade() + "|" + sb.toString());
        //     sb.setLength(0);
        //     //sb.delete(0, sb.length());
        // }

        /* 
        for(Course course : courses){
            System.out.print(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade() + "|");
            for(Lecture lecture : lectures){
                if(course.getDosen().contains(lecture.getInitial())){
                    if(course.getDosen().indexOf(lecture.getInitial()) == 0){
                        System.out.print(lecture.getInitial() + " (" + lecture.getEmail() + ")");
                    }else{
                        System.out.print(";" + lecture.getInitial() + " (" + lecture.getEmail() + ")");
                    }
                }

            }
            
            System.out.println();
        }
            
        */

        Collections.sort(students, new Comparator<Student>(){
            public int compare(Student s1, Student s2){
                return s1.getId().compareTo(s2.getId());
            }
        });
        
        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }

        for(Enrollment enrollment : enrollments){
            System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
        }

        sc.close();
    }

}