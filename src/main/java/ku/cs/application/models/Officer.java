package ku.cs.application.models;

public class Officer extends Complaint{
    private String officerName;
    private String role;
    private String topic;

    public Officer(String name,String CATEGORY,String headComplaint, String bodyComplaint) {
        super(headComplaint, bodyComplaint, CATEGORY);
        this.role = CATEGORY;
        this.topic = headComplaint;
        this.officerName = name;
    }

    public String getName() {
        return officerName;
    }

    public String getTopic() {
        return topic;
    }

    public String getRole() {
        if (role.equals("officer1")){
            return "เรื่องร้องเรียงทั่วไป";
        } else if (role.equals("officer2")) {
            return "เรื่องร้องเรียงอาจารย์/บุคลาการ";
        } else if (role.equals("officer3")){
            return "เรื่องร้องเรียงอาคาร แลพสถานที่";
        } else if (role.equals("officer4")) {
            return  "เรื่องร้องเรียงการลงทะเบียนเรียน";
        }
        return "";
    }
}
