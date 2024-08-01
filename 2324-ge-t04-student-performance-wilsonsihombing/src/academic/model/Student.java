package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */

public class Student{
    private String id_student;
    private String name_student;
    private float ipk;
    private int credit;

    public Student(String id_student, String name_student, float ipk, int credit){
        this.id_student = id_student;
        this.name_student = name_student;
        this.ipk = ipk;
        this.credit = credit;
    }

    public String getIdStudent(){
        return this.id_student;
    }

    public String getNameStudent(){
        return this.name_student;
    }

    public float getIpk(){
        return this.ipk;
    }

    public int getCredit(){
        return this.credit;
    }

}