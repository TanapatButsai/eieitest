package ku.cs.application.services;

import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ComplaintListDataSource implements DataSource<ComplaintList>{
    private String directoryName;
    private String fileName;
    public ComplaintListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    public ComplaintListDataSource() {
        ComplaintList ComplaintList = new ComplaintList();
        readData();
    }

    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public ComplaintListDataSource() {
//        ComplaintList ComplaintList = new ComplaintList();
//        readData();
//    }
    @Override
    public ComplaintList readData() {
        ComplaintList list = new ComplaintList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                if (data[6].equals("isDone")) continue;
                String headComplaint = data[0].trim();
                String bodyComplaint = data[1].trim();
                String fixComplaint = data[2].trim();
                String category = data[3].trim();
                String nameWriter = data[4].trim();
                String time = data[5].trim();
                boolean done = Boolean.parseBoolean(data[6].trim());
                boolean inProgress = Boolean.parseBoolean(data[7].trim());
                boolean unmanaged = Boolean.parseBoolean(data[8].trim());
                int rating = Integer.parseInt(data[9].trim());
                boolean isBan = Boolean.parseBoolean(data[10].trim());
                String solution = data[11].trim();
                String url = data[12].trim().replace("\\",File.separator);
                Complaint complaint = new Complaint(headComplaint,bodyComplaint,fixComplaint,category,nameWriter
                        ,time,done,inProgress,unmanaged,rating,isBan,solution,url);
                list.add(complaint);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public void writeData(ComplaintList complaintList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            String header = "topic,body,detail,category,usernameWriter,time,isDone,isInProgress,isUnmanaged,vote,isBan,isOfficer";
            buffer.append(header);
            buffer.newLine();
            for (Complaint complaint : complaintList.getAllComplaint()) {
                String line = complaint.getHeadComplaint() + ","
                        + complaint.getBodyComplaint() + ","
                        + complaint.getFixComplaint() + ","
                        + complaint.getCategory()+","
                        + complaint.getNameWriter()+","
                        + complaint.getTime()+","
                        + complaint.isDone()+","
                        + complaint.isInProgress()+","
                        + complaint.isUnmanaged()+","
                        + complaint.getRating()+","
                        + complaint.isBan()+","
                        + complaint.getSolution() +","
                        + complaint.getImageUrl().replace(File.separator,"\\");
                buffer.append(line);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "ComplaintListDataSource{" +
                "directoryName='" + directoryName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
    //--------

}
