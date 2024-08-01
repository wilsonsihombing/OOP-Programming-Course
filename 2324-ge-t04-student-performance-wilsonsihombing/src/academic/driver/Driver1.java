package academic.driver;



/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */

import academic.model.Student;
import java.util.*;

public class Driver1 {

    public static void main(String[] _args) {
    
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();

        while(true){
            String str  = sc.nextLine();
            if(str.equals("---")){
                break;
            }

            String[] tokens = str.split("#");
            if(tokens[0].equals("student-add")){
                String id_student = tokens[1];
                String name_student = tokens[2];
                float ipk = Float.parseFloat(tokens[3]);
                int credit = Integer.parseInt(tokens[4]);
                Student student = new Student(id_student, name_student, ipk, credit);
                students.add(student);

            }else if(tokens[0].equals("student-show-all")){
                for(Student student : students){
                    System.out.println(student.getIdStudent() + "|" + student.getNameStudent() + "|" + student.getIpk() + "|" + student.getCredit());
                }
            }
            
        }

        sc.close();

    }

}