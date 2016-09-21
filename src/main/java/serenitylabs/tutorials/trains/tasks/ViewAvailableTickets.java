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
public class ViewAvailableTickets implements Task {
    private final String fromStation;
    private final String toStation;

    public ViewAvailableTickets(String origin, String destination) {
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

    public static ViewAvailableTicketsBuilder from(String origin) {
        return new ViewAvailableTicketsBuilder(origin);
    }

    public static class ViewAvailableTicketsBuilder{

        private final String origin;

        public ViewAvailableTicketsBuilder(String origin) {
            this.origin=origin;
        }

        public ViewAvailableTickets to(String destination) {
            return  new ViewAvailableTickets(origin,destination);
        }
    }
}
