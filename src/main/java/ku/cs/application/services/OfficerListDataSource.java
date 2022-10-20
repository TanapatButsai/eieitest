package ku.cs.application.services;

import ku.cs.application.models.Office;
import ku.cs.application.models.OfficeList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OfficerListDataSource implements DataSource<OfficeList>{
    private String directoryName;
    private String fileName;

    public OfficerListDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
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
    public OfficeList readData() {
        OfficeList list = new OfficeList();
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
                Office office = new Office(
                        data[0].trim(), new ArrayList<String>(List.of(data[1].trim().split("-"))));
                list.addOfficer(office);
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
    public void writeData(OfficeList officerIDList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for (Office office : officerIDList.getAllOfficerID()) {
                StringBuilder officerIDArrayList = new StringBuilder();
                for (String s : office.getAllOfficerUsername()){
                    officerIDArrayList.append(s).append("-");
                }
                String line = office.getRole() +","
                        + officerIDArrayList;
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
