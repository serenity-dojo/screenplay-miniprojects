package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.OutboundJourneySummaryPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by Vikrant Dheer on 9/27/2016.
 */
public class BuyTheTicket implements Task {

    @Step("{0} tries to buy ticket")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(OutboundJourneySummaryPage.JOURNEY_PRICE)
        );
    }

    public static Performable forOneSide() {
        return instrumented(BuyTheTicket.class);
    }
}
