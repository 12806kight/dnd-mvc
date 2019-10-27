package org.launchcode.dndmvc.models;

import java.util.ArrayList;

public class DndData {
    static ArrayList<Dnd> classes = new ArrayList<>();

    // getAll
    public static ArrayList<Dnd> getAll() {
        return classes;
    }

    // add
    public static void add(Dnd newDnd) {
        classes.add(newDnd);
    }


    // getById
    public static Dnd getById(int id) {

        Dnd theDnd = null;

        for (Dnd candidateDnd : classes) {
            if (candidateDnd.getDndId() == id) {
                theDnd = candidateDnd;
            }
        }

        return theDnd;
    }


}
