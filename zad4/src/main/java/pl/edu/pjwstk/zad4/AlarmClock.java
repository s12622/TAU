package pl.edu.pjwstk.zad4;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock implements Alarm
{
    boolean alreadyRinging;
    boolean ring;
    Time time;
    String timeNow;

    List<String> timeList = new ArrayList<String>();

    public boolean alarmRing()
    {
        ring = false;
        for(int i = 0; i < timeList.size(); i++)
        {
            String ringingTime = timeList.get(i);
            timeNow = time.getTime();
            int mistake = Integer.parseInt(timeNow) - Integer.parseInt(ringingTime);
            if((timeList.get(i).equals(timeNow)) || isMistaken(mistake))
            {
                if(alreadyRinging) ring = false;
                else ring = true; alreadyRinging = true;
            }
            else
            {
                ring = false;
                alreadyRinging = false;
            }
        }
        return ring;
    }

    public Boolean isMistaken(int mistake)//maksymalnie 2 sekundy
    {
        if(mistake >= 0 && mistake <=2) return true; else return false;
    }

    public void setTime(String timeNow){
        this.timeNow = timeNow;
    }

    public void clearAlarmTime(String time) {
        timeList.remove(time);
    }

    public void addAlarmTime(String time) {
        timeList.add(time);
    }


}