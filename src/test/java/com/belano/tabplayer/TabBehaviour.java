package com.belano.tabplayer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

}
