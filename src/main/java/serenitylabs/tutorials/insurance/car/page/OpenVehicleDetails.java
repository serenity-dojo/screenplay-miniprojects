package serenitylabs.tutorials.insurance.car.page;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Created by sapurani on 9/29/2016.
 */
@DefaultUrl("https://www.comparethemarket.com/car-insurance/")
public class OpenVehicleDetails implements Task{
   // DefaultCarInsurancePage defaultCarInsurancePage;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn( ).the(new DefaultCarInsurancePage())
        );

    }

    public static Performable defaultPage() {
        return Instrumented.instanceOf(OpenVehicleDetails.class).newInstance();
    }
}
