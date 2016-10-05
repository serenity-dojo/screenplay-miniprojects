package serenitylabs.tutorials.insurance.car.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import serenitylabs.tutorials.insurance.car.page.NewInsurancePage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sapurani on 10/5/2016.
 */
public class FillVehicleDetails implements Task{
     private final  boolean haveRegistrationNumber;


    public FillVehicleDetails(boolean haveRegistrationNumber) {
        this.haveRegistrationNumber = haveRegistrationNumber;
    }

    public final Map<Boolean,Target> RADIO_MAP= new HashMap<>();{
        RADIO_MAP.put(true,NewInsurancePage.VEHICLE_REG_NO);
        RADIO_MAP.put(false,NewInsurancePage.NO_VEHICLE_REG_NO);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Click.on(resolveRadioType(haveRegistrationNumber))
            );
        timer();

    }

    private void timer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Target resolveRadioType(boolean haveRegistrationNumber) {
        return RADIO_MAP.get(haveRegistrationNumber);

    }

    public static Performable withoutVehicleRegistrationNumber(boolean haveRegistrationNumber) {
         return Instrumented.instanceOf(FillVehicleDetails.class).withProperties(haveRegistrationNumber);
    }
}
