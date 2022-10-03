package ku.cs.application.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Complaint {
    private String headComplaint; //หัวเรื่อง
    private String bodyComplaint; //เนื้อหาที่จะร้องเรียน
    private String fixComplaint;
    private String category;
    private String nameWriter;
    private boolean done;
    private boolean inProgress;
    private boolean unmanaged;
    private int rating;
    private String time;
    private boolean isBan;

    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint, String category, String nameWriter, String time,
                     boolean done, boolean inProgress, boolean unmanaged, int rating, boolean isBan) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.fixComplaint = fixComplaint;
        this.category = category;
        this.nameWriter = nameWriter;
        this.done = done;
        this.inProgress = inProgress;
        this.unmanaged = unmanaged;
        this.rating = rating;
        this.time = time;
        this.isBan = isBan;
    }

    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint,
                     String category, String nameWriter) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.fixComplaint = fixComplaint;
        this.category = category;
        this.nameWriter = nameWriter;
        isBan = false;
    }

    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint, String category, String nameWriter, String time) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.fixComplaint = fixComplaint;
        this.category = category;
        this.nameWriter = nameWriter;
        this.time = time;
        isBan = false;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isUnmanaged() {
        return unmanaged;
    }

    public void setUnmanaged(boolean unmanaged) {
        this.unmanaged = unmanaged;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
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
        return headComplaint;
    }

    public String getFixComplaint() {
        return fixComplaint;
    }

    public void setFixComplaint(String fixComplaint) {
        this.fixComplaint = fixComplaint;
    }
}
