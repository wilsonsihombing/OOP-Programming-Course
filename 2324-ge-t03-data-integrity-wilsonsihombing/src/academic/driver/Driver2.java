package academic.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */
public class Driver2 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

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
                Student student = new Student(id_student, name_student, year, prodi);
                students.add(student);
            }else if(tokens[0].equals("course-add")){
                String id_course = tokens[1];
                String name_course = tokens[2];
                int sks = Integer.parseInt(tokens[3]);
                String grade = tokens[4];
                Course course = new Course(id_course, name_course, sks, grade);
                courses.add(course);

            }else if(tokens[0].equals("enrollment-add")){
                String id_course = tokens[1];
                String id_student = tokens[2];
                String year = tokens[3];
                String semester = tokens[4];

                //cara mencek apakah id_course dan id_student sudah terdaftar di array list
                boolean valid_course = false;
                boolean valid_student = false;
                for(Course course : courses){
                    if(course.getCourse_id().equals(id_course)){
                        valid_course = true;
                        break;
                    }
                }  
                for(Student student : students){
                    if(student.getId().equals(id_student)){
                        valid_student = true;
                        break;
                    }
                }

                if(valid_course && valid_student){
                    Enrollment enrollment = new Enrollment(id_student, id_course, year, semester);
                    enrollments.add(enrollment);
                }else if(valid_course == false){
                    System.out.println("invalid course" + "|" + id_course);
                }else if(valid_student == false){
                    System.out.println("invalid student" + "|" + id_student);
                }

            }
        }

        
        //mengouputkan arraylist berdasarkan id_course secara ascending
        //menggunakan Collections.sort
        Collections.sort(courses, new Comparator<Course>(){
            public int compare(Course c1, Course c2){
                return c1.getCourse_id().compareTo(c2.getCourse_id());
            }
        });

        for(Course course : courses){
            System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade());
        }
        
        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }

        for(Enrollment enrollment : enrollments){
            System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
        }

        sc.close();

    }

}

