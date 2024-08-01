package academic.model;
import java.util.ArrayList;

/**
 * @author 12S22011 Wilson Sihombing
 * @author 
 */
public class Enrollment {

    private String id_student;
    private String id_course;
    private String year;
    private String semester;
    private String status = "None"; 
    private String grade_before = ""; 
    private String status_remendial = "";
    private String best = "";

    public Enrollment(String id_student, String id_course, String year, String semester) {
        this.id_student = id_student;
        this.id_course = id_course;
        this.year = year;
        this.semester = semester;
    }


    public String getId_student() {
        return this.id_student;
    }

    public void setBest (String best){
        this.best = best;
    }
 
    public String getId_course() {
        return this.id_course;
    }

    public String getYear() {
        return this.year;
    }

    public String getSemester() {
        return this.semester;
    }

    public String getStatus() {
        return this.status;
    }
 
    public void setStatus (String stat){
        this.status = stat;
    }

    public void setGradeBefore(String grade){
        this.grade_before = grade;
    }

    public String getGradeBefore(){
        return this.grade_before;
    }

    public void setStatusRemedial(String stat){
        this.status_remendial = stat;
    }

    public String getStatusRemedial(){
        return this.status_remendial;
    }

    public void showbest(){
        System.out.println();
    }

    public static double CalculateGpa(ArrayList<Enrollment> enrollments,ArrayList<Course> courses,String id_std,int total_sks){
        boolean cek = false;
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
                    int sks_now = 0;
                    cek = true;
                    for(Course crs : courses){
                    if(enr.getId_course().equals(crs.getCourse_id()) ){
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
                        
                    }
                    
                    }
                } 
            }
        
        }
        return hasil_perhitungan_total;
    }

    public static void Deleteduplicate(ArrayList<Enrollment> enrollments,String id_std){
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

    public static int CalculateSks(ArrayList<Enrollment> enrollments,ArrayList<Course> courses,String id_std,int total_sks){
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

    public static void StdRemendial(ArrayList<Enrollment> enrollments,ArrayList<Enrollment> temp_enrollments,String course_id, String id_std,String year, String semester, String grade){
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
                    enrols.setStatusRemedial("Sudah Remedial");
                }
                
            }
        }        
        
    }

}