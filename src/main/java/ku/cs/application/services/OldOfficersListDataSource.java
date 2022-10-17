//package ku.cs.application.services;
//
//import ku.cs.application.models.Officer;
//import ku.cs.application.models.OfficerList;
//
//import java.io.*;
//
//public class OfficersListDataSource implements DataSource<OfficerList>{
//    private String directoryName;
//    private String fileName;
//
//    public OfficersListDataSource(String directoryName, String fileName){
//        this.directoryName = directoryName;
//        this.fileName = fileName;
//        checkFileIsExisted();
//    }
//
//    private void checkFileIsExisted() {
//        File file = new File(directoryName);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        String filePath = directoryName + File.separator + fileName;
//        file = new File(filePath);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @Override
//    public OfficerList readData() {
//        OfficerList list = new OfficerList();
//        String filePath = directoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader reader = null;
//        BufferedReader buffer = null;
//
//        try {
//            reader = new FileReader(file);
//            buffer = new BufferedReader(reader);
//
//            String line = "";
//            while ((line = buffer.readLine()) != null) {
//                String[] data = line.split(",");
//                boolean isDone = false;
//                boolean isInProgress = false;
//                boolean isUnmanaged = false;
//                if (data[5].trim().equals("true")) {
//                    isDone = true;
//                }else if (data[6].trim().equals("true")) {
//                    isInProgress = true;
//                }if (data[7].trim().equals("true")) {
//                    isUnmanaged = true;
//                }
//                Officer officer = new Officer(
//                        data[0].trim(),
//                        data[1].trim(),
//                        data[2].trim(),
//                        data[3].trim(),
//                        data[4].trim(),
//                        isDone,
//                        isInProgress,
//                        isUnmanaged
//                        );
//                list.add(officer);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                buffer.close();
//                reader.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return list;
//    }
//
////    @Override
////    public OfficerList readData2() {
////        return null;
////    }
////
////    @Override
////    public OfficerList readData3() {
////        return null;
////    }
////
////    @Override
////    public OfficerList readData4() {
////        return null;
////    }
//
//    @Override
//    public void writeData(OfficerList officerList) {
//        String filePath = directoryName + File.separator + fileName;
//        File file = new File(filePath);
//
//        FileWriter writer = null;
//        BufferedWriter buffer = null;
//        try{
//            writer = new FileWriter(file);
//            buffer = new BufferedWriter(writer);
//            for (Officer officer : officerList.getAllOfficer()) {
//                String line = officer.getName() + ","
//                        + officer.getRole() + ","
//                        + officer.getTopic()+","
//                        + officer.getBody()+","
//                        + officer.getFixComplaint()+","
//                        + officer.isDone()+","
//                        + officer.isInProgress()+","
//                        + officer.isUnmanaged()+",";
//
//                buffer.append(line);
//                buffer.newLine();
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            try {
//                buffer.close();
//                writer.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//    }
