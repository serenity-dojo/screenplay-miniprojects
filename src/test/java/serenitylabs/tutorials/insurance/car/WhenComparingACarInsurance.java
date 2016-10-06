package serenitylabs.tutorials.insurance.car;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitylabs.tutorials.insurance.car.domain.Manufacturer;
import serenitylabs.tutorials.insurance.car.domain.VehicleInformation;
import serenitylabs.tutorials.insurance.car.page.NewInsurancePage;
import serenitylabs.tutorials.insurance.car.page.OpenVehicleDetails;
import serenitylabs.tutorials.insurance.car.questions.TheVehicleSummary;
import serenitylabs.tutorials.insurance.car.tasks.SelectVehicleDetails;
import serenitylabs.tutorials.insurance.car.tasks.StartANewQuote;
import serenitylabs.tutorials.insurance.car.tasks.SupplyRegistrationNumberIfAvailable;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.is;

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
        givenThat(john).attemptsTo(
                OpenVehicleDetails.defaultPage(),
                StartANewQuote.forCarInsurance());

        and(john).attemptsTo(
                SupplyRegistrationNumberIfAvailable.withoutVehicleRegistrationNumber(),
                SelectVehicleDetails.forVehicleHaving(theVehicleInformation()));

        and(john).attemptsTo(Click.on(NewInsurancePage.VEHICLE_DETAILS_SUBMIT));

        then(john).should(seeThat(TheVehicleSummary.details(),is("1999 AC Ace Auto, 4942CC Petrol, 2DR, Auto")));
    }

    private VehicleInformation theVehicleInformation() {
        return new VehicleInformation().withManufacturer(Manufacturer.AC).
                    andModel("Ace").andRegistrationYear("1999T").
                    andNumberOfDoorsOrStyle("2DR Cabriolet").
                    andTransmission("Auto").
                    andEngineCapacity("4942CC").
                    andTrim("Auto");
    }

    private void timer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
