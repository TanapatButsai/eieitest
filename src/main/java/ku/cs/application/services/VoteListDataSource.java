package ku.cs.application.services;

import ku.cs.application.models.Office;
import ku.cs.application.models.OfficeList;
import ku.cs.application.models.Vote;
import ku.cs.application.models.VoteList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VoteListDataSource implements DataSource<VoteList>{
    private String directoryName;
    private String fileName;

    public VoteListDataSource(String directoryName, String fileName) {
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
    public VoteList readData() {
        VoteList list = new VoteList();
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
                Vote vote = new Vote(
                        data[0].trim(), new ArrayList<String>(List.of(data[1].trim().split("-"))));
                list.add(vote);
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
    public void writeData(VoteList voteList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for (Vote vote : voteList.getAllVotes()) {
                buffer.append(vote.toString().replaceAll(" ",""));
                buffer.append("-");
                buffer.newLine();
            }
//            buffer.append(newUserString);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}

