package stu.doms3.conways;

import processing.core.PApplet;

import java.util.ArrayList;

class Grid {
    private Square[] squares;
    private int gridSize;
    private PApplet sketch;

    Grid( int size, StartingSquareSelector selector, PApplet sketch ) {
        this.sketch = sketch;
        int numSquares = size * size;
        gridSize = size;
        squares = new Square[ numSquares ];
        for ( int i = 0; i < numSquares; i++ ) {
            float x = ( i % size ) * ( sketch.width / size );
            float y = ( i / size ) * ( sketch.height / size );
            float h = sketch.height / size;
            float w = sketch.width / size;
            if( selector.test( x, y ) ) {
                squares[i] = new Square( x, y, h, w, true, sketch );
            } else {
                squares[i] = new Square( x, y, h, w, false, sketch );
            }
        }

        for( int i = 0; i < numSquares; i++ ) {
            int row = i / size;
            int col = i % size;
            for( int i1 = -1; i1 <= 1; i1++ ) {
                for( int i2 = -1; i2 <= 1; i2++ ) {
                    int newRow = row + i1;
                    int newCol = col + i2;
                    if( newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && !( i1 == 0 && i2 == 0 ) ) {
                        int index = newRow * size + newCol;
                        squares[i].addNeighbor( squares[index] );
                    }
                }
            }

        }
    }

    public void update() {
        ArrayList<Square> toChange = new ArrayList<>( squares.length );

        for( Square s : squares ) {
            if( s.willUpdate() ) {
                toChange.add( s );
            }
        }

        for( Square s : toChange ) {
            s.changeState();
            s.show();
        }
    }

    public void show() {
        for( Square s : squares ) {
            s.show();
        }
    }

    public void onMouseEvent() {
        int row = (int) Math.floor( sketch.mouseX * 1.0 / sketch.width * gridSize );
        int col = (int) Math.floor( sketch.mouseY * 1.0 / sketch.height * gridSize );
        int index = col * gridSize + row;
        squares[index].changeState();
        squares[index].show();
    }

}