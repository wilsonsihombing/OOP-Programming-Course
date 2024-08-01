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
        StringBuilder sbs = new StringBuilder();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();
        String[] simpan = new String[5];
        int idxe = 0;
        for(int i = 0;i< 5;i++){
            simpan[i] = "";
        }

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
            }else if(tokens[0].equals("find-the-best-student")){
                String year = tokens[1];
                String semester = tokens[2];
                int idx = 0;
                double[] temp_konversi= new double[20];
                String[] temp_nim = new String[20];
                String[] temp_grade = new String[20];
                double best_grade;
                int index_akhir = 0;

                for(int i = 0;i<enrollments.size();i++){      
                    if(enrollments.get(i).getYear().equals(year) && enrollments.get(i).getSemester().equals(semester)){
                        temp_nim[idx]= enrollments.get(i).getId_student();
                        temp_grade[idx] = enrollments.get(i).getStatus();

                        if(temp_grade.equals("A")){
                            temp_konversi[idx] = 4;
                        }else if(temp_grade.equals("AB")){
                           temp_konversi[idx] = 3.5;
                        }else if(temp_grade.equals("B")){
                            temp_konversi[idx] = 3.0;
                        }else if(temp_grade.equals("BC")){
                            temp_konversi[idx] = 2.5;
                        }else if(temp_grade.equals("C")){
                            temp_konversi[idx] = 2.0;
                        }else if(temp_grade.equals("D")){
                            temp_konversi[idx] = 1.5;
                        }else if(temp_grade.equals("E")){
                            temp_konversi[idx] = 0;
                        }

                        idx++;
                    }
                }

                for(int i = 0 ; i < idx; i++){
                    for (int j = i+1; j < idx; j++) {
                        if(temp_konversi[i] > temp_konversi[j]){
                            best_grade = temp_konversi[i];
                            index_akhir = i;
                        }else if(temp_konversi[i] < temp_konversi[j]){
                            best_grade = temp_konversi[j];
                            index_akhir = j;
                        }else{
                            String tempnim1 = temp_nim[i];
                            String tempnim2 = temp_nim[j];

                            int intial1 = Integer.parseInt(tempnim1.substring(7));
                            int intial2 = Integer.parseInt(tempnim2.substring(7));

                            if(intial1 % 2 == 0){
                                index_akhir = i;
                            }else if(intial2 % 2 == 0){
                                index_akhir = j;
                            }
                            
                            
                        }
                    }
                }

                String id_student_ = temp_nim[index_akhir];
                String new_grade = "";
                String odd = "";
                String even = "";
                for(Enrollment E :enrollments){
                    if (E.getId_student().equals(id_student_) && E.getSemester().equals("odd") && E.getYear().equals(year)) {
                        odd = E.getStatus();
                    }else if (E.getId_student().equals(id_student_) && E.getSemester().equals("even") && E.getYear().equals(year)) {
                        even = E.getStatus();
                    }
                }

                for (Enrollment E : enrollments) {
                    if (E.getId_student().equals(id_student_) && E.getYear().equals(year)&& E.getSemester().equals(semester)) {
                        simpan[idxe] = id_student_+"|" + odd + "/" + even;
                        idxe++;
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

        for(int i = 0;i < idxe ; i++){
            System.out.println(simpan[i]);
        }


        sc.close();
    }

}