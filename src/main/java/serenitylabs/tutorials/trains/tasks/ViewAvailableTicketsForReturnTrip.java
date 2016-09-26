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
public class ViewAvailableTicketsForReturnTrip implements Task {
    private final String fromStation;
    private final String toStation;
    private final String returnDate;

    public ViewAvailableTicketsForReturnTrip(String origin, String destination, String returnDate) {
        this.fromStation=origin;
        this.toStation=destination;
        this.returnDate=returnDate;
    }



    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(CookieMessageWindow.close());

        actor.attemptsTo(
                Enter.theValue(fromStation).into(TicketTypeForm.ORIGIN).thenHit(Keys.TAB),
                Enter.theValue(toStation).into(TicketTypeForm.DEPARTURE).thenHit(Keys.TAB),
                Click.on(TicketTypeForm.RETURN),
               // Enter.theValue(returnDate).into(TicketTypeForm.RETURN_DATE),
                Click.on(TicketTypeForm.BUY_TICKETS)
        );
    }

    public static ViewAvailableTicketsBuilder from(String origin) {
        return new  ViewAvailableTicketsBuilder().from(origin);
    }

    public static ViewAvailableTicketsBuilder to(String destination) {
        return  new  ViewAvailableTicketsBuilder().to(destination);
    }

    public static ViewAvailableTicketsForReturnTrip returnDate(String returnDate) {
        return  new  ViewAvailableTicketsBuilder().returnDate(returnDate);
    }

    public static class ViewAvailableTicketsBuilder {

        private String destination;
        private String origin;

        public ViewAvailableTicketsBuilder(String origin) {
            this.origin = origin;
        }

        public ViewAvailableTicketsBuilder(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }

        public ViewAvailableTicketsBuilder() {

        }


        public ViewAvailableTicketsBuilder from(String origin) {
            return new ViewAvailableTicketsBuilder(origin);
        }

        public ViewAvailableTicketsBuilder to(String destination) {
            return new ViewAvailableTicketsBuilder(origin, destination);
        }

        public ViewAvailableTicketsForReturnTrip returnDate(String returnDate) {
            return  new ViewAvailableTicketsForReturnTrip(origin,destination,returnDate);
        }

    }
}
