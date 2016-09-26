package serenitylabs.tutorials.trains;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.trains.questions.TheOutBoundJourneySummary;
import serenitylabs.tutorials.trains.tasks.ChosenTo;
import serenitylabs.tutorials.trains.tasks.ViewAvailableTicketsForReturnTrip;
import serenitylabs.tutorials.trains.tasks.ViewAvailableTicketsForSingleTrip;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class WhenPlanningATrip {
    @Managed(driver = "chrome")
    static WebDriver webDriver ;

    Actor tracy;

    @Before
    public void setTheStage() {
        System.setProperty("webdriver.chrome.driver", "D:\\Users\\sapurani\\Desktop\\Chrome Driver\\chromedriver.exe");
        tracy = Actor.named("Tracy");
        tracy.can(BrowseTheWeb.with(webDriver));
    }

    @Test
    public void booking_a_simple_trip() {
        tracy.has(ChosenTo.bookATicket());

        //WHEN
        tracy.attemptsTo(ViewAvailableTicketsForSingleTrip.from("Reading").to("Oxford"));


        //THEN
        tracy.should(
                seeThat(TheOutBoundJourneySummary.origin(),is("READING")),
                seeThat(TheOutBoundJourneySummary.destination(),is("Oxford")),
                seeThat(TheOutBoundJourneySummary.duration(),notNullValue()),
                seeThat(TheOutBoundJourneySummary.ticketType(),notNullValue()));
    }

    @Test
    public void booking_a_simple_round_trip() {
        tracy.has(ChosenTo.bookATicket());

        //WHEN
        tracy.attemptsTo(ViewAvailableTicketsForReturnTrip.from("Reading").to("Oxford").returnDate("27th Sep 18:15"));
        //aND

        //tracy.should(seeThat(TheOutBoundJourneySummary.optionTitle(),is("Choose travel option")));
         //THEN
       /* tracy.should(
                seeThat(TheOutBoundJourneySummary.origin(),is("READING")),
                seeThat(TheOutBoundJourneySummary.destination(),is("Oxford")),
                seeThat(TheOutBoundJourneySummary.duration(),notNullValue()),
                seeThat(TheOutBoundJourneySummary.ticketType(),notNullValue()));*/
    }
}
