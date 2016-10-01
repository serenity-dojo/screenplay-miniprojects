package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by Vikrant Dheer on 9/27/2016.
 */
public class InboundJourneySummaryPage {

    public static final Target EXPECTED_DEPARTURE = Target.the("Departure field").locatedBy(".planner-header__origin");
    public static final Target EXPECTED_DESTINATION = Target.the("Destination field").locatedBy(".planner-header__destination");
    public static final Target JOURNEY_OPTIONS = Target.the("Number of Journey options").locatedBy(".journey__price");
    public static final Target DURATION = Target.the("Duration of journey").locatedBy(".journey__duration-text");
    public static final Target TICKET_TYPE = Target.the("Type of ticket").locatedBy(".journey__ticket-type-text");
    public static final Target JOURNEY_ORIGIN = Target.the("Origin of journey").locatedBy(".journey__origin");
    public static final Target JOURNEY_DESTINATION = Target.the("Destination of journey").locatedBy(".journey__destination");
    public static final Target INBOUND_JOURNEY_PRICE = Target.the("price and buys the return ticket as well").locatedBy(".journey__price[href='inbound/select/1']");
    public static final Target UPGRADE = Target.the("Upgrade to First class").locatedBy(".options-input-header__title");
    public static final Target UPGRADE_OPTION = Target.the("Upgrade to First class").locatedBy("#option-0");
    public static final Target DIRECT_OPTION = Target.the("direct option").locatedBy("article:nth-child(2) .journey__changes");
    public static final Target DEPARTING_TIME = Target.the("time of departure").locatedBy("article:nth-child(2)  .itinerary__depart .itinerary__time");
    public static final Target ARRIVAL_TIME = Target.the("time of arrival").locatedBy("article:nth-child(2)  .itinerary__arrive .itinerary__time");
    public static final Target DEPARTING_STATION = Target.the("departure station").locatedBy("article:nth-child(2)  .itinerary__depart .itinerary__station");;
    public static final Target ARRIVAL_STATION = Target.the("arrival station").locatedBy("article:nth-child(2)  .itinerary__arrive .itinerary__station");;
    public static final Target CHANGE_OPTION = Target.the("change option").locatedBy("article:nth-child(2) .journey__changes");
}
