import java.awt.*;

public class Fish {

        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
       public int gxpos;                //the x position
        public int gypos;                //the y position
        public int gdx;                    //the speed of the hero in the x direction
        public int gdy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public int health;
        public int strength;
        public Rectangle hitbox;
        public Rectangle shoothitbox;
        public int bounces;
        public int initialbounce;
        public boolean iscrashing;


    public boolean isAlive;

        public Fish(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =6;
            dy =-2;
            width = 100;
            height = 60;
            shoothitbox = new Rectangle(xpos,ypos, 20,20);
            gdx = dx*2;
            gdy = dy;
            iscrashing = false;
            gxpos = xpos+100;
            gypos = ypos+30;
            health =  (int)(Math.random() * 400)+100;
            strength = (int)(Math.random() * 100)+20;
            bounces = (int)(Math.random()*5)+1;
            initialbounce = bounces;


        }

        public void move() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            if(ypos>400){
                dy=-dy;
            }
            if(ypos<0){
                dy = -dy;
            }
            if(xpos + width >1000){
                dx = -dx;
                width = -width;
            }
            if(xpos + width< 0){
                dx = -dx;
                width = -width;

            }
            if (isAlive == true) {
                hitbox = new Rectangle(xpos, ypos, width, height);
            }


        }

        public void shoot()
        {
            if(bounces > 0) {
                if (gxpos > 1000) {
                    gdx = -gdx;
                    bounces--;
                }
                if (gxpos < 0) {
                    gdx = -gdx;
                    bounces--;
                }
                if (gypos - 20 > 700) {
                    gdy = -gdy;
                    bounces--;
                }
                if (gypos < 0) {
                    gdy = -gdy;
                    bounces--;
                }
            }
            else {
                gxpos = xpos+100;
                gypos = ypos+30;
                gdx = dx*2;
                gdy = dy;
                bounces = initialbounce;
            }

            gxpos = gxpos + gdx;
            gypos = gypos + gdy;
            shoothitbox = new Rectangle(gxpos,gypos, 20,20);


        }
    }


