package serenitylabs.tutorials.insurance.car.page;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by sapurani on 10/5/2016.
 */
public class NewInsurancePage {
    public static final Target NEW_CAR_INSURANCE =Target.the("Car Insurance icon").located(By.id("lph-cta-car")) ;
    public static final Target NO_VEHICLE_REG_NO = Target.the("No Vehicle Registration").locatedBy("#knows-registration-number-no ~ .Checkbox-label") ;
    public static final Target VEHICLE_REG_NO = Target.the("No Vehicle Registration").locatedBy("#knows-registration-number-yes ~ .Checkbox-label") ;
    public static final Target SELECT_MANUFACTURER =  Target.the("Select Manufacturer").locatedBy("#manufacturers");
    public static final Target SELECT_MODEL = Target.the("Select Manufacturer").locatedBy("#models");
    public static final Target SELECT_REGISTRATION_YEAR =Target.the("Select Registration Year").locatedBy("#registration-year-and-letters");
    public static final Target SELECT_NO_OF_DOOR_OR_STYLE = Target.the("Select Number Of Door or Style").locatedBy("#styles");
    public static final Target SELECT_TRANSMISSION = Target.the("Select Transmission").locatedBy("#transmissions");
    public static final Target SELECT_ENGINE_CAPACITY = Target.the("Select Engine Capacity").locatedBy("#engines");
    public static final Target SELECT_TRIM  = Target.the("Select Engine Capacity").locatedBy("#trims");
    public static final Target VEHICLE_DETAILS_SUBMIT  = Target.the("Submit Vehicle Details").locatedBy("#this-is-my-vehicle");

    ;
}
