package edu.ucsb.cs48.a_night_in_iv;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by kovlv on 5/6/2017.
 */
public class MapSection {
    ArrayList<BufferedImage> Tarrains;
    ArrayList<Sprite> Sprites;

    public void update(){
        for (Sprite s: Sprites) {
            s.update();
        }
    }

}

