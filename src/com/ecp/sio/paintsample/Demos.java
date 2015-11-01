package com.ecp.sio.paintsample;

import com.ecp.sio.paintsample.model.Circle;
import com.ecp.sio.paintsample.model.Rectangle;
import com.ecp.sio.paintsample.model.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Some very basic java language demos
 */
public class Demos {

    public static void main(String[] args) {

        int a = 2;
        float b = 3.2f;
        double c = 3.1;
        boolean d = true;
        char e = 'z';
        long f = 586L;
        String g = "Hello world";

        double h = 2. / 3;
        boolean and = d && false;
        boolean or = d || true;

        if (a == 2) {
            System.out.println(a + " equals 2");
        } else if (a == 3) {
            System.out.println(a + " equals 3");
        } else {

        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Loop " + i);
        }

        a = Math.round(b);

        System.out.println(a);

        Rectangle rect1 = new Rectangle(10, 20, 200, 100);
        Rectangle rect2 = new Rectangle(10, 20, 200, 100);
        Rectangle rect3 = rect1;

        Shape shape = new Rectangle(10, 20, 200, 100);
        System.out.println(shape.getArea());

        boolean test = rect1.equals(rect2);

        Circle circ1 = new Circle(150, 120, 100);

        int[] table1 = new int[3];
        int[] table2 = new int[] { 1, 2, 3 };
        int[] table3 = { 1, 2, 3 };

        List<String> list = new ArrayList<>();
        list.add("toto");
        list.add("tata");

        //list.add(rect1);
        //list.remove(0);
        //String str = list.get(0);

        String[] strings = { "Hello", "world" };
        String first = strings[0];

        for (String s : list) {
            list.remove(s);
        }

        /*for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
        }*/

    }
}
