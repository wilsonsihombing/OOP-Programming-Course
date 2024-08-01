package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;

import academic.model.Enrollment;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */
public class Driver3 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        while(true){
            String str = sc.nextLine();
            if(str.equals("---")){
                break;
            }
            String[] tokens = str.split("#");
            String id_course = tokens[0];
            String id_student = tokens[1];
            String year = tokens[2];
            String semester = tokens[3];

            Enrollment enrollment = new Enrollment(id_student, id_course, year, semester);

            enrollments.add(enrollment);

        }

        for(Enrollment enrollment : enrollments){
            System.out.println(enrollment.getId_course() + "|" + enrollment.getId_student() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getStatus());
        }
        
        sc.close();

    }

}