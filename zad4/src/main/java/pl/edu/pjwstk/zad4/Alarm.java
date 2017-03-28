package pl.edu.pjwstk.zad4;

public interface Alarm {
    public boolean alarmRing();
    public void addAlarmTime(String time);
    public void setTime(String time);
    public void clearAlarmTime(String time);
}