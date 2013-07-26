package quadtree;

import gameobject.BoundingBox;
import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL2;

/**
 *
 * @author Jonathan Trowbridge
 */
public class QuadTree {

    private static final int MAX_LEVEL = 6;
    private static final int MAX_CAPACITY = 1;
    private BoundingBox boundary;
    public List<BoundingBox> objects;
    private int level;
    private QuadTree northEast, northWest, southWest, southEast;

    public QuadTree(int level, BoundingBox boundary) {
        objects = new ArrayList();
        this.level = level;
        this.boundary = boundary;
    }

    public void draw(GL2 gl) {
        boundary.draw(gl);
        if (northEast != null) {
            northEast.draw(gl);
            northWest.draw(gl);
            southWest.draw(gl);
            southEast.draw(gl);
        }
    }

    public boolean insert(BoundingBox b) {

        if (!boundary.contains(b.x, b.y, b.width, b.height))
            return false;

        if (objects.size() < MAX_CAPACITY && northEast == null) {
            objects.add(b);
            return true;
        }

        if (level < MAX_LEVEL) {
            if (objects.size() == MAX_CAPACITY && northEast == null)
                split();

            if (northEast.insert(b))
                return true;
            else if (northWest.insert(b))
                return true;
            else if (southWest.insert(b))
                return true;
            else if (southEast.insert(b))
                return true;

        }

        objects.add(b);
        return true;

    }

    public List retrieve(List returnObjects, BoundingBox b) {
        QuadTree quad = null;

        if (northEast != null) {
            if (northEast.boundary.contains(b.x, b.y, b.width, b.height))
                quad = northEast;
            else if (northWest.boundary.contains(b.x, b.y, b.width, b.height))
                quad = northWest;
            else if (southWest.boundary.contains(b.x, b.y, b.width, b.height))
                quad = southWest;
            else if (southEast.boundary.contains(b.x, b.y, b.width, b.height))
                quad = southEast;

            if (quad != null)
                quad.retrieve(returnObjects, b);
        }



        returnObjects.addAll(objects);

        return returnObjects;
    }

    private void split() {
        float subWidth = boundary.width / 2;
        float subHeight = boundary.height / 2;
        northEast = new QuadTree(level + 1, new BoundingBox(boundary.x + subWidth, boundary.y, subWidth, subHeight));
        northWest = new QuadTree(level + 1, new BoundingBox(boundary.x, boundary.y, subWidth, subHeight));
        southWest = new QuadTree(level + 1, new BoundingBox(boundary.x, boundary.y - subHeight, subWidth, subHeight));
        southEast = new QuadTree(level + 1, new BoundingBox(boundary.x + subWidth, boundary.y - subHeight, subWidth, subHeight));

        // Bump the particles down to the children
        for (BoundingBox b : objects) {
            if (northEast.insert(b))
                continue;
            if (northWest.insert(b))
                continue;
            if (southWest.insert(b))
                continue;
            if (southEast.insert(b))
                continue;
        }

        // There should be no particles in current node at this point
        objects.clear();
    }

    public void clear() {
        objects.clear();
        if (northEast != null) {
            northEast.clear();
            northWest.clear();
            southWest.clear();
            southEast.clear();
            northEast = northWest = southWest = southEast = null;
        }
    }

    @Override
    public String toString() {
        if (northEast != null)
            return String.format("ROOT = %d, NE = %d, NW = %d, SW = %d, SE = %d", objects.size(), northEast.objects.size(), northWest.objects.size(), southWest.objects.size(), southEast.objects.size())
                    + String.format("\n  %s", northEast.toString())
                    + String.format("\n  %s", northWest.toString())
                    + String.format("\n  %s", southWest.toString())
                    + String.format("\n  %s", southEast.toString());
        //else
        //     return String.format("(%f, %f) = %d", boundary.x, boundary.y, objects.size());
        return "";
    }
}