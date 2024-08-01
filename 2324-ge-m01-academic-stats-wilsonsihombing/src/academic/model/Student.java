package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 12S22028 Tennov Pakpahan
 */
public class Student {

    private String id;
    private String name;
    private String address;
    private String prodi;
    private double GPA;
    private int sks;

    public Student(String id, String name, String address, String prodi) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.prodi = prodi;
    } 

    
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getProdi() {
        return this.prodi;
    }

    public void Setgpa(Double gpa){
        this.GPA = GPA;
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

}