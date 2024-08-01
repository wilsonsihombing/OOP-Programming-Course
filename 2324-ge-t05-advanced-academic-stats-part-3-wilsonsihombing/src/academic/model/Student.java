package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */
public class Student  extends Person{

    //private String id;
    //private String name;
    private String year;
    //private String prodi;
    private double GPA;
    private int sks;

    public Student(String id, String name, String address, String prodi) {
        // this.id = id;
        // this.name = name;
        super(id, name, prodi);
        this.year = address;
        // this.prodi = prodi;
    } 

    
    // public String getId() {
    //     return this.id;
    // }

    // public String getName() {
    //     return this.name;
    // }

    @Override
    public String getType() {
        return "Student";
    }

    public String getAddress() {
        return this.year;
    }

    // public String getProdi() {
    //     return this.prodi;
    // }

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