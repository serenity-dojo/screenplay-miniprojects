package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.JourneyDetailsPage;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class EnterReturnSchedule implements Task {

    private final String departOrArrive;
    private final int date;
    private final int hour;
    private final int minute;

    public EnterReturnSchedule(String departOrArrive, int date, int hour, int minute) {
        this.departOrArrive = departOrArrive;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    @Step("{0} plans to #departOrArrive on #date of month at #hour:#minute")
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(JourneyDetailsPage.RETURN_SCHEDULE),
                Click.on(String.format(JourneyDetailsPage.DEPART_OR_ARRIVE.getCssOrXPathSelector(), departOrArrive)),
                Click.on(String.format(JourneyDetailsPage.DATE_OF_RETURN.getCssOrXPathSelector(), date)),
                SelectFromOptions.byIndex(hour).from(JourneyDetailsPage.HOUR),
                SelectFromOptions.byValue(String.valueOf(minute)).from(JourneyDetailsPage.MINUTE),
                Click.on(JourneyDetailsPage.DONE),
                Click.on(JourneyDetailsPage.BUT_TICKETS_BUTTON)
        );
    }

    public static EnterReturnScheduleBuilder departOrArrive(String departOrArrive) {
        return new EnterReturnScheduleBuilder(departOrArrive);
    }


    public static class EnterReturnScheduleBuilder {
        private final String departorArrive;
        private int date;

        public EnterReturnScheduleBuilder(String departOrArrive) {
            this.departorArrive = departOrArrive.equalsIgnoreCase("Arrive") ? "arrive" : "depart";
        }

        public EnterReturnScheduleBuilder On(LocalDate returnTravelDate) {
            date = returnTravelDate.getDayOfMonth();
            return this;
        }

        public Performable at(LocalTime returnTravelTimings) {
            return Instrumented.instanceOf(EnterReturnSchedule.class).withProperties(departorArrive, date, returnTravelTimings.getHour(), returnTravelTimings.getMinute());
        }
    }
}
