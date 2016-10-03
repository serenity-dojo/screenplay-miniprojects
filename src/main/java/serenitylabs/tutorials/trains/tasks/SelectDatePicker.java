package serenitylabs.tutorials.trains.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import serenitylabs.tutorials.trains.ui.TicketTypeForm;

/**
 * Created by sapurani on 9/27/2016.
 */
public class SelectDatePicker implements Task{

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                  Click.on(TicketTypeForm.DATE_PICKER)
        );


    }


    public static Performable open() {
        return Instrumented.instanceOf(SelectDatePicker.class).newInstance();
    }
}
