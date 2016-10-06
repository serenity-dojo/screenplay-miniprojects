package serenitylabs.tutorials.insurance.car.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;
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

                getFrom(vehicleInformation.getManufacturer().toString(),NewInsurancePage.SELECT_MANUFACTURER),
                getFrom(vehicleInformation.getModel(),NewInsurancePage.SELECT_MODEL),
                getFrom(vehicleInformation.getRegistrationYear(),NewInsurancePage.SELECT_REGISTRATION_YEAR),
                getFrom(vehicleInformation.getNumberOfDoorsOrStyle(),NewInsurancePage.SELECT_NO_OF_DOOR_OR_STYLE),
                getFrom(vehicleInformation.getTransmission(),NewInsurancePage.SELECT_TRANSMISSION),
                getFrom(vehicleInformation.getEngineCapacity(),NewInsurancePage.SELECT_ENGINE_CAPACITY),
                getFrom(vehicleInformation.getTrim(),NewInsurancePage.SELECT_TRIM)

        );

    }

    private Performable getFrom(String vehicleInformation,Target selectManufacturer) {
        return SelectFromOptions.byVisibleText(vehicleInformation).from(selectManufacturer);
    }

    public static Performable forVehicleHaving(VehicleInformation vehicleInformation) {
        return Instrumented.instanceOf(SelectVehicleDetails.class).withProperties(vehicleInformation);

    }
}
