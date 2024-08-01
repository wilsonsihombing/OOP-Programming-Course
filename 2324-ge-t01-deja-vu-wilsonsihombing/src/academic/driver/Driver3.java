package academic.driver;

/**
 * @author NIM Nama
 */

import java.util.*;
import academic.model.Enrollment;

public class Driver3 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);

        String kodematkul = sc.nextLine();
        String Nim = sc.nextLine();
        String year = sc.nextLine();
        String semester = sc.nextLine();

        Enrollment enrollment = new Enrollment(kodematkul , Nim , year , semester);
        System.out.println(enrollment.toString());

        sc.close();

    }

}