package ku.cs.application.models;

public class Officer extends Users{
    private String role;


    public Officer(String name, String id, String username, String password, String lastTimeLogin, String userImage, String role) {
        super(name, id, username, password, false, lastTimeLogin, userImage, false, true);
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
