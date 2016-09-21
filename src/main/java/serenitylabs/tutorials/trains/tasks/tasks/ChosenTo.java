package serenitylabs.tutorials.trains.tasks.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import serenitylabs.tutorials.trains.page.BuyTicketsPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by sapurani on 9/21/2016.
 */
public class ChosenTo implements Task {
    private BuyTicketsPage buyTicketsPage;

    @Override
    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(Open.browserOn(buyTicketsPage));

    }

    public static Performable bookATicket() {
        return instrumented(ChosenTo.class);
    }
}
