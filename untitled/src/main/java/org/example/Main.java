package org.example;

import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Main {
    public static void main(String[] args) {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

        //Kasse kasse=new Kasse(100,80,130);
       // csg.view(kasse.box(),kasse.holes());
       // csg.view(kasse.holesinthWall());
        //csg.view(kasse.display());

        BoxWithBox box=new BoxWithBox(100,80,130);

        csg.view(box.display());




    }
}