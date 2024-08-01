package academic.driver;

import java.util.Scanner;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */

import java.util.*;
import academic.model.Course;

public class Driver1 {

    public static void main(String[] _args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<Course>();

        while(true){
            String str = sc.nextLine();
            if(str.equals("---")){
                break;
            }
            String[] tokens = str.split("#");
            String id_course = tokens[0];
            String name_course = tokens[1];
            int sks = Integer.parseInt(tokens[2]);
            String grade = tokens[3];

            Course course = new Course(id_course, name_course, sks, grade);
            courses.add(course);

        }

        for(Course course : courses){
            System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getSks() + "|" + course.getGrade());
        }
        
        sc.close();
    }

    
}