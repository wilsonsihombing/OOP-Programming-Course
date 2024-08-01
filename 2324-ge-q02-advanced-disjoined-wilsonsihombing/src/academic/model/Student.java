package academic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */
public class Student  extends Person{
    private String year; 
    private double GPA;
    private int sks;
 
    public Student(String id, String name, String address, String prodi) {
        super(id, name, prodi);
        this.year = address;
    } 

    @Override
    public String getType() {
        return "Student";
    }

    public String getAddress() {
        return this.year;
    }
    
    public void Setsks(int sks){
        this.sks = sks;
    }

    public Double getGPA(){
        return this.GPA;
    }

    public int getsks(){
        return this.sks;
    }

    public static void SortingStudent(ArrayList<Student> students){
        Collections.sort(students, new Comparator<Student>(){
            public int compare(Student s1, Student s2){
                return s1.getId().compareTo(s2.getId());
            }
        });
    }
}