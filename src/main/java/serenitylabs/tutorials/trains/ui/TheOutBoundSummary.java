package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by sapurani on 9/21/2016.
 */
public class TheOutBoundSummary {
    public static Target ORIGIN = Target.the("Origin").locatedBy(".planner-header__origin");
    public static Target DESTINATION = Target.the("Departure").locatedBy(".planner-header__destination");
}
