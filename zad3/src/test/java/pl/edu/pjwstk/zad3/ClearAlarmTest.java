import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith ( JUnit4. class )
public class ClearAlarmTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    public Alarm alarmClock = new AlarmClock();

    @Mock
    public Time time;

    @org.junit.Test
    public void firstClearRingTest() {
        String ringingTime = "210001";
        alarmClock.addAlarmTime(ringingTime);
        expect(time.getTime()).andReturn("210002").anyTimes();
        replay(time);
        assertEquals(true, alarmClock.alarmRing());
        alarmClock.clearAlarmTime(ringingTime);
        assertEquals(false, alarmClock.alarmRing());
        verify(time);
    }

    @org.junit.Test
    public void secondClearRingTest() {
        String ringingTime = "222431";
        alarmClock.addAlarmTime(ringingTime);
        expect(time.getTime()).andReturn("222433").anyTimes();
        replay(time);
        assertEquals(true, alarmClock.alarmRing());
        alarmClock.clearAlarmTime(ringingTime);
        assertEquals(false, alarmClock.alarmRing());
        verify(time);
    }
}
