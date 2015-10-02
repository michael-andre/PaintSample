package com.ecp.sio.paintsample.model;

import java.awt.Graphics;
import java.util.List;

/**
 * Created by Michaël on 29/09/2015.
 */
public class Polygon extends Shape {

    private List<Point> mPoints;

    public Polygon(int x, int y, List<Point> points) {
        super(x, y);
        mPoints = points;
    }

    @Override
    public double getArea() {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        /*
        xMin = mPoints.get(0).x;
        xMax = xMin;
        yMin = mPoints.get(0).y;
        yMax = yMin;
        */
        for (Point p : mPoints) {
            xMin = Math.min(xMin, p.x);
            yMin = Math.min(yMin, p.y);
            xMax = Math.max(xMax, p.x);
            yMax = Math.max(yMax, p.y);
        }
        return (xMax - xMin) * (yMax - yMin);
    }

    @Override
    protected void doDraw(Graphics g) {
        Point start = mPoints.get(mPoints.size() - 1);
        for (Point end : mPoints) {
            g.drawLine(
                    start.x + getX(),
                    start.y + getY(),
                    end.x + getX(),
                    end.y + getY()
            );
            start = end;
        }

        /*int size = mPoints.size();
        int[] x = new int[size];
        int[] y = new int[size];
        for (int i = 0; i < size; i++) {
            Point p = mPoints.get(i);
            x[i] = p.x + getX();
            y[i] = p.y + getY();
        }
        g.drawPolygon(x, y, size);*/
    }

    /**
     * Created by Michaël on 29/09/2015.
     */
    public static class Point {

        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }
}
