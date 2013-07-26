package control;

import bouncingcircles.BouncingCircles;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import gameobject.Circle;

/**
 *
 * @author jtsan
 */
public class Mouse implements MouseListener, MouseMotionListener {

    private BouncingCircles controller;
    private int height;

    public Mouse(BouncingCircles controller, int height) {
        this.controller = controller;
        this.height = height;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 6; i++) {
            float minVelocity = -4;
            float maxVelocity = 4;
            float minRadius = 15;
            float maxRadius = 45;
            float minX, maxX, minY, maxY;
            minX = minY = -100;
            maxX = maxY = 100;

            float radius = minRadius + (float) (Math.random() * ((maxRadius - minRadius) + 1));
            float velocityX = minVelocity + (float) (Math.random() * ((maxVelocity - minVelocity) + 1));
            float velocityY = minVelocity + (float) (Math.random() * ((maxVelocity - minVelocity) + 1));

            velocityX++;
            velocityY++;

            float x = minX + (float) (Math.random() * ((maxX - minX) + 1));
            float y = minY + (float) (Math.random() * ((maxY - minY) + 1));
            controller.insertGameObject(new Circle(radius, e.getX() + x, height - e.getY() + y, velocityX, velocityY));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
