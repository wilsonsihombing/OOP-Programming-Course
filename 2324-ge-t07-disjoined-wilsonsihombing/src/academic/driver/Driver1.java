package academic.driver;

import java.util.*;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.Lecture;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */

public class Driver1 {

    public static void main(String[] _args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        ArrayList<Enrollment> temp_enrollments = new ArrayList<Enrollment>();
        StringBuilder sb = new StringBuilder();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();

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
                try{
                    int sks = Integer.parseInt(tokens[3]);
                }catch(NumberFormatException e){
                    System.out.println("Error: Invalid input");
                }

                int sks = Integer.parseInt(tokens[3]);
                String grade = tokens[4];

                Course course = new Course(id_course, name_course, sks, grade);
                courses.add(course);

            }else if(tokens[0].equals("enrollment-add")){
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
                    if(enr.getId_course().equals(course_id) && enr.getId_student().equals(std_id) && enr.getYear().equals(year) && enr.getSemester().equals(semester)){
                        enr.setStatus(grade);
                    }
                }

                for(Enrollment enrs : temp_enrollments){
                    if(enrs.getId_course().equals(course_id) && enrs.getId_student().equals(std_id) && enrs.getYear().equals(year) && enrs.getSemester().equals(semester)){
                        enrs.setStatus(grade);
                    }
                }
                

            }else if(tokens[0].equals("student-details")){
                String id_std = tokens[1];
                int total_sks = 0;

                Enrollment.Deleteduplicate(enrollments, id_std);

                total_sks = Enrollment.CalculateSks(enrollments, courses, id_std, total_sks);

                double hasil_perhitungan_total = 0.00;
                hasil_perhitungan_total = Enrollment.CalculateGpa(enrollments, courses, id_std, total_sks);

                double total_seluruh = hasil_perhitungan_total / total_sks;
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

            }else if(tokens[0].equals("enrollment-remedial")){
                String course_id = tokens[1];
                String id_std = tokens[2];
                String year = tokens[3];
                String semester = tokens[4];
                String grade = tokens[5];

                Enrollment.StdRemendial(enrollments, temp_enrollments, course_id, id_std, year, semester, grade);
                
            }else if(tokens[0].equals("course-open")){
                String course_id = tokens[1];
                String year = tokens[2];
                String semester = tokens[3];
                String guardian = tokens[4];

                for(Course crs : courses){
                    for(Lecture lcr : lectures){
                        if(crs.getCourse_id().equals(course_id) && lcr.getInitial().equals(guardian)){
                            CourseOpening courseOpening = new CourseOpening(course_id, year, semester, guardian);
                            courseOpenings.add(courseOpening);
                        }
                    }
                }
            }else if(tokens[0].equals("course-history")){
                String course_id = tokens[1];
                
                CourseOpening.sortingCrsOpening(courseOpenings);

                for(Course crs : courses){
                    if(crs.getCourse_id().equals(course_id)){
                        for(CourseOpening crs_open : courseOpenings){
                            if(crs_open.getCourse_id().equals(crs.getCourse_id())){
                                String[] token_initial = crs_open.getGuardian().split(",");
                                for(String token : token_initial){
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
        }

        for(Lecture lecture : lectures){
            System.out.println(lecture.getId() + "|" + lecture.getName() + "|" + lecture.getInitial() + "|" + lecture.getEmail() + "|" + lecture.getProdi());
        }

        for(Course course : courses){
            System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade());
        }

        Student.SortingStudent(students);

        
        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }


        for(Enrollment enrollment : temp_enrollments){

            if(enrollment.getGradeBefore().equals("")){
                System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
            }else{
                System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus() + "(" + enrollment.getGradeBefore() + ")");
            }
            
        }

        sc.close();
    }

}