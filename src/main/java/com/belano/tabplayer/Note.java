package com.belano.tabplayer;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Note {

    private GuitarString string;
    private int fret;

    public Note(GuitarString string, int fret) {
        this.string = string;
        this.fret = fret;
    }

    public static Note A(int i) {
        return new Note(GuitarString.A, i);
    }

    public static Note B(int i) {
        return new Note(GuitarString.B, i);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("string", this.string).append("fret", this.fret).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Note) {
            final Note other = (Note) obj;
            return new EqualsBuilder().append(string, other.string).append(fret, other.fret).isEquals();
        } else {
            return false;
        }
    }

}
