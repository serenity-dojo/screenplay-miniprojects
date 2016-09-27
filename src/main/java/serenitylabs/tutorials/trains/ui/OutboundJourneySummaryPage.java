package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class OutboundJourneySummaryPage {
    public static final Target DEPARTURE = Target.the("Departure field").locatedBy(".planner-header__origin");
    public static final Target DESTINATION = Target.the("Destination field").locatedBy(".planner-header__destination");
    public static final Target JOURNEY_OPTIONS = Target.the("Number of Journey options").locatedBy(".journey__price");
    public static final Target DURATION = Target.the("Duration of journey").locatedBy(".journey__duration-text");
    public static final Target TICKET_TYPE = Target.the("Type of ticket").locatedBy(".journey__ticket-type-text");
    public static final Target JOURNEY_ORIGIN = Target.the("Origin of journey").locatedBy(".journey__origin");
    public static final Target JOURNEY_DESTINATION = Target.the("Destination of journey").locatedBy(".journey__destination");
    public static final Target JOURNEY_PRICE = Target.the("price and buys the ticket").locatedBy(".journey__summary a[href='outbound/select/1']");
}
