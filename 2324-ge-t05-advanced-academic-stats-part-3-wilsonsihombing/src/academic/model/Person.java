package academic.model;

public abstract class Person {
    private String id;
    private String name;
    private String prodi;


    public Person(String id, String name, String prodi) {
        this.id = id;
        this.name = name;
        this.prodi = prodi;
    }

    public abstract String getType();

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getProdi() {
        return this.prodi;
    }


}
