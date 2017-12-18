package stu.doms3.conways;

import processing.core.PApplet;

public class ConwaysGameOfLife extends PApplet {
    private static final int DEFAULT_SIZE = 20;

    private int time;
    private Grid grid;
    private boolean paused = true;

    public static void main( String[] args ) {
        PApplet.main("stu.doms3.conways.ConwaysGameOfLife", new String[]{ "100" } );
    }

    @Override
    public void settings() {
        size( 900, 900 );
    }

    @Override
    public void setup() {
        int size;
        if( args != null ) {
            if( args.length == 2 ) {
                randomSeed( args[0].hashCode() );
                size = Integer.parseInt( args[1] );
            } else if( args.length == 1 ) {
                size = Integer.parseInt( args[0] );
            } else {
                size = DEFAULT_SIZE;
            }
        } else {
            size = DEFAULT_SIZE;
        }
        noStroke();
        time = 0;
        grid = new Grid(size, (row, col) -> random(1) < 0.2, this );
    }

    @Override
    public void draw() {
        if( frameCount % 5 == 0 ) { this.advanceTime(); }
    }

    @Override
    public void mousePressed() {
        grid.onMouseEvent();
    }

    @Override
    public void keyPressed() {
        if( key == ENTER || key == RETURN ) {
            this._pause();
        }
    }

    private void advanceTime() {
        if( !paused ) {
            time++;
            grid.update();
        }
        grid.show();
    }


    private void _pause() {
        paused = !paused;
    }
}
