package com.belano.tabplayer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TabBehaviour {

    @Test
    public void shouldParseTabUponConstruction() throws Exception {
        // Given
        TabParser parser = mock(TabParser.class);
        String asciiTab = "e|--------A3-B6-";

        // When
        new Tab(asciiTab, parser);

        // Then
        verify(parser).parseAsciiTab(asciiTab);
    }

    @Test
    public void shouldProvideNotesToBePlayedInOrder() throws Exception {
        // Given
        TabParser parser = mock(TabParser.class);
        String asciiTab = "e|--------A3-B6-";

        List<Note> notes = Arrays.asList(Note.A(3), Note.B(6));
        stub(parser.parseAsciiTab(asciiTab)).toReturn(notes);

        // When
        Tab tab = new Tab(asciiTab, parser);
        List<Note> actualNotes = tab.notesToBePlayed();

        // Then
        assertThat(notes, is(actualNotes));
    }

}
