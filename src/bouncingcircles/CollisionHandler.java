package bouncingcircles;

import gameobject.BoundingBox;
import gameobject.Circle;

/**
 *
 * @author jtsan
 */
public class CollisionHandler {

    public void handleCollision(BoundingBox firstObject, BoundingBox secondObject) {

        Circle c1, c2;

        // Handle collisions between circles
        if (firstObject instanceof Circle && secondObject instanceof Circle) {
            c1 = (Circle) firstObject;
            c2 = (Circle) secondObject;

            double xDist = c1.centerX - c2.centerX;
            double yDist = c1.centerY - c2.centerY;

            // Do a second check to figure out if the circles have actually collided yet
            //double distSquared = Math.pow(c1.centerX - c2.centerX, 2) + Math.pow(c1.centerY - c2.centerY, 2);

            double distance = Math.sqrt(Math.pow(c1.centerX - c2.centerX, 2) + Math.pow(c1.centerY - c2.centerY, 2));


            //System.out.println(String.format("Distance = %f c1.radius + c2.radius = %f", distance, c1.radius + c2.radius));
            if (distance <= c1.radius + c2.radius) {


                c1.color = c2.color = Circle.INTERSECT_COLOR;


                
                 float newVelX1 = (c1.velocityX * (c1.radius - c2.radius) + (2 * c2.radius * c2.velocityX)) / (c1.radius + c2.radius);
                 float newVelY1 = (c1.velocityY * (c1.radius - c2.radius) + (2 * c2.radius * c2.velocityY)) / (c1.radius + c2.radius);
                 float newVelX2 = (c2.velocityX * (c2.radius - c1.radius) + (2 * c1.radius * c1.velocityX)) / (c1.radius + c2.radius);
                 float newVelY2 = (c2.velocityY * (c2.radius - c1.radius) + (2 * c1.radius * c1.velocityY)) / (c1.radius + c2.radius);

                 c1.centerX += newVelX1;
                 c1.centerY += newVelY1;

                 c2.centerX += newVelX2;
                 c2.centerY += newVelY2;

/* c1.x = c1.centerX - c1.radius;
                 c1.y = c1.centerY + c1.radius;
                 c2.x = c2.centerX - c2.radius;
                 c2.y = c2.centerY + c2.radius;
                 */
            }
        }
    }
}
