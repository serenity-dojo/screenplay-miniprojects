package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage;

import java.util.List;

/**
 * Created by Vikrant Dheer on 9/27/2016.
 */
@Subject("The details of inbound journey page")
public class TheInboundJourneyPage{

    public static Question<String> departureHeading() {
        return actor -> Text.of(InboundJourneySummaryPage.EXPECTED_DEPARTURE).viewedBy(actor).asString();
    }

    public static Question<String> destinationHeading() {
        return actor -> Text.of(InboundJourneySummaryPage.EXPECTED_DESTINATION).viewedBy(actor).asString();
    }

    public static Question<List<String>> numberOfJourneyOptions() {
        return actor -> Text.of(InboundJourneySummaryPage.JOURNEY_OPTIONS).viewedBy(actor).asList();
    }

    public static Question<List<String>> typeOfTicket() {
        return actor -> Text.of(InboundJourneySummaryPage.TICKET_TYPE).viewedBy(actor).asList();
    }

    public static Question<String> durationOfJourney() {
        return actor -> Text.of(InboundJourneySummaryPage.DURATION).viewedBy(actor).asString();
    }

    public static Question<String> journeyDeparture() {
        return actor -> Text.of(InboundJourneySummaryPage.JOURNEY_ORIGIN).viewedBy(actor).asString();
    }

    public static Question<String> journeyDestination() {
        return actor -> Text.of(InboundJourneySummaryPage.JOURNEY_DESTINATION).viewedBy(actor).asString();
    }

    public static Question<String> expectedDeparture() {
        return actor -> Text.of(InboundJourneySummaryPage.DEPARTING_STATION).viewedBy(actor).asString();
    }

    public static Question<String> expectedArrival() {
        return actor -> Text.of(InboundJourneySummaryPage.ARRIVAL_STATION).viewedBy(actor).asString();
    }
}
