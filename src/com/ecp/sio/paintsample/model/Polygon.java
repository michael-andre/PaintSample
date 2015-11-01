package com.ecp.sio.paintsample.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A model class for a polygon
 */
public class Polygon extends Shape {

    private List<Point> points;

    public Polygon(int x, int y, List<Point> points) {
        super(x, y);
        this.points = points;
    }

    /**
     * This constructor is for our CustomJsonParser
     * @param conf The JSON configuration
     */
    public Polygon(JsonObject conf) {
        super(conf);
        points = new ArrayList<>();
        for (JsonElement pointConf : conf.get("points").getAsJsonArray()) {
            Point point;
            if (pointConf.isJsonArray()) {
                point = new Point(pointConf.getAsJsonArray());
            } else if (pointConf.isJsonObject()) {
                point = new Point(pointConf.getAsJsonObject());
            } else {
                throw new IllegalStateException("Invalid point configuration");
            }
            points.add(point);
        }
    }

    /**
     * To make things simpler, we return the area of the bounding rectangle
     * @return The area of the bounding box
     */
    @Override
    public double getArea() {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (Point p : points) {
            xMin = Math.min(xMin, p.x);
            yMin = Math.min(yMin, p.y);
            xMax = Math.max(xMax, p.x);
            yMax = Math.max(yMax, p.y);
        }
        return (xMax - xMin) * (yMax - yMin);
    }

    @Override
    protected void doDraw(Graphics g) {
        Point start = points.get(points.size() - 1);
        for (Point end : points) {
            g.drawLine(
                    start.x + getX(),
                    start.y + getY(),
                    end.x + getX(),
                    end.y + getY()
            );
            start = end;
        }
    }

    /**
     * This is a nested class to represent a single point.
     * It must static not to depend on the nesting instance, ie to have the same behaviour as if the class was not nested.
     * Non-static nested classes are also possible, but it is an advanced use.
     */
    public static class Point {

        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(JsonObject conf) {
            this.x = conf.get("x").getAsInt();
            this.y = conf.get("y").getAsInt();
        }

        public Point(JsonArray conf) {
            this.x = conf.get(0).getAsInt();
            this.y = conf.get(1).getAsInt();
        }

    }
}