package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 */


public class Course {
    private String code;
    private String name;
    private int credits;
    private String passinggrade;

    public Course(String code, String name, int credits, String passinggrade) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.passinggrade = passinggrade;
    }

    public String toString(){
        return code + "|" + name + "|" + credits + "|" + passinggrade;
    }

}