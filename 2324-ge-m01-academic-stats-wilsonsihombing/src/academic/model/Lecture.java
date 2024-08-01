package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 12S22028 Tennov Pakpahan
 */

public class Lecture {
    private String id;
    private String name;
    private String initial;
    private String email;
    private String study_program;


public Lecture(String id, String name, String initial, String email, String study_program){
    this.id = id;
    this.name = name;
    this.initial = initial;
    this.email = email;
    this.study_program = study_program;
}

public String getId() {
    return this.id;
} 

public String getName() {
    return this.name;
}

public String getInitial() {
    return this.initial;
}

public String getEmail() {
    return this.email;
}

public String getStudy_program() {
    return this.study_program;
}

}


