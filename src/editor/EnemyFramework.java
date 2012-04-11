package editor;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

import attributes.Attribute;


public class EnemyFramework {

    protected ArrayList<Attribute> attributes;
    @SuppressWarnings("unused")
    private BufferedImage myImage;
    @SuppressWarnings("unused")
    private String myName;

    public EnemyFramework(BufferedImage im, String name, ArrayList<Attribute> b) {
        attributes = b;
        myImage = im;
        myName = name;
    }

    public void addBehavior(Attribute b) {
        attributes.add(b);
    }
}
