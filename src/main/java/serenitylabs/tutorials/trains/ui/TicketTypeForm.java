package serenitylabs.tutorials.trains.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Created by sapurani on 9/21/2016.
 */
public class TicketTypeForm {
    public static final Target ORIGIN = Target.the("Origin Station").located(By.id("depart-from"));
    public static final Target DEPARTURE = Target.the("Departure Station").located(By.id("going-to"));
    public static final Target BUY_TICKETS = Target.the("Origin Station").located(By.className("planner__submit"));
}
