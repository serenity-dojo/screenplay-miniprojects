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
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {

    Actor tracy, bill;

    @Managed
    WebDriver browser;

    @Before
    public void setup() {
        tracy = Actor.named("Tracy the student");
        bill = Actor.named("Bill the business man");

        //browse the web.. functionality given to both the users
        tracy.can(BrowseTheWeb.with(browser));
        bill.can(BrowseTheWeb.with(browser));

        browser.manage().window().maximize();
    }

    @Test
    public void tracy_books_a_one_way_ticket_from_Reading_to_Oxford_leaving_now() {
        givenThat(tracy).has(ChosenTo.bookATicket());

        when(tracy).attemptsTo(ViewTicketsForOneSideTrip.from("Reading").to("Oxford"));

        then(tracy).should(
                seeThat(
                        TheResultPage.departureHeading(), is("READING")
                ),
                seeThat(
                        TheResultPage.destinationHeading(), is("Oxford")
                ),
                seeThat(
                        TheResultPage.numberOfJourneyOptions(), hasSize(greaterThan(0))
                ),
                seeThat(
                        TheResultPage.typeOfTicket(), hasItem("Off-Peak Day Single")
                ),
                seeThat(
                        TheResultPage.durationOfJourney(), notNullValue()
                ),
                seeThat(
                        TheResultPage.journeyDeparture(), is("Reading")
                ),
                seeThat(
                        TheResultPage.journeyDestination(), is("Oxford")
                )
        );
    }

    @Test
    public void tracy_books_a_return_ticket_from_Reading_to_Oxford_leaving_now_and_returning_tomorrow() {

        givenThat(tracy).has(ChosenTo.bookATicket());

        when(tracy).attemptsTo(ViewTicketsForReturnTrip.from("Reading").to("Oxford"));
        and(tracy).attemptsTo(EnterReturnSchedule.departOrArrive("arrive").On(28, 9, 2016).at("07", "15"));
        then(tracy).attemptsTo(BuyTheTicket.forOneSide());

        then(tracy).should(
                eventually(
                        seeThat("the departure station",
                                TheInboundJourneyPage.departureHeading(), equalTo("Oxford")
                        )
                ),
                seeThat("the destination station",
                        TheInboundJourneyPage.destinationHeading(), is("READING")
                ),
                seeThat("the journey options",
                        TheInboundJourneyPage.numberOfJourneyOptions(), hasSize(greaterThan(0))
                ),
                seeThat("the type of ticket",
                        the(InboundJourneySummaryPage.TICKET_TYPE), isCurrentlyVisible()
                ),
                seeThat("the type of ticket",
                        TheInboundJourneyPage.typeOfTicket(), hasItem("Anytime Day Single")
                ),
                seeThat("the duration of journey",
                        the(InboundJourneySummaryPage.DURATION), isCurrentlyVisible()
                ),
                seeThat("the departure point of journey",
                        TheInboundJourneyPage.journeyDeparture(), is("Oxford")
                ),
                seeThat("the destination point of journey",
                        TheInboundJourneyPage.journeyDestination(), is("Reading")
                )
        );
    }
}
