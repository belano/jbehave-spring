package com.belano.tabplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTabParser implements TabParser {

    private static int DEFAUL_LENGTH = 35;

    public List<Note> parseAsciiTab(String asciiTab) {
        String[] stringPlusFrets = asciiTab.split("\n");
        Map<GuitarString, char[]> map = extractMap(stringPlusFrets);
        List<Note> notes = getNotesFromMap(map);
        return notes;
    }

    private List<Note> getNotesFromMap(Map<GuitarString, char[]> acc) {
        List<Note> notes = new ArrayList<Note>();
        final int columns = DEFAUL_LENGTH;
        for (int j = 0; j < columns; ++j) {
            for (Map.Entry<GuitarString, char[]> entry : acc.entrySet()) {
                char[] value = entry.getValue();
                if (j < value.length) {
                    int fret = extractFret(value[j]);
                    if (fret != -1) {
                        notes.add(new Note(entry.getKey(), fret));
                    }
                }
            }
        }
        return notes;
    }

    private Map<GuitarString, char[]> extractMap(String[] strings) {
        Map<GuitarString, char[]> acc = new HashMap<GuitarString, char[]>();
        for (String string : strings) {
            String[] split = string.split("\\|");
            acc.put(GuitarString.valueOf(split[0]), split[1].toCharArray());
        }
        return acc;
    }

    private int extractFret(char ch) {
        if (Character.isDigit(ch)) {
            return Character.digit(ch, 10);
        }
        return -1;
    }

}
