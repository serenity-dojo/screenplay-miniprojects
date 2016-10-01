package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.JourneyDetailsPage;
import serenitylabs.tutorials.trains.ui.JourneyPage;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class ChosenTo implements Task {

    public JourneyPage journeyPage;

    @Step("{0} has chosen to book a ticket")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(journeyPage),
                Click.on(JourneyDetailsPage.CLOSE_COOKIES)
        );

    }

    public static Performable bookATicket() {
        return Instrumented.instanceOf(ChosenTo.class).newInstance();
    }
}
