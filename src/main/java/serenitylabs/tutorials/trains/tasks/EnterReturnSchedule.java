package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.JourneyDetailsPage;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class EnterReturnSchedule implements Task {

    private final String departorArrive;
    private final int date;
    private final int month;
    private final int year;
    private final String hour;
    private final String minute;

    public EnterReturnSchedule(String departorArrive, int date, int month, int year, String hour, String minute) {
        this.departorArrive = departorArrive;
        this.date = date;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    @Step("{0} plans to #departorArrive on #date/#month/#year at #hour:#minute")
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(JourneyDetailsPage.RETURN_SCHEDULE),
                Click.on(String.format(JourneyDetailsPage.DEPART_OR_ARRIVE.getCssOrXPathSelector(), departorArrive)),
                Click.on(String.format(JourneyDetailsPage.DATE_OF_RETURN.getCssOrXPathSelector(), date)),
                SelectFromOptions.byValue(hour).from(JourneyDetailsPage.HOUR),
                SelectFromOptions.byValue(minute).from(JourneyDetailsPage.MINUTE),
                Click.on(JourneyDetailsPage.DONE),
                Click.on(JourneyDetailsPage.BUT_TICKETS_BUTTON)
        );
    }

    public static EnterReturnScheduleBuilder departOrArrive(String departorArrive) {
        return new EnterReturnScheduleBuilder(departorArrive);
    }


    public static class EnterReturnScheduleBuilder {
        private final String departorArrive;
        private int date;
        private int month;
        private int year;

        public EnterReturnScheduleBuilder(String departorArrive) {
            this.departorArrive = departorArrive.equalsIgnoreCase("Arrive") ? "arrive" : "depart";
        }

        public EnterReturnScheduleBuilder On(int date, int month, int year) {
            this.date = date;
            this.month = month;
            this.year = year;
            return this;
        }

        public Performable at(String hour, String minute) {
//            return new EnterReturnSchedule(departorArrive, date, month, year, hour, minute);
            return Instrumented.instanceOf(EnterReturnSchedule.class).withProperties(departorArrive, date, month, year, hour, minute);
        }
    }
}
