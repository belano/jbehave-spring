package com.belano.tabplayer;

import static com.belano.tabplayer.Note.A;
import static com.belano.tabplayer.Note.D;
import static com.belano.tabplayer.Note.E;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class PlayedNotesConverterBehaviour {

    @Test
    public void shouldConvertNotes() throws Exception {
        // Given
        String notes = "A0 A2 A3 D0 D0 A2 A0 E3 A0 A0";

        // When
        List<Note> playedNotes = new PlayedNotesConverter().convert(notes);

        // Then
        assertThat("Expected notes were not found", playedNotes,
                contains(A(0), A(2), A(3), D(0), D(0), A(2), A(0), E(3), A(0), A(0)));
    }

}
