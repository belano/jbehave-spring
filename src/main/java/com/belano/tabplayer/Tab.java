package com.belano.tabplayer;

import java.util.List;

public class Tab {

    TabParser parser;
    List<Note> notesToBePlayed;

    public Tab(String asciiTab, TabParser parser) {
        notesToBePlayed = parser.parseAsciiTab(asciiTab);
    }

    public List<Note> notesToBePlayed() {
        return notesToBePlayed;
    }

}
