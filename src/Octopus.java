import java.awt.*;

public class Octopus {


        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public boolean isAlive;//a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;
        public boolean iscrashing;
        public int health;
        public int totalhealth;
        public int strength;


        public Octopus (int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =-2;
            dy =-3;
            width = 60;
            height = -450;
            isAlive = true;
            iscrashing = false;
            //randomly generated stats
            health =  (int)(Math.random() * 400)+100;
            //total health is used to store the maximum health to make a health bar that shows proportionally how much health it has left
            totalhealth = health;
            strength = (int)(Math.random() * 100)+20;


            hitbox = new Rectangle (xpos, ypos, width, height);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            //if statements create the streaching effect that the octopus is given
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
            //teleports the octopus's hitbox outisde the arena if it dies;
            if (isAlive == true) {
                hitbox = new Rectangle(xpos, ypos, width, height);
            }
            else
            {
                hitbox = new Rectangle(2000,2000,10,10);
            }




        }
    }
