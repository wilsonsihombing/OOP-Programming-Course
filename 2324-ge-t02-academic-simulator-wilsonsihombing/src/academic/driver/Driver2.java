package academic.driver;
import java.util.ArrayList;
import java.util.Scanner;

import academic.model.Student;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class Driver2 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();

        while(true){
            String str = sc.nextLine();
            if(str.equals("---")){
                break;
            }

            String[] tokens = str.split("#");
            String id_student = tokens[0];
            String name_student = tokens[1];
            String year = tokens[2];
            String prodi = tokens[3];

            Student student = new Student(id_student, name_student, year, prodi);
            students.add(student);


        }

        for(Student student : students){
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getAddress() + "|" + student.getProdi());
        }
        
        sc.close();

    }

}