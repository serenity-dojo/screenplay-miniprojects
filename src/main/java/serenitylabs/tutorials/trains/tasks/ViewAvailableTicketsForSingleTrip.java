package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import serenitylabs.tutorials.trains.ui.TicketTypeForm;

/**
 * Created by sapurani on 9/21/2016.
 */
public class ViewAvailableTicketsForSingleTrip implements Task {
    private final String fromStation;
    private final String toStation;

    public  ViewAvailableTicketsForSingleTrip(String origin, String destination) {
        this.fromStation=origin;
        this.toStation=destination;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(CookieMessageWindow.close());

        actor.attemptsTo(
                Enter.theValue(fromStation).into(TicketTypeForm.ORIGIN).thenHit(Keys.TAB),
                Enter.theValue(toStation).into(TicketTypeForm.DEPARTURE).thenHit(Keys.TAB),
                Click.on(TicketTypeForm.BUY_TICKETS)
        );
    }

    public static ViewAvailableTicketsForSingleTripBuilder from(String origin) {
        return new  ViewAvailableTicketsForSingleTripBuilder().from(origin);
    }

    public static ViewAvailableTicketsForSingleTrip to(String destination) {
        return  new  ViewAvailableTicketsForSingleTripBuilder().to(destination);
    }



    public static class ViewAvailableTicketsForSingleTripBuilder {

        private String destination;
        private String origin;

        public ViewAvailableTicketsForSingleTripBuilder(String origin) {
            this.origin = origin;
        }

        public ViewAvailableTicketsForSingleTripBuilder(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }

        public ViewAvailableTicketsForSingleTripBuilder() {

        }


        public ViewAvailableTicketsForSingleTripBuilder from(String origin) {
            return new ViewAvailableTicketsForSingleTripBuilder(origin);
        }

        public ViewAvailableTicketsForSingleTrip to(String destination) {
            return new ViewAvailableTicketsForSingleTrip(origin, destination);
        }


    }
}
