package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import serenitylabs.tutorials.trains.ui.JourneySummaryPage;

import java.util.List;

/**
 * Created by Vikrant Dheer on 9/26/2016.
 */
public class TheResultPage {
    public static Question<String> departureHeading() {
        return actor -> Text.of(JourneySummaryPage.DEPARTURE).viewedBy(actor).asString();
    }

    public static Question<String> destinationHeading() {
        return actor -> Text.of(JourneySummaryPage.DESTINATION).viewedBy(actor).asString();
    }

    public static Question<List<String>> numberOfJourneyOptions() {
        return actor -> Text.of(JourneySummaryPage.JOURNEY_OPTIONS).viewedBy(actor).asList();
    }

    public static Question<String> typeOfTicket() {
        return actor -> Text.of(JourneySummaryPage.TICKET_TYPE).viewedBy(actor).asString();
    }

    public static Question<String> durationOfJourney() {
        return actor -> Text.of(JourneySummaryPage.DURATION).viewedBy(actor).asString();
}

    public static Question<String> journeyDeparture() {
        return actor -> Text.of(JourneySummaryPage.JOURNEY_ORIGIN).viewedBy(actor).asString();
    }

    public static Question<String> journeyDestination() {
        return actor -> Text.of(JourneySummaryPage.JOURNEY_DESTINATION).viewedBy(actor).asString();
    }
}

