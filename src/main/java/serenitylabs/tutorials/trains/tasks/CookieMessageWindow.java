package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import serenitylabs.tutorials.trains.ui.TicketTypeForm;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.not;

    /**
     * Created by sapurani on 9/21/2016.
     */
    public class CookieMessageWindow implements Task{
        @Override
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Click.on(TicketTypeForm.ACCEPT_COOKIES)
            );

            actor.should(eventually(seeThat(the(Target.the("foo").locatedBy(".cookies button")),not(isCurrentlyVisible()))));

        }

        public static Performable close() {
            return instrumented(CookieMessageWindow.class);
        }
    }
