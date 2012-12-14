package com.belano.tabplayer;

import java.util.List;

public class Guitar {

    public List<Note> notesPlayed;

    public void play(Tab tab) {
        notesPlayed = tab.notesToBePlayed();
    }

    public List<Note> notesPlayed() {
        return notesPlayed;
    }

}
