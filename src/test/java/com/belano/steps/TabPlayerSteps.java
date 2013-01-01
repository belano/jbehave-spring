package com.belano.steps;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.belano.tabplayer.DefaultTabParser;
import com.belano.tabplayer.Guitar;
import com.belano.tabplayer.Note;
import com.belano.tabplayer.PlayedNotesConverter;
import com.belano.tabplayer.Tab;
import com.belano.tabplayer.TabFileReader;
import com.belano.util.StepsDefinition;

@StepsDefinition
public class TabPlayerSteps {

    Tab tab;
    Guitar guitar;

    @Given("we have the input tab from file $ascii")
    public void tab(String asciiTabFile) {
        String asciiTab = new TabFileReader().readAsciiTabFromFile(asciiTabFile);
        tab = new Tab(asciiTab, new DefaultTabParser());
    }

    @When("the guitar plays")
    public void guitarPlays() {
        guitar = new Guitar();
        guitar.play(tab);
    }

    @Then("the following notes will be played $notes")
    public void theFollowingNotesWillBePlayed(String notes) {
        assertThat(expectedNotes(notes), arePlayedBy(guitar));
    }

    private NotesPlayedMatcher arePlayedBy(Guitar guitar) {
        return new NotesPlayedMatcher(guitar);
    }

    private List<Note> expectedNotes(String notes) {
        return new PlayedNotesConverter().convert(notes);
    }

    private class NotesPlayedMatcher extends TypeSafeMatcher<List<Note>> {

        List<Note> notesPlayed;

        public NotesPlayedMatcher(Guitar guitar) {
            this.notesPlayed = guitar.notesPlayed();
        }

        @Override
        protected boolean matchesSafely(List<Note> expectedNotes) {
            return notesPlayed.equals(expectedNotes);
        }

        public void describeTo(Description description) {
            description.appendText(notesPlayed.toString());
        }

    }

}
