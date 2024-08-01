package academic.model;

/**
 * @author 12S22011 Wilson Eksaudi Sihombing
 * @author 
 */

public class Lecture extends Person{
    // private String id;
    // private String name;
    private String initial;
    private String email;
    //private String prodi;


public Lecture(String id, String name, String initial, String email, String study_program){
    // this.id = id;
    // this.name = name;
    super(id, name, study_program);
    this.initial = initial;
    this.email = email;
    //this.prodi = study_program;
}

// public String getId() {
//     return this.id;
// } 

// public String getName() {
//     return this.name;
// }
@Override
public String getType() {
    return "Lecture";
}

public String getInitial() {
    return this.initial;
}

public String getEmail() {
    return this.email;
}

// public String getStudy_program() {
//     return this.study_program;
// }

}


