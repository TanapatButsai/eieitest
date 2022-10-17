package ku.cs.application.services;

import ku.cs.application.models.*;

import java.io.*;

public class ReportListDataSource implements DataSource<ReportList> {
    private String directoryName;
    private String fileName;
    private ReportList reportList;

    public ReportListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public ReportListDataSource() {
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

    @Override
    public ReportList readData() {
        ReportList list = new ReportList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            //reporterUsername,reportedUsername,reason,objectID,reportTime
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String checkHeader = "reporterUsernamereportedUsernamereason";
                if (checkHeader.equals((data[0].trim())+(data[1].trim())+(data[2].trim()))) continue;
                String reporterUsername = data[0].trim();
                String reportedUsername = data[1].trim();
                String reason = data[2].trim();
                String objectID = data[3].trim();
                String timeReported = data[4].trim();
                Report report = new Report(reporterUsername,reportedUsername,reason,objectID,timeReported);
                list.add(report);
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
    public void writeData(ReportList reportList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
//        newUser = new Users("jaja123","456","780","123");
//        String newUserString = newUser.getName()+","+newUser.getId()+","+ newUser.getUsername()+","+ newUser.getPassword();
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            String header = "reporterUsername,reportedUsername,reason,objectID,reportTime";
            buffer.append(header);
            buffer.newLine();
            for (Report report : reportList.getReportList()) {
                String line = report.getReporterUsername() + ","
                        + report.getReportedUsername() + ","
                        + report.getReason() + ","
                        + report.getObjectID() + ","
                        + report.getReportTime();

                buffer.append(line);
                buffer.newLine();
            }
//            buffer.append(newUserString);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
