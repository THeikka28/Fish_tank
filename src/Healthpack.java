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




        // METHOD DEFINITION SECTION

        // Constructor Definition
        // A constructor builds the object when called and sets variable values.


        //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
        // if you put in a String, an int and an int the program will use this constructor instead of the one above.
        public Healthpack (int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =-1;
            dy =-1;
            width = 60;
            height = -60;
            isAlive = true;
            heal = (int) (Math.random()*100)+50;
            boost = (int) (Math.random()*50)+20;


            hitbox = new Rectangle (xpos, ypos, width, height);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            xpos = xpos + dx;
            if (xpos+ width > 1000){
                dx = -dx;
            }
            if (xpos -width < 0){
                dx = -dx;
            }
            if (height < -650){
                dy = -dy;
            }
            if (height > -350){
                dy = -dy;
            }
            if (isAlive == true) {
                hitbox = new Rectangle(xpos, ypos, width, height);
            }
            else {
                hitbox = new Rectangle(2000,2000,10,10);
            }

        }
    }
