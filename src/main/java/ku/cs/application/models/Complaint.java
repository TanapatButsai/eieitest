package ku.cs.application.models;

public class Complaint {
    private String headComplaint; //หัวเรื่อง
    private String bodyComplaint; //เนื้อหาที่จะร้องเรียน
    //private String detailComplaint; //
    private String CATEGORY;
    private String nameWriter;

    private int rating;

    private boolean isBan;
    public Complaint(String headComplaint, String bodyComplaint,
                     String CATEGORY,String nameWriter) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.CATEGORY = CATEGORY;
        this.nameWriter = nameWriter;
        isBan = false;
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
