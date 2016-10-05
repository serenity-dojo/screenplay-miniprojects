package serenitylabs.tutorials.insurance.car.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import serenitylabs.tutorials.insurance.car.page.NewInsurancePage;

/**
 * Created by sapurani on 10/5/2016.
 */
public class StartANewQuote implements Task{
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(NewInsurancePage.NEW_CAR_INSURANCE)
        );

    }

    public static Performable forCarInsurance() {
        return Instrumented.instanceOf(StartANewQuote.class).newInstance();
    }
}
