/*package org.example;

import org.abstractica.javacsg.*;

import java.util.ArrayList;
import java.util.List;

public class Kasse {
private double height;
private double width;
private double length;
private final double tolerance = 16;
private final double diameter = 8;
private final double diff=10;

    JavaCSG csg= JavaCSGFactory.createNoCaching();


    public Kasse(double height, double width, double length){
    this.height = height;
    this.width = width;
    this.length = length;

    }

    public double holes(){
        double area = tolerance;
        double amountOfHoles=Math.floor(length/area);

        return amountOfHoles;

        }

    //Geometry3D hole= csg.cylinder3D();

    public Geometry3D holesinthWall() {
        List<Geometry3D> cyl = new ArrayList<>();
        double ww=(length-holes()*tolerance)/2;
        for (int i=0;i<holes();i++) {
            Geometry3D cyls = csg.cylinder3D(diameter, tolerance, (int) height, false);
            cyls =csg.translate3D(ww+i*tolerance,height-diff,0).transform(cyls);
            cyls =csg.rotate3DX(csg.degrees(90)).transform(cyls);
           cyls=csg.translate3DX((-1*(length/2)+diff)-1.8).transform(cyls);
           cyls=csg.translate3DY(width/2+diff).transform(cyls);
            cyl.add(cyls);
        }

          return csg.union3D(cyl);



    }
    public Geometry3D box(){
        List<Geometry3D> parts = new ArrayList<>();

        Geometry3D innerBox = csg.box3D(length,width,height,false);
        Geometry3D outerBox=csg.box3D(length+diff,width+diff,height,false);
        Geometry3D base = csg.translate3DZ(diff).transform(innerBox);
        Geometry3D cutbox=csg.difference3D(outerBox,base);
        return csg.union3D(cutbox);
    }

    public Geometry3D display(){
        Geometry3D removehole=csg.difference3D(box(),holesinthWall());
        return csg.union3D(removehole);
    }


}
*/