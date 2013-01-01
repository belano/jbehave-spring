package com.belano.tabplayer;

import java.util.ArrayList;
import java.util.List;

public class PlayedNotesConverter {

    public List<Note> convert(String notes) {
        List<Note> result = new ArrayList<Note>();
        String[] split = notes.split("\\s");
        for (String note : split) {
            result.add(getNote(note));
        }
        return result;
    }

    private Note getNote(String note) {
        GuitarString guitarString = GuitarString.valueOf(note.substring(0, 1));
        final int fret = Integer.parseInt(note.substring(1));
        return new Note(guitarString, fret);
    }

}
