package ku.cs.application.models;

public class Officer extends Complaint{
    private String role;
    private String topic;

    public Officer(String headComplaint, String bodyComplaint, String CATEGORY, String nameWriter) {
        super(headComplaint, bodyComplaint, CATEGORY, nameWriter);
        this.role = CATEGORY;
        this.topic = headComplaint;

    }

    public String getRole() {
        return role;
    }
}
