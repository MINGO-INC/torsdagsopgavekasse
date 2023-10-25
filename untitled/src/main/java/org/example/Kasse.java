package org.example;

import org.abstractica.javacsg.*;

import java.util.ArrayList;
import java.util.List;

public class Kasse {
private double height;
private double width;
private double length;
private final double tolerance = 16;
private final double diameter = 8;
JavaCSG csg= JavaCSGFactory.createNoCaching();


    public Kasse(double height, double width, double length){
    this.height = height;
    this.width = width;
    this.length = length;

    }

    public void holes(){
        double area = tolerance+diameter;
        double amountOfHoles=Math.floor(length/area);

        System.out.println(amountOfHoles);
    }




    public Geometry3D box(){
        double diff=10;
        List<Geometry3D> parts = new ArrayList<>();

        Geometry3D innerBox = csg.box3D(length,width,height,false);
        Geometry3D outerBox=csg.box3D(length+diff,width+diff,height,false);
        Geometry3D base = csg.translate3DZ(diff).transform(innerBox);
        Geometry3D cutbox=csg.difference3D(outerBox,base);
        return csg.union3D(cutbox);
    }




}
