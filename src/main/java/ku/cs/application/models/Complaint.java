package ku.cs.application.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Complaint {
    private String headComplaint; //หัวเรื่อง
    private String bodyComplaint; //เนื้อหาที่จะร้องเรียน
    //private String detailComplaint; //
    private String CATEGORY;
    private String nameWriter;

    private int rating;
    private String time;
    private boolean isBan;
    public Complaint(String headComplaint, String bodyComplaint,
                     String CATEGORY,String nameWriter) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.CATEGORY = CATEGORY;
        this.nameWriter = nameWriter;
        isBan = false;
    }

    public Complaint(String headComplaint, String bodyComplaint, String CATEGORY, String nameWriter, String time) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.CATEGORY = CATEGORY;
        this.nameWriter = nameWriter;
        this.time = time;
        isBan = false;
    }

    public void recordTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
        this.time = now.format(formatter);

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNameWriter() {
        return nameWriter;
    }

    public void setNameWriter(String nameWriter) {
        this.nameWriter = nameWriter;
    }

    public Complaint(String headComplaint, String bodyComplaint, String CATEGORY) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.CATEGORY = CATEGORY;
    }

    public void donBan(){
        isBan = true;
    }
    public String getHeadComplaint() {
        return headComplaint;
    }
    public void setHeadComplaint(String headComplaint) {
        this.headComplaint = headComplaint;
    }
    public String getBodyComplaint() {
        return bodyComplaint;
    }
    public void setBodyComplaint(String bodyComplaint) {
        this.bodyComplaint = bodyComplaint;
    }
//    public String getDetailComplaint() {
//        return detailComplaint;
//    }
//    public void setDetailComplaint(String detailComplaint) {
//        this.detailComplaint = detailComplaint;
//    }
    public String getCATEGORY() {
        return CATEGORY;
    }
    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }
    @Override
    public String toString() {
        return "Complaint{" +
                "headComplaint='" + headComplaint + '\'' +
                ", bodyComplaint='" + bodyComplaint + '\''
                +'\'' +
                ", CATEGORY='" + CATEGORY + '\'' +
                '}';
    }
}
