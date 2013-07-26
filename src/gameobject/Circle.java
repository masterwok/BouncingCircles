package gameobject;

import java.awt.Color;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 *
 * @author jtsan
 */
public class Circle extends BoundingBox {

    public final int SEGMENTS = 32;
    public static final Color INTERSECT_COLOR = Color.MAGENTA;
    public static final Color NORMAL_COLOR = Color.CYAN;
    public float radius;
    public float centerX, centerY;       // Center of circle overrides top-left of bounding box
    public float velocityX, velocityY;
    public Color color = NORMAL_COLOR;

    public Circle(float radius, float x, float y, float velocityX, float velocityY) {
        super(x - radius, y + radius, radius * 2, radius * 2);
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public void draw(GL2 gl) {
        //super.draw(gl);
        gl.glPushMatrix();
        gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2f(centerX, centerY);
        for (int n = 0; n <= SEGMENTS; ++n) {
            float t = 2 * (float) Math.PI * n / SEGMENTS;
            gl.glVertex2f(centerX + (float) Math.sin(t) * radius, centerY + (float) Math.cos(t) * radius);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void move() {

        float ballMinX = container.x + radius;
        float ballMinY = container.y - container.height + radius;
        float ballMaxX = container.x + container.width - radius;
        float ballMaxY = container.y - radius;

        // Calculate the ball's new position
        centerX += velocityX;
        centerY += velocityY;

        // Check if the ball moves over the bounds. If so, adjust the position and speed.
        if (centerX < ballMinX) {
            velocityX = -velocityX; // Reflect along normal
            centerX = ballMinX;     // Re-position the ball at the edge
        } else if (centerX > ballMaxX) {
            velocityX = -velocityX;
            centerX = ballMaxX;
        }

        // May cross both x and y bounds
        if (centerY < ballMinY) {
            velocityY = -velocityY;
            centerY = ballMinY;
        } else if (centerY > ballMaxY) {
            velocityY = -velocityY;
            centerY = ballMaxY;
        }

        super.x = centerX - radius;
        super.y = centerY + radius;

    }
}
