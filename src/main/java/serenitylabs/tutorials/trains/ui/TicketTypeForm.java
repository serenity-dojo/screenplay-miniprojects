package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by sapurani on 9/21/2016.
 */
public class TicketTypeForm {
    public static final Target ORIGIN = Target.the("Origin Station").located(By.id("depart-from"));
    public static final Target DEPARTURE = Target.the("Departure Station").located(By.id("going-to"));
    public static final Target BUY_TICKETS = Target.the("Buy Tickets").located(By.className("planner__submit"));
    public static final Target ACCEPT_COOKIES = Target.the("Accept Cookies").locatedBy(" .cookies button");
    //public static final Target RETURN = Target.the("Return trip").locatedBy("//*[@id=\"journey-planner\"]/div[2]/div[3]/div[1]/div[2]/div/div/div[2]/label");
        public static final Target RETURN = Target.the("Return trip").located(By.cssSelector("label[for='return']"));
        public static final Target DATE_PICKER = Target.the("Date picker").located(By.cssSelector(".date-time[data-journey-type='Return'] button span.rest-of-date"));
}
