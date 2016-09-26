package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheResultPage;
import serenitylabs.tutorials.trains.tasks.ChosenTo;
import serenitylabs.tutorials.trains.tasks.ViewTheAvailableTickets;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
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
    }

    @Test
    public void tracy_books_a_one_way_ticket_from_Reading_to_Oxford_leaving_now() {
        givenThat(tracy).has(ChosenTo.bookATicket());

        when(tracy).attemptsTo(ViewTheAvailableTickets.from("Reading").to("Oxford"));

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
                        TheResultPage.typeOfTicket(), is("Off-Peak Day Single")
                ),
                seeThat(
                        TheResultPage.durationOfJourney(), is("25 minutes")
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
    public void tracy_books_a_return_ticket_from_Reading_to_Oxford_leaving_now_and_returning_tomorrow(){

    }
}
