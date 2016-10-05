package serenitylabs.tutorials.insurance.car;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.insurance.car.page.OpenVehicleDetails;
import serenitylabs.tutorials.insurance.car.tasks.FillVehicleDetails;
import serenitylabs.tutorials.insurance.car.tasks.StartANewQuote;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;

@RunWith(SerenityRunner.class)
public class WhenComparingACarInsurance {
    @Managed(driver = "chrome")
    static WebDriver webDriver ;

    Actor john;


    @Before
    public void setTheStage() {
        //System.setProperty("webdriver.chrome.driver", "D:\\Users\\sapurani\\Desktop\\Chrome Driver\\chromedriver.exe");
        john = Actor.named("John");
        john.can(BrowseTheWeb.with(webDriver));
    }

    @Test
    public void john_enters_vehicle_details(){
        givenThat(john).attemptsTo(OpenVehicleDetails.defaultPage(),
                StartANewQuote.forCarInsurance());
        givenThat(john).attemptsTo(FillVehicleDetails.withoutVehicleRegistrationNumber(false));

    }


}
