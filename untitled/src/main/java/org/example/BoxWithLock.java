package org.example;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;
import java.util.List;

public class BoxWithLock {
private double height;
private double width;
private double length;
private final double tolerance = 16;
private final double diameter = 8;
private final double diff=10;

    JavaCSG csg= JavaCSGFactory.createNoCaching();


    public BoxWithLock(double height, double width, double length){
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
    public Geometry3D lid(){

        Geometry3D holeInTheSide=csg.box3D(length,width,diff/2,false);
        holeInTheSide=csg.translate3DZ(height).transform(holeInTheSide);
        Geometry3D holeinouterBox=csg.box3D(length+diff,width+diff,diff/2,false);
        holeinouterBox=csg.translate3DZ(height+diff/2).transform(holeinouterBox);
        Geometry3D circle=csg.cylinder3D(diameter*3,diff/2,128,false);
        Geometry3D hole=csg.cylinder3D((diameter*3)/1.3,diff,128,false);
        Geometry3D handle = csg.difference3D(circle,hole);
        handle=csg.rotate3DX(csg.degrees(90)).transform(handle);
        handle=csg.translate3DZ(height+diff).transform(handle);
        handle=csg.translate3DY(length/length+diff/4).transform(handle);
        Geometry3D lid = csg.union3D(holeInTheSide,holeinouterBox,handle);
        return lid = csg.translate3DZ(25).transform(lid);
    }
    public Geometry3D lock(){
        Geometry3D topMount = csg.box3D(diff*2,diff,diff,false);
        lid();
        topMount=csg.translate3DZ(height).transform(topMount);
        //topMount=csg.rotate3DY(csg.degrees(90)).transform(topMount);
        return csg.union3D(lid(),topMount);
    }



    public Geometry3D display(){
        Geometry3D removehole=csg.difference3D(box(),holesinthWall());
        return csg.union3D(removehole,lock());
    }


}
