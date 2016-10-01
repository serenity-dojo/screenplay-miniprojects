package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheInboundJourneyPage;
import serenitylabs.tutorials.trains.questions.TheResultPage;
import serenitylabs.tutorials.trains.tasks.*;
import serenitylabs.tutorials.trains.ui.InboundJourneySummaryPage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {

    //declaring two travellers
    Actor tracy, bill;

    //managed Webdriver for tracy the student
    @Managed
    WebDriver driverForTracy;

    //managed Webdriver for bill the business man
    @Managed
    WebDriver driverForBill;

    //List for containing types of tickets available as per schedule and timing of booking
    List<String> typeOfTickets;

    //date for booking manipulations
    LocalDate currentDate;

    //time for booking manipulations
    LocalTime defaultTime;


    /*
    setting up the application with few assumptions
     */
    @Before
    public void setup() {
        tracy = Actor.named("Tracy the student");
        bill = Actor.named("Bill the business man");

        //browse the web.. functionality given to both the users
        tracy.can(BrowseTheWeb.with(driverForTracy));
        bill.can(BrowseTheWeb.with(driverForBill));

        driverForTracy.manage().window().maximize();
        driverForBill.manage().window().maximize();

        currentDate = LocalDate.now();
        defaultTime = LocalTime.of(00, 00);

        typeOfTickets = new ArrayList<>();
        typeOfTickets.add("Super Off-Peak Return");
        typeOfTickets.add("Off-Peak Return");
        typeOfTickets.add("Anytime Single");
        typeOfTickets.add("Off-Peak Day Single");
    }

    //1st scenario
    @Test
    public void tracy_books_a_one_way_ticket_from_Reading_to_Oxford_leaving_now() {
        givenThat(tracy).has(ChosenTo.bookATicket());

        when(tracy).attemptsTo(ViewTicketsForOneSideTrip.from("Reading").to("Oxford"));

        then(tracy).should(
                seeThat(TheResultPage.departureHeading(), is("READING")),
                seeThat(TheResultPage.destinationHeading(), is("Oxford")),
                seeThat(TheResultPage.numberOfJourneyOptions(), hasSize(greaterThan(0))),
                seeThat(the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()),
                seeThat(TheResultPage.typeOfTicket(), everyItem(isIn(typeOfTickets))),
                seeThat(TheResultPage.durationOfJourney(), notNullValue()),
                seeThat(TheResultPage.journeyDeparture(), is("Reading")),
                seeThat(TheResultPage.journeyDestination(), is("Oxford"))
        );
    }

    //2nd scenario
    @Test
    public void tracy_books_a_return_ticket_from_Reading_to_Oxford_leaving_now_and_returning_tomorrow() {

        givenThat(tracy).has(ChosenTo.bookATicket());

        when(tracy).attemptsTo(ViewTicketsForReturnTrip.from("Reading").to("Oxford"));
        and(tracy).attemptsTo(EnterReturnSchedule.departOrArrive("arrive").
                On(currentDate).at(defaultTime.plusHours(07).plusMinutes(30)));
        then(tracy).attemptsTo(BuyTheTicket.forOneSide());

        then(tracy).should(eventually(seeThat(the(InboundJourneySummaryPage.EXPECTED_DEPARTURE), isVisible())));

        then(tracy).should(
                eventually(
                        seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalTo("Oxford"))
                ),
                seeThat("the destination station", TheInboundJourneyPage.destinationHeading(), is("READING")),
                seeThat("the journey options", TheInboundJourneyPage.numberOfJourneyOptions(), hasSize(greaterThan(0))),
                seeThat("visibility of type of ticket", the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()),
                seeThat("the type of ticket is valid", TheInboundJourneyPage.typeOfTicket(), everyItem(isIn(typeOfTickets))),
                seeThat("the duration of journey", the(InboundJourneySummaryPage.DURATION), isCurrentlyVisible()),
                seeThat("the departure point of journey", TheInboundJourneyPage.journeyDeparture(), is("Oxford")),
                seeThat("the destination point of journey", TheInboundJourneyPage.journeyDestination(), is("Reading"))
        );
    }

    //3rd Scenario
    @Test
    public void bill_books_a_return_ticket_from_Reading_to_Oxford_leaving_tomorrow() {

        givenThat(bill).has(ChosenTo.bookATicket());

        when(bill).attemptsTo(ViewTicketsForReturnTrip.from("London Kings Cross").to("York"));
        and(bill).attemptsTo(EnterReturnSchedule.departOrArrive("arrive")
                .On(currentDate).at(defaultTime.plusHours(12).plusMinutes(45)));
        then(bill).attemptsTo(BuyTheTicket.forOneSide());
        then(bill).attemptsTo(BuyTheTicket.forReturn());

        then(bill).should(eventually(seeThat("the upgrade option to First Class", the(InboundJourneySummaryPage.UPGRADE_OPTION), isVisible())));
    }

    //4th Scenario
    @Test
    public void bill_views_the_details_of_a_trip_for_a_direct_trip() {

        givenThat(bill).has(ChosenTo.bookATicket());

        when(bill).attemptsTo(ViewTicketsForReturnTrip.from("Reading").to("Bath Spa"));
        and(bill).attemptsTo(EnterReturnSchedule.departOrArrive("arrive")
                .On(currentDate.plusDays(1)).at(defaultTime.plusHours(14).plusMinutes(15)));
        then(bill).attemptsTo(BuyTheTicket.withDirectOption());

        then(bill).should(
                eventually(
                        seeThat("the departing time in direct option", the(InboundJourneySummaryPage.DEPARTING_TIME), isCurrentlyVisible())
                ),
                (seeThat("the arrival time in direct option", the(InboundJourneySummaryPage.ARRIVAL_TIME), isCurrentlyVisible())),
                (seeThat("the departing station in direct option", the(InboundJourneySummaryPage.DEPARTING_STATION), isCurrentlyVisible())),
                (seeThat("the arrival station in direct option", the(InboundJourneySummaryPage.ARRIVAL_STATION), isCurrentlyVisible())),

                (seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalToIgnoringCase("Reading"))),
                (seeThat("the arrival station", TheInboundJourneyPage.destinationHeading(), equalToIgnoringCase("Bath Spa")))
//                seeThat("the inbound itinerary", TheInboundJourneyPage.itinerary(), is(ATripItinerary.goingFrom("Oxford").to("Reading"))
        );
    }

    //5th Scenario
    @Test
    public void bill_views_the_details_of_a_trip_for_a_trip_with_changes() {

        givenThat(bill).has(ChosenTo.bookATicket());

        when(bill).attemptsTo(ViewTicketsForReturnTrip.from("Cardiff Central").to("York"));
        and(bill).attemptsTo(EnterReturnSchedule.departOrArrive("arrive")
                .On(currentDate.plusWeeks(1)).at(defaultTime.plusHours(04).plusMinutes(15)));
        then(bill).attemptsTo(BuyTheTicket.withOneChangeOption());

        then(bill).should(
                eventually(
                        seeThat("the departing time in direct option", the(InboundJourneySummaryPage.DEPARTING_TIME_FOR_CHANGE_LEG_1), isCurrentlyVisible())
                ),
                (seeThat("the arrival time in direct option", the(InboundJourneySummaryPage.ARRIVAL_TIME_FOR_CHANGE_LEG_1), isCurrentlyVisible())),
                (seeThat("the arrival time in direct option", the(InboundJourneySummaryPage.DEPARTING_TIME_FOR_CHANGE_LEG_2), isCurrentlyVisible())),
                (seeThat("the arrival time in direct option", the(InboundJourneySummaryPage.ARRIVAL_TIME_FOR_CHANGE_LEG_2), isCurrentlyVisible())),

                (seeThat("the departing station in direct option", the(InboundJourneySummaryPage.DEPARTING_STATION_FOR_CHANGE_LEG_1), isCurrentlyVisible())),
                (seeThat("the arrival station in direct option", the(InboundJourneySummaryPage.ARRIVAL_STATION_FOR_CHANGE_LEG_1), isCurrentlyVisible())),
                (seeThat("the departing station in direct option", the(InboundJourneySummaryPage.DEPARTING_STATION_FOR_CHANGE_LEG_2), isCurrentlyVisible())),
                (seeThat("the arrival station in direct option", the(InboundJourneySummaryPage.ARRIVAL_STATION_FOR_CHANGE_LEG_2), isCurrentlyVisible())),

                (seeThat("the arrival station of leg1", TheInboundJourneyPage.arrivalStationAtLeg1(), equalTo("Gloucester"))),
                (seeThat("the departure station of leg 2", TheInboundJourneyPage.departureStationAtLeg2(), equalTo("Gloucester"))),

                (seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalToIgnoringCase("Cardiff Central"))),
                (seeThat("the arrival station", TheInboundJourneyPage.destinationHeading(), equalToIgnoringCase("York")))
//                seeThat("the inbound itinerary", TheInboundJourneyPage.itinerary(), is(ATripItinerary.goingFrom("Oxford").to("Reading"))
        );
    }
}
