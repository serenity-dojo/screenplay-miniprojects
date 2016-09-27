package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class JourneyDetailsPage {
    public static final Target CLOSE_COOKIES = Target.the("close the Cookie message").locatedBy(".button--white");
    public static final Target DEPARTURE = Target.the("origin field").locatedBy("#depart-from");
    public static final Target DESTINATION = Target.the("destination field").locatedBy("#going-to");
    public static final Target RETURN_TRIP_BUTTON = Target.the("return trip button").located(By.cssSelector("label[for='return']"));
    public static final Target RETURN_SCHEDULE = Target.the("return trip scheduler").locatedBy("//button[contains(.,'Select time/date')]");
    public static final Target DEPART_OR_ARRIVE = Target.the("selecting options for ticket type as Depart or Arrive").locatedBy(".radio-group__label[for='Inbound-%s']");
    public static final Target DATE_OF_RETURN = Target.the("date for").locatedBy(".picker[style='display: block;'] button[data-pika-day='%d']");
    public static final Target HOUR = Target.the("dropdown for selecting the hours").locatedBy(".picker[style='display: block;'] select[data-ref='hours']");
    public static final Target MINUTE = Target.the("dropdown for selecting the minutes").locatedBy(".picker[style='display: block;'] select[data-ref='minutes']");
    public static final Target DONE = Target.the("done button").locatedBy(".picker[style='display: block;'] .button[data-ref='closeButton']");
    public static final Target BUT_TICKETS_BUTTON = Target.the("buy tickets button to view the journey options").locatedBy(".planner__submit");
}
