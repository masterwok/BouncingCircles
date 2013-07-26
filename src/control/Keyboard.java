package control;

import bouncingcircles.BouncingCircles;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jonathan Trowbridge
 */
public class Keyboard implements KeyListener {

    private BouncingCircles controller;

    public Keyboard(BouncingCircles controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_SPACE):
                //controller.generateNewPoints(5);
                break;
            case (KeyEvent.VK_C):
                controller.reset();
                break;
        }
    }
}
