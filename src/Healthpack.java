import java.awt.*;

public class Healthpack {


        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public boolean isAlive;//a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;
        public int heal;
        public int boost;
        public int uses;
        public int range;


        public Healthpack (int pXpos, int pYpos, int pdx, int prange) {
            xpos = pXpos;
            ypos = pYpos;
            dx =pdx;
            dy =-1;
            width = 60;
            height = 60;
            range = prange;
            isAlive = true;
            heal = (int) (Math.random()*range)+10;
            boost = (int) (Math.random()*range)+20;
            uses = 3;

//establishes hitbox used for intersections
            hitbox = new Rectangle (xpos, ypos, width, height);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
//makes the health pack bounce around the screen
            if (xpos  > 1000){
                dx = -dx;
            }
            if (xpos  < 0){
                dx = -dx;
            }
            if (ypos > 1000){
                dy = -dy;
            }
            if (ypos < 70){
                dy = -dy;
            }
            xpos = xpos + dx;
            ypos = ypos +dy;

//establishes hitbox used for intersections
            hitbox = new Rectangle (xpos, ypos, width, height);
        }
    }
