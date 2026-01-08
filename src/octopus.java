import java.awt.*;

public class octopus {


        public String name;                //holds the name of the hero
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public boolean isAlive;//a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;


        // METHOD DEFINITION SECTION

        // Constructor Definition
        // A constructor builds the object when called and sets variable values.


        //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
        // if you put in a String, an int and an int the program will use this constructor instead of the one above.
        public octopus (int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =-2;
            dy =-3;
            width = 60;
            height = -250;
            isAlive = true;
            hitbox = new Rectangle (xpos, ypos, width, 50);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            xpos = xpos + dx;
            if (xpos+ width > 1000){
                dx = -dx;
                width = -width;
            }
            if (xpos -width < 0){
                dx = -dx;
                width = -width;
            }
            height = height + dy;
            if (height < -700){
                dy = -dy;
            }
            if (height > -250){
                dy = -dy;
            }



        }
    }
