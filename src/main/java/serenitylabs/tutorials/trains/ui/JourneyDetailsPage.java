package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class JourneyDetailsPage {
    public static final Target CLOSE_COOKIES = Target.the("Close the Cookie message").locatedBy(".button--white");
    public static final Target DEPARTURE = Target.the("Origin field").locatedBy("#depart-from");
    public static final Target DESTINATION = Target.the("Destination field").locatedBy("#going-to");
    public static final Target BUT_TICKETS_BUTTON = Target.the("Buy tickets button").locatedBy(".planner__submit");
}
