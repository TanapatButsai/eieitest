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
        Report reportRemove = findReport(report);
        reportList.remove(reportRemove);
    }

    public ArrayList<Report> getReportList() {
        return reportList;
    }
    public Report findReport (Report report){
        for (Report report1: reportList){
            if (report.toString().equals(report1.toString())) {
                return report1;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "ReportList{" +
                "reportList=" + reportList +
                '}';
    }
}
