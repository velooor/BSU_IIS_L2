package by.bsu.zakharchenya.lab.enity;

import java.util.ArrayList;

/**
 * Created by Lagarde on 17.12.2017.
 */
public class LClass {
    private String name;
    private int id;

    public LClass(String name, int id, ArrayList<LAttribute> attributes) {
        this.name = name;
        this.id = id;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    private ArrayList<LAttribute> attributes;

    public LClass(String name, ArrayList<LAttribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public LClass(String name, int[][] attrs) {
        attributes = new ArrayList<LAttribute>();
        this.name = name;
        for(int i = 0; i < attrs.length; i++){
            attributes.add(new LAttribute(attrs[i]));
        }
    }

    public String getName() {
        return name;
    }


    public ArrayList<LAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<LAttribute> attributes) {
        this.attributes = attributes;
    }
}
