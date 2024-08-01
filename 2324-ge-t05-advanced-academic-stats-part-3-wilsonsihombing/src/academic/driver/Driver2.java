package academic.driver;

import java.util.*;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.Lecture;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */

public class Driver2 {

    public static void main(String[] _args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        ArrayList<Enrollment> temp_enrollments = new ArrayList<Enrollment>();
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
                boolean cek = false, subtract = false;
                // String temp_course = "";
                // String temp_coursescek = "";
                // String temp_grade_now = "";

                // for(Enrollment enr : enrollments){
                //     if(enr.getId_student().equals(id_std) && !enr.getStatus().equals("None")){
                //         for(Course crs : courses){
                //             //System.out.println(enr.getId_course());
                //             if(enr.getId_course().equals(crs.getCourse_id())){ 
                //                 if(crs.getCourse_id().equals(temp_course)){
                //                     // System.out.println("masuk");
                //                     total_sks = total_sks - crs.getSks();
                //                     subtract = true;
                             
                //                 }
                //                 total_sks = total_sks + crs.getSks();
                //                     // System.out.println(crs.getSks());
                //                     // System.out.println(total_sks);
                //                 temp_course = enr.getId_course();
                //                     // System.out.println("___" + temp_course);
                                
                                
                //             }
                //         }
                //     }
                // }

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

                for(Enrollment enr : enrollments){
                    if(enr.getId_student().equals(id_std)){
                        for(Course crs : courses){
                            if(enr.getId_course().equals(crs.getCourse_id())){
                                total_sks = total_sks + crs.getSks();
                            }
                        }
                    }
                }

                

                double hasil_perhitungan_total = 0.00;

                for(Enrollment enr : enrollments){
                    double hasil_perhitungan = 0.00;
                    if(enr.getId_student().equals(id_std)){
                        if(enr.getStatus().equals("None" )){
                            if(!cek){
                                total_sks = 0;
                            }else{
                                break;
                            }
                        }else{
                            String grade_now = enr.getStatus();
                            //temp_grade_now = enr.getStatus();
                            int sks_now = 0;
                            cek = true;
                            for(Course crs : courses){
                            if(enr.getId_course().equals(crs.getCourse_id()) ){
                                // System.out.println(enr.getId_course() + "|" + enr.getId_student() + "|" + enr.getYear() + "|" + enr.getSemester() + "|" + enr.getStatus() + "|" + crs.getCourse_id() + "|" + crs.getCourse_name() + "|" + crs.getSks() + "|" + crs.getGrade() + "|" + crs.getDosen());
                                // System.out.println(enr.getStatus());
                                //subtract = false;
                                sks_now = crs.getSks();
                                if(grade_now.equals("A")){
                                    hasil_perhitungan = 4.00 * sks_now ;
                                }else if(grade_now.equals("AB")){
                                    hasil_perhitungan = 3.50 * sks_now;
                                }else if(grade_now.equals("B")){
                                    hasil_perhitungan = 3 * sks_now;
                                }else if(grade_now.equals("BC")){
                                    hasil_perhitungan = 2.50 * sks_now;
                                }else if(grade_now.equals("C")){
                                    hasil_perhitungan = 2.00 * sks_now;
                                }else if(grade_now.equals("D")){
                                    hasil_perhitungan = 1.00 * sks_now;
                                }else if(grade_now.equals("E")){
                                    hasil_perhitungan = 0.00 * sks_now;
                                }
                                
                                
                                
                                hasil_perhitungan_total = hasil_perhitungan_total + hasil_perhitungan;
                                //temp_coursescek = enr.getId_course();
                                //System.out.println(hasil_perhitungan_total);
                                
                            }
                            
                        }
                    } 
                    }
                
                }
                
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

            }
        }

        for(Lecture lecture : lectures){
            System.out.println(lecture.getId() + "|" + lecture.getName() + "|" + lecture.getInitial() + "|" + lecture.getEmail() + "|" + lecture.getProdi());
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

            
        Collections.sort(students, new Comparator<Student>(){
            public int compare(Student s1, Student s2){
                return s1.getId().compareTo(s2.getId());
            }
        });
        
        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }

        // for(Enrollment enrollment : enrollments){
        //     System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
        // }

        for(Enrollment enrollment : temp_enrollments){
            System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
        }

        sc.close();
    }

}