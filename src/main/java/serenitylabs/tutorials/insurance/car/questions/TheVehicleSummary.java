package serenitylabs.tutorials.insurance.car.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import serenitylabs.tutorials.insurance.car.target.VehicleDetailsSummary;

/**
 * Created by sapurani on 10/6/2016.
 */
public class TheVehicleSummary {
    public static Question<String> details() {
        return actor -> Text.of(VehicleDetailsSummary.VEHICLE_SUMMARY).viewedBy(actor).asString();
    }
}
