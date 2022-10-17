package ku.cs.application.models;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reportList;
    public ReportList() {
        reportList = new ArrayList<>();
    }
    public void add(Report report){
        reportList.add(report);
    }
    public void remove(Report report){
        reportList.remove(report);
    }

    public ArrayList<Report> getReportList() {
        return reportList;
    }

    @Override
    public String toString() {
        return "ReportList{" +
                "reportList=" + reportList +
                '}';
    }
}
