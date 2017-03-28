package pl.edu.pjwstk.zad4;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;



public class AlarmClockSteps {
    Alarm alarm;
    Time time;

    @Given("Given there is a $time")
    public void setTime(String time)
    {
        alarm.setTime(time);
    }

    @When("When clock was given an $alarmTime")
    public void alarmsAdded(String alarmTime)
    {
        alarm = new AlarmClock();
        time = mock(Time.class);
        given(time.getTime()).willReturn(alarmTime);
        alarm.addAlarmTime(time.toString());
    }

    @Then("Then clock should ring: $summary")
    public void summary(Boolean summary)
    {
        assertEquals(summary, alarm.alarmRing());
    }
}

