package com.belano.tabplayer;

import static com.belano.tabplayer.Note.A;
import static com.belano.tabplayer.Note.B;
import static com.belano.tabplayer.Note.D;
import static com.belano.tabplayer.Note.E;
import static com.belano.tabplayer.Note.G;
import static com.belano.tabplayer.Note.e;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class TabParserBehaviour {

    @Test
    public void shouldConvertTabIntoNotesToBePlayed() throws Exception {
        // Given
        String asciiTab = "e|--------------7--5------------\n" + "B|--------4-4-4-----------------\n"
                + "G|-------3----------------------\n" + "D|-----2------------------------\n"
                + "A|---1--------------------------\n" + "E|-1----------------------------\n";
        TabParser parser = new DefaultTabParser();

        // When
        List<Note> notes = parser.parseAsciiTab(asciiTab);

        // Then
        assertThat("Expected notes were not played", notes,
                contains(E(1), A(1), D(2), G(3), B(4), B(4), B(4), e(7), e(5)));
    }
}
