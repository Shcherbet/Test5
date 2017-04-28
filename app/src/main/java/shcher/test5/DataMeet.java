package shcher.test5;

import java.util.ArrayList;

/**
 * Created by U_M0J35 on 28.04.2017.
 */

public class DataMeet {

    private String dateList;
    private ArrayList<String> allMeetTime;


    public DataMeet() {

    }
    public DataMeet(String dateList, ArrayList<String> allMeetTime) {
        this.dateList = dateList;
        this.allMeetTime = allMeetTime;
    }



    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public ArrayList<String> getAllMeetTime() {
        return allMeetTime;
    }

    public void setAllMeetTime(ArrayList<String> allMeetTime) {
        this.allMeetTime = allMeetTime;
    }




}
