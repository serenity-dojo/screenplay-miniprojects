package serenitylabs.tutorials.insurance.car.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import serenitylabs.tutorials.insurance.car.domain.VehicleInformation;
import serenitylabs.tutorials.insurance.car.page.NewInsurancePage;

/**
 * Created by sapurani on 10/6/2016.
 */
public class SelectVehicleDetails implements Task{
    VehicleInformation vehicleInformation;

    public SelectVehicleDetails(VehicleInformation vehicleInformation){
        this.vehicleInformation = vehicleInformation;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                SelectFromOptions.byVisibleText(vehicleInformation.getManufacturer().toString()).from(NewInsurancePage.SELECT_MANUFACTURER)
        );

    }

    public static Performable forVehicle(VehicleInformation vehicleInformation) {
        return Instrumented.instanceOf(SelectVehicleDetails.class).withProperties(vehicleInformation);

    }
}
