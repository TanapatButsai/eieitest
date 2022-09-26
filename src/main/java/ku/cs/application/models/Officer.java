package ku.cs.application.models;

public class Officer extends Complaint{
    private String officerName;
    private String role;
    private String topic;
    private boolean done;
    private boolean inProgress;
    private boolean unmanaged;

    public Officer(String name,String CATEGORY,String headComplaint, String bodyComplaint,boolean done, boolean inProgress, boolean unmanaged) {
        super(headComplaint, bodyComplaint, CATEGORY);
        this.role = CATEGORY;
        this.topic = headComplaint;
        this.officerName = name;
        this.done = done;
        this.inProgress = inProgress;
        this.unmanaged = unmanaged;
    }
    //setProcess
    public boolean setDone() {
        done = true;
        inProgress = false;
        unmanaged = false;
        return done;
    }
    public boolean setInProgress() {
        inProgress = true;
        done = false;
        unmanaged =false;
        return inProgress;
    }
    public boolean setUnmanaged() {
        unmanaged = true;
        done = false;
        inProgress =false;
        return unmanaged;
    }
    public boolean isDone() {
        return done;
    }
    public boolean isInProgress() {
        return inProgress;
    }
    public boolean isUnmanaged() {
        return unmanaged;
    }

    public String getName() {
        return officerName;
    }
    public String getTopic() {
        return topic;
    }
    public  String getBody(){
        return getBodyComplaint();
    }
    public String getRole() {
        return role;
    }
    public String getStatus(){
        if (isDone()){
            System.out.println("111");
            return "ดำเนินการแล้ว";
        }else if (isInProgress() == true){
            System.out.println("11");
            return "อยู่ระหว่างการดำเนินการแล้ว";
        }else if (isUnmanaged() == true){
            System.out.println("1");
            return "ยังไม่ถูกจัดการ";
        }
        return "";
    }
    @Override
    public String toString() {
        return topic;
    }
}
