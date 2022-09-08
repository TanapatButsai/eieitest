package ku.cs.application.models;

public class Complaint {
    private String information;
    private String intro;

    public Complaint(String information, String intro) {
        this.information = information;
        this.intro = intro;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
