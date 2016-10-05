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
}
