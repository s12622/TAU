import java.util.ArrayList;
import java.util.List;

public class AlarmClock implements Alarm
{
    public Time time;
    boolean alreadyRinging;
    boolean ring;

    List<String> timeList = new ArrayList<String>();

    public boolean alarmRing()
    {
        String t = time.getTime();
        ring = false;

        for(int i = 0; i < timeList.size(); i++)
        {
            String ringingTime = timeList.get(i);
            int mistake = Integer.parseInt(t) - Integer.parseInt(ringingTime);

            if((timeList.get(i).equals(t)) || mistake >= 0 && mistake <=2)
            {
                if(!alreadyRinging)
                {
                    ring = true;
                    alreadyRinging = true;
                }
                else
                {
                    ring = false;
                }
            }
            else
            {
                alreadyRinging = false;
                ring = false;
            }
        }
        return ring;
    }

    public void clearAlarmTime(String time) {
        timeList.remove(time);
    }

    public void addAlarmTime(String time) {
        timeList.add(time);
    }


}