package ku.cs.application.models;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Complaint implements Comparable<Complaint> {
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

    private String solution;

    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint, String category, String nameWriter,
                     String time, boolean done, boolean inProgress, boolean unmanaged, int rating, boolean isBan
            ,String solution, String imageUrl) {
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
        this.solution = solution;
        this.imageUrl = imageUrl;
    }//CONSTRUCTOR FOR READ,WRITE DATA

    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint,
                     String category, String nameWriter) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.fixComplaint = fixComplaint;
        this.category = category;
        this.nameWriter = nameWriter;
        done = false;
        inProgress = false;
        unmanaged = true;
        isBan = false;
    } //construct ทั่วไป
    public Complaint(String headComplaint, String bodyComplaint, String fixComplaint, String category, String nameWriter, String time) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.fixComplaint = fixComplaint;
        this.category = category;
        this.nameWriter = nameWriter;
        this.time = time;
        done = false;
        inProgress = false;
        unmanaged = true;
        isBan = false;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isBan() {
        return isBan;
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


    public Complaint(String headComplaint, String bodyComplaint, String category) {
        this.headComplaint = headComplaint;
        this.bodyComplaint = bodyComplaint;
        this.category = category;
    }


    public String getHeadComplaint() {
        return headComplaint;
    }

    public String getBodyComplaint() {
        return bodyComplaint;
    }



    public String getCategory() {
        return category;
    }


//officer
public String getStatus(){
    if (isDone()){
        //System.out.println("111");
        return "ดำเนินการแล้ว";
    }else if (isInProgress() == true){
        //System.out.println("11");
        return "อยู่ระหว่างการดำเนินการแล้ว";
    }else if (isUnmanaged() == true){
        //System.out.println("1");
        return "ยังไม่ถูกจัดการ";
    }
    return "";
}
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


    @Override
    public String toString() {
        return headComplaint +" "+time +" "+ rating;
    }
    public String getFixComplaint() {
        return fixComplaint;
    }


    @Override
    public int compareTo(Complaint o) {
        return Integer.compare(rating,o.getRating());
    }
    public double getTimeToSecond(){
        String[] timeArr = new String[time.length()];
        timeArr = time.split("-");
        return (Double.parseDouble(timeArr[0])*3600)+(Double.parseDouble(timeArr[1])*60)
                +Double.parseDouble(timeArr[2])+(Double.parseDouble(timeArr[3])*86400)
                +(Double.parseDouble(timeArr[4])*2629743);
    }


    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    private String imageUrl;

    public String getImage(){
        String url;
        if (imageUrl.equals("ku/cs/complaint_images/default_complaint.png")){
            url =  getClass().getResource("ku/cs/complaint_images/default_complaint.png").toExternalForm();
        }else {
            url = "file:"+ imageUrl;
        }
        return url;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
