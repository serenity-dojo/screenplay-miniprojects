package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import serenitylabs.tutorials.trains.ui.JourneyDetailsPage;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class ViewTicketsForOneSideTrip implements Task {

    private final String departure;
    private final String destination;

    public ViewTicketsForOneSideTrip(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }


    @Step("{0} tries to book ticket from #departure to #destination")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(departure).into(JourneyDetailsPage.DEPARTURE).thenHit(Keys.TAB),
                Enter.theValue(destination).into(JourneyDetailsPage.DESTINATION).thenHit(Keys.TAB),
                Click.on(JourneyDetailsPage.BUT_TICKETS_BUTTON)
        );

    }

    public static ViewTheAvailableTicketsBuilder from(String departure) {
        return new ViewTheAvailableTicketsBuilder(departure);
    }

    public static class ViewTheAvailableTicketsBuilder {
        private final String departure;

        public ViewTheAvailableTicketsBuilder(String departure) {
            this.departure = departure;
        }

        public Performable to(String destination) {
            return Instrumented.instanceOf(ViewTicketsForOneSideTrip.class).withProperties(departure, destination);
        }
    }
}
