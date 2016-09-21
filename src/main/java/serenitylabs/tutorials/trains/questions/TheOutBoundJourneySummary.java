package serenitylabs.tutorials.trains.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import serenitylabs.tutorials.trains.ui.TheOutBoundSummary;

/**
 * Created by sapurani on 9/21/2016.
 */
public class TheOutBoundJourneySummary {
    public static Question<String> origin() {
        return actor -> Text.of(TheOutBoundSummary.ORIGIN).viewedBy(actor).asString();
    }

    public static Question<String> destination() {
        return actor -> Text.of(TheOutBoundSummary.DESTINATION).viewedBy(actor).asString();
    }
}
