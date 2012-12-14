package com.belano.tabplayer;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import org.junit.Test;

public class GuitarBehaviour {

    @Test
    public void shouldPlayTheNotesTranscribedInTheGivenTab() throws Exception {
        // Given
        Guitar axe = new Guitar();
        Tab tab = mock(Tab.class);
        Note A1 = Note.A(1);
        Note B2 = Note.B(2);
        stub(tab.notesToBePlayed()).toReturn(asList(A1, B2));
        // When
        axe.play(tab);
        // Then
        assertThat(axe.notesPlayed(), containsInAnyOrder(A1, B2));
    }

}
