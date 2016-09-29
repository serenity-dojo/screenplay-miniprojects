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


    @Before
    public void setup() {
        tracy = Actor.named("Tracy the student");
        bill = Actor.named("Bill the business man");

        //browse the web.. functionality given to both the users
        tracy.can(BrowseTheWeb.with(browserForTracy));
        bill.can(BrowseTheWeb.with(browserForBill));

        browserForTracy.manage().window().maximize();
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
                seeThat(TheResultPage.typeOfTicket(), hasItem("Off-Peak Day Single")),
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
        and(tracy).attemptsTo(EnterReturnSchedule.departOrArrive("arrive").On(29, 9, 2016).at("09", "30"));
        then(tracy).attemptsTo(BuyTheTicket.forOneSide());

        then(tracy).should(eventually(seeThat(the(InboundJourneySummaryPage.DEPARTURE), isVisible())));

        then(tracy).should(
                eventually(
                        seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalTo("Oxford"))
                ),
                seeThat("the destination station", TheInboundJourneyPage.destinationHeading(), is("READING")),
                seeThat("the journey options", TheInboundJourneyPage.numberOfJourneyOptions(), hasSize(greaterThan(0))),
                seeThat("the type of ticket", the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()),
                seeThat("the type of ticket", TheInboundJourneyPage.typeOfTicket(), hasItem("Anytime Day Single")),
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
        and(bill).attemptsTo(EnterReturnSchedule.departOrArrive("arrive").On(29, 9, 2016).at("09", "30"));
        then(bill).attemptsTo(BuyTheTicket.forOneSide());

        then(bill).should(
                eventually(
                        seeThat("the departure station", TheInboundJourneyPage.departureHeading(), equalTo("Oxford"))
                ),
                seeThat("the destination station", TheInboundJourneyPage.destinationHeading(), is("READING")),
                seeThat("the journey options", TheInboundJourneyPage.numberOfJourneyOptions(), hasSize(greaterThan(0))),
                seeThat("the type of ticket", the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()),
                seeThat("the type of ticket", TheInboundJourneyPage.typeOfTicket(), hasItem("Anytime Day Single")),
                seeThat("the duration of journey", the(InboundJourneySummaryPage.DURATION), isCurrentlyVisible()),
                seeThat("the departure point of journey", TheInboundJourneyPage.journeyDeparture(), is("Oxford")),
                seeThat("the destination point of journey", TheInboundJourneyPage.journeyDestination(), is("Reading"))
        );

    }
}
