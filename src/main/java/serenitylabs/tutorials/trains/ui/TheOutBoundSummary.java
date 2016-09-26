package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by sapurani on 9/21/2016.
 */
public class TheOutBoundSummary {
    public static final Target TITLE = Target.the("Title").locatedBy(".options__title-text");
    public static Target ORIGIN = Target.the("Origin").locatedBy(".planner-header__origin");
    public static Target DESTINATION = Target.the("Departure").locatedBy(".planner-header__destination");
    public static Target DURATION = Target.the("Departure").locatedBy(".journey__duration-text");
    public static Target TICKET_TYPE = Target.the("Departure").locatedBy(".journey__ticket-type-text");
}
