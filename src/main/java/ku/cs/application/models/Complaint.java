package ku.cs.application.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Complaint {
    private String headComplaint; //หัวเรื่อง
    private String bodyComplaint; //เนื้อหาที่จะร้องเรียน
    //private String detailComplaint; //
    private String bodyComplaint1;
    private String category;
    private String nameWriter;

    private int rating;
    private String time;
    private boolean isBan;
    public Complaint(String headComplaint, String bodyComplaint, String bodyComplaint1,
                     String category, String nameWriter) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.bodyComplaint1 = bodyComplaint1;
        this.category = category;
        this.nameWriter = nameWriter;
        isBan = false;
    }

    public Complaint(String headComplaint, String bodyComplaint, String bodyComplaint1, String category, String nameWriter, String time) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.bodyComplaint1 = bodyComplaint1;
        this.category = category;
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

    public Complaint(String headComplaint, String bodyComplaint, String category) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.category = category;
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
//    @Override
//    public String toString() {
//        return "Complaint{" +
//                "headComplaint='" + headComplaint + '\'' +
//                ", bodyComplaint='" + bodyComplaint + '\''
//                +'\'' +
//                ", category='" + category + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Complaint{" +
                "headComplaint='" + headComplaint + '\'' +
                ", bodyComplaint='" + bodyComplaint + '\'' +
                ", bodyComplaint1='" + bodyComplaint1 + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getBodyComplaint1() {
        return bodyComplaint1;
    }

    public void setBodyComplaint1(String bodyComplaint1) {
        this.bodyComplaint1 = bodyComplaint1;
    }
}
