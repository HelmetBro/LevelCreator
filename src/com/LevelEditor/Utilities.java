package com.LevelEditor;


import com.LevelEditor.Shapes.Path;
import com.LevelEditor.Shapes.Point;
import com.LevelEditor.Shapes.Rectangle;

import java.util.ArrayList;
import java.util.Stack;

public class Utilities {

    /**
     * @param angle is in degrees.
     */
    public static float undoAngleMods(float angle) {
        return 360 - angle;
    }

    /**
     * @param angle is in degrees.
     */
    public static float normalize(float angle) {
        return (angle < 0) ? angle + 360 : angle;
    }

    public static boolean pathCollide(Path path, int inputX, int inputY) {
        return circleCollide(path.startPoint(), Path.START_POINT_RADIUS + 5, inputX, inputY);
    }

    //jordan curve theorem
    public static boolean polyCollide(int numVertices, int[] xVertices, int[] yVertices, int inputX, int inputY) {
        int i, j;
        boolean result = false;
        for (i = 0, j = numVertices - 1; i < numVertices; j = i++)
            if ((yVertices[i] > inputY != yVertices[j] > inputY) &&
                    (inputX < (xVertices[j] - xVertices[i]) * (inputY - yVertices[i]) / (yVertices[j] - yVertices[i]) + xVertices[i]))
                result = !result;
        return result;
    }

    public static boolean circleCollide(Point center, int radius, int inputX, int inputY) {
        return distance(new Point(inputX, inputY), center) <= radius;
    }

    public static boolean ellipseCollide(Point center, int ellipseW, int ellipseH, int inputX, int inputY) {
        float radiusX = ellipseW / 2;
        float radiusY = ellipseH / 2;

        float xOffset = inputX - center.getX();
        float yOffset = inputY - center.getY();

        return ((xOffset * xOffset) / (radiusX * radiusX)
                +
                (yOffset * yOffset) / (radiusY * radiusY)) <= 1;
    }

    public static boolean rectangleCollide(Rectangle rect, int inputX, int inputY) {
        return inputX >= rect.getTopLeft().getX() && inputX <= rect.getTopLeft().getX() + rect.width &&
                inputY >= rect.getTopLeft().getY() && inputY <= rect.getTopLeft().getY() + rect.height;
    }

    public static int round(float value, int incrementToRound) {
        return Math.round(value / incrementToRound) * incrementToRound;
    }

    public static int GCD(int a, int b) {
        return (b == 0) ? a : GCD(b, a % b);
    }

    public static int[] intListToStaticArray(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = integers.get(i);

        return ret;
    }

    public static float distance(Point p1, Point p2) {
        final float x_d = p1.getX() - p2.getX();
        final float y_d = p1.getY() - p2.getY();
        return (float) Math.sqrt(x_d * x_d + y_d * y_d);
    }

    public static float distance2(Point p1, Point p2) {
        final float x_d = p1.getX() - p2.getX();
        final float y_d = p1.getY() - p2.getY();
        return x_d * x_d + y_d * y_d;
    }

    public static Point compute2DPolygonCentroid(Stack<Point> points) {
        return compute2DPolygonCentroid(new ArrayList<>(points));
    }

    public static Point compute2DPolygonCentroid(ArrayList<Point> points) {
        int numPoints = points.size();

        Point centroid = new Point();
        double signedArea = 0.0;
        double x0; // Current vertex X
        double y0; // Current vertex Y
        double x1; // Next vertex X
        double y1; // Next vertex Y
        double a;  // Partial signed area

        // For all vertices except last
        int i;
        for (i = 0; i < numPoints - 1; ++i) {
            x0 = points.get(i).getX();
            y0 = points.get(i).getY();
            x1 = points.get(i + 1).getX();
            y1 = points.get(i + 1).getY();
            a = x0 * y1 - x1 * y0;
            signedArea += a;
            centroid.setX(centroid.getX() + (int) ((x0 + x1) * a));
            centroid.setY(centroid.getY() + (int) ((y0 + y1) * a));
        }

        // Do last vertex separately to avoid performing an expensive
        // modulus operation in each iteration.
        x0 = points.get(i).getX();
        y0 = points.get(i).getY();
        x1 = points.get(0).getX();
        y1 = points.get(0).getY();
        a = x0 * y1 - x1 * y0;
        signedArea += a;
        centroid.setX(centroid.getX() + (int) ((x0 + x1) * a));
        centroid.setY(centroid.getY() + (int) ((y0 + y1) * a));

        signedArea *= 0.5;
        centroid.setX((int) (centroid.getX() / (6.0 * signedArea)));
        centroid.setY((int) (centroid.getY() / (6.0 * signedArea)));

        return centroid;
    }

//    public static int[] reverse(int[] x) {
//
//        int[] d = new int[x.length];
//
//        for (int i = 0; i < x.length; i++)
//            d[i] = x[x.length - 1 - i];
//
//        return d;
//    }

//    public static int[] insertionSort(int[] input) {
//
//        int temp;
//
//        for (int i = 1; i < input.length; i++) {
//            for (int j = i; j > 0; j--) {
//                if (input[j] < input[j - 1]) {
//                    temp = input[j];
//                    input[j] = input[j - 1];
//                    input[j - 1] = temp;
//                }
//            }
//        }
//
//        return input;
//    }

//    public static boolean isDouble(String check) {
//        final String Digits = "(\\p{Digit}+)";
//        final String HexDigits = "(\\p{XDigit}+)";
//        // an exponent is 'e' or 'E' followed by an optionally
//        // signed decimal integer.
//        final String Exp = "[eE][+-]?" + Digits;
//        final String fpRegex =
//                ("[\\x00-\\x20]*" + // Optional leading "whitespace"
//                        "[+-]?(" +         // Optional sign character
//                        "NaN|" +           // "NaN" string
//                        "Infinity|" +      // "Infinity" string
//
//                        // A decimal floating-point string representing a finite positive
//                        // number without a leading sign has at most five basic pieces:
//                        // Digits . Digits ExponentPart FloatTypeSuffix
//                        //
//                        // Since this method allows integer-only strings as input
//                        // in addition to strings of floating-point literals, the
//                        // two sub-patterns below are simplifications of the grammar
//                        // productions from the Java Language Specification, 2nd
//                        // edition, section 3.10.2.
//
//                        // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
//                        "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +
//
//                        // . Digits ExponentPart_opt FloatTypeSuffix_opt
//                        "(\\.(" + Digits + ")(" + Exp + ")?)|" +
//
//                        // Hexadecimal strings
//                        "((" +
//                        // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
//                        "(0[xX]" + HexDigits + "(\\.)?)|" +
//
//                        // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
//                        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
//
//                        ")[pP][+-]?" + Digits + "))" +
//                        "[fFdD]?))" +
//                        "[\\x00-\\x20]*");// Optional trailing "whitespace"
//        return Pattern.matches(fpRegex, check);
//    }

//    public static boolean isInteger(String str) {
//        if (str == null) {
//            return false;
//        }
//        int length = str.length();
//        if (length == 0) {
//            return false;
//        }
//        int i = 0;
//        if (str.charAt(0) == '-') {
//            if (length == 1) {
//                return false;
//            }
//            i = 1;
//        }
//        for (; i < length; i++) {
//            char c = str.charAt(i);
//            if (c < '0' || c > '9') {
//                return false;
//            }
//        }
//        return true;
//    }

}
