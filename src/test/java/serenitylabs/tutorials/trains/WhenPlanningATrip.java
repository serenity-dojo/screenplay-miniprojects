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

    Actor tracy, bill;

    @Managed
    WebDriver browserForTracy;

    @Managed
    WebDriver browserForBill;

    List<String> typeOfTickets;

    LocalDate currentDate;
    LocalTime defaultTime;


    @Before
    public void setup() {
        tracy = Actor.named("Tracy the student");
        bill = Actor.named("Bill the business man");

        //browse the web.. functionality given to both the users
        tracy.can(BrowseTheWeb.with(browserForTracy));
        bill.can(BrowseTheWeb.with(browserForBill));

        browserForTracy.manage().window().maximize();
        browserForBill.manage().window().maximize();

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

        then(tracy).should(eventually(seeThat(the(InboundJourneySummaryPage.DEPARTURE), isVisible())));

        then(tracy).should(
                eventually(
                        seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalTo("Oxford"))
                ),
                seeThat("the destination station", TheInboundJourneyPage.destinationHeading(), is("READING")),
                seeThat("the journey options", TheInboundJourneyPage.numberOfJourneyOptions(), hasSize(greaterThan(0))),
                seeThat("the type of ticket", the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()),
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

        when(bill).attemptsTo(ViewTicketsForReturnTrip.from("Reading ").to("Bath Spa"));
        and(bill).attemptsTo(EnterReturnSchedule.departOrArrive("arrive")
                .On(currentDate).at(defaultTime.plusHours(12).plusMinutes(45)));
        then(bill).attemptsTo(BuyTheTicket.withDirectOption());

        then(bill).should(
                eventually(
                        seeThat("the direct option", the(InboundJourneySummaryPage.DEPARTING_TIME), isCurrentlyVisible())
                ),
                (seeThat("the direct option", the(InboundJourneySummaryPage.ARRIVAL_TIME), isCurrentlyVisible())),
                (seeThat("the direct option", the(InboundJourneySummaryPage.DEPARTING_STATION), isCurrentlyVisible())),
                (seeThat("the direct option", the(InboundJourneySummaryPage.ARRIVAL_STATION), isCurrentlyVisible()))
        );
    }
}
