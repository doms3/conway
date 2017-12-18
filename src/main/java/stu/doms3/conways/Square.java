package stu.doms3.conways;

import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

class Square {
    private ArrayList<Square> neighbors;
    private PVector location;
    private boolean isAlive;
    private PVector size;
    private PApplet sketch;

    Square( float x, float y, float h, float w, boolean isAlive, PApplet sketch ) {
        location = new PVector( x, y );
        size = new PVector( w, h );
        this.isAlive = isAlive;
        neighbors = new ArrayList<>();
        this.sketch = sketch;
    }

    boolean willUpdate() {
        if( isAlive ) {
            return !( numLivingNeighbors() == 2 || numLivingNeighbors() == 3 );
        } else {
            return numLivingNeighbors() == 3;
        }
    }

    void update() {
        if( willUpdate() ) { changeState(); }
    }

    void changeState() {
        isAlive = !isAlive;
    }

    void show() {
        sketch.fill( isAlive ? 200 : 100 );
        sketch.rect( location.x, location.y, size.x, size.y );
    }

    void addNeighbor( Square square ) {
        neighbors.add( square );
    }

    boolean isAlive() {
        return isAlive;
    }

    private int numLivingNeighbors() {
        int count = 0;
        for( Square s : neighbors ) {
            if( s.isAlive() ) { count++; }
        }
        return count;
    }
}
