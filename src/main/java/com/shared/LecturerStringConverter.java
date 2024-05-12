package com.shared;

import com.Lecturer.Lecturer;

public class LecturerStringConverter extends javafx.util.StringConverter<Lecturer> {
    @Override
    public String toString(Lecturer object) {
        return object == null ? null : object.getName();
    }

    @Override
    public Lecturer fromString(String string) {
        return null;
    }
}
