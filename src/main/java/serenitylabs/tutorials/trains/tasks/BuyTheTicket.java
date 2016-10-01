package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage.CHANGE_OPTION;
import static serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage.DIRECT_OPTION;
import static serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage.INBOUND_JOURNEY_PRICE;
import static serenitylabs.tutorials.trains.ui.OutboundJourneySummaryPage.OUTBOUND_JOURNEY_PRICE;

/**
 * Created by Vikrant Dheer on 9/27/2016.
 */
public class BuyTheTicket implements Task {

    private final Target summaryPage;

    @Step("{0} tries to choose #summaryPage")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(summaryPage)
        );
    }

    public BuyTheTicket(Target summaryPage) {
        this.summaryPage = summaryPage;

    }

    public static Performable forOneSide() {
        return instrumented(BuyTheTicket.class, OUTBOUND_JOURNEY_PRICE);
    }

    public static Performable forReturn() {
        return instrumented(BuyTheTicket.class, INBOUND_JOURNEY_PRICE);
    }

    public static Performable withDirectOption() {
        return instrumented(BuyTheTicket.class, DIRECT_OPTION);
    }

    public static Performable withOneChangeOption() {
        return instrumented(BuyTheTicket.class, CHANGE_OPTION);
    }
}
