package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author NIM Nama
 */
public class Student {

    private String id;
    private String name; 
    private String address;
    private String prodi;

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

}