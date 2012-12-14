package com.belano.tabplayer;

import java.util.List;

public interface TabParser {

    List<Note> parseAsciiTab(String asciiTabFile);

}
