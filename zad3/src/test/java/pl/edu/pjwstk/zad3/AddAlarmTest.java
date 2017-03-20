import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith ( JUnit4. class )
public class AddAlarmTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    public Alarm alarmClock = new AlarmClock();

    @Mock
    public Time time;

    @org.junit.Test
    public void firstRingTest() {
        String ringingTime = "210001";  //godzina w formacie HH:mm:ss
        alarmClock.addAlarmTime(ringingTime);
        expect(time.getTime()).andReturn("210001").times(2)
                .andReturn("203500")
                .andReturn("210002");
        replay(time);
        assertEquals(true, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(true, alarmClock.alarmRing());
        verify(time);
    }

    @org.junit.Test
    public void secondRingTest() {
        String secondRinging = "222431"; //godzina w formacie HH:mm:ss
        alarmClock.addAlarmTime(secondRinging);
        expect(time.getTime())
                .andReturn("222431").times(4)
                .andReturn("210001")
                .andReturn("222433")
                .andReturn("222431");
        replay(time);
        assertEquals(true, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());
        assertEquals(true, alarmClock.alarmRing());
        assertEquals(false, alarmClock.alarmRing());

        verify(time);
    }
}