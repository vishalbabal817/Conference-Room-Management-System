package models;

public class Slot {

    private int beginDate;
    private int beginTime;
    private int endDate;
    private int endTime;

    public Slot(int beginDate, int beginTime, int endDate, int endTime) {
        this.beginDate = beginDate;
        this.beginTime = beginTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public void setBeginDate(int beginDate) {
        this.beginDate = beginDate;
    }

    public int getBeginDate() {
        return beginDate;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
