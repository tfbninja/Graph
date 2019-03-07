package grapher;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Tim Barber
 */
public class Grapher extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Canvas canvas;
    private RedrawTimer timer = new RedrawTimer();
    private Block bg = new Block(0, 0, WIDTH, HEIGHT, Color.BLACK);
    private Color color = Color.DODGERBLUE;
    private double minTheta = 0;
    private double maxTheta = 100 * Math.PI;
    private double theta = 0;
    private double increment = 0.5;
    private Point p = new Point(0, 0, WIDTH / 2, HEIGHT / 2, color);
    private Point p2 = new Point(0, 0, WIDTH / 2, HEIGHT / 2, color);
    private Point p3 = new Point(0, 0, WIDTH / 2, HEIGHT / 2, color);
    private Point p4 = new Point(0, 0, WIDTH / 2, HEIGHT / 2, color);
    private Point p5 = new Point(0, 0, WIDTH / 2, HEIGHT / 2, color);

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        bg.draw(canvas, bg.getColor());
        primaryStage.setTitle("yeet");
        primaryStage.setScene(scene);
        primaryStage.show();
        timer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public class RedrawTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            //GraphicsContext gc = canvas.getGraphicsContext2D();
            color = Color.GOLDENROD;
            //double[] pos1 = lemniscate(300, false, 8, p);
            color = Color.MAROON;
            //double[] pos2 = lemniscate(300, false, p, p2);
            //double[] pos3 = cardiod(100, true, false, p3);
            //double[] pos4 = roseCurve(300, false, 8, p4);
            double[] pos5 = logSpiral(Math.E, 50, p5);
        }
    }

    public String decToHex(int dec) {
        String hex = "";
        while (dec > 0) {
            double exact = dec / 16.0;
            int trunc = dec / 16;
            dec = trunc;
            double diff = exact - trunc;
            if (diff * 16 > 9) {
                hex = (char) (65 + ((int) (diff * 16) - 10)) + hex;
            } else {
                hex = String.valueOf((int) (diff * 16)) + hex;
            }
        }
        while (hex.length() < 2) {
            hex = "0" + hex;
        }
        System.out.println(hex);
        return hex;
    }

    public int wrap(int num) {
        while (num > 255) {
            num -= 255;
        }
        while (num < 0) {
            num += 255;
        }
        return num;
    }

    public double[] roseCurve(double a, boolean cos, double n, Point p) {
        if (theta >= minTheta && theta <= maxTheta) {
            double r = 0;
            if (cos) {
                r = a * Math.cos(n * theta);
            } else {
                r = a * Math.sin(n * theta);
            }
            double x = toX(r, theta);
            double y = toY(r, theta);
            p.setX(x);
            p.setY(y);
            p.setColor(color);
            p.draw(canvas, color);
            double[] pos = {x, y};
            theta += increment;
            return pos;
        }
        double[] blank = new double[2];
        return blank;
    }

    public double[] logSpiral(double base, double divisor, Point p) {
        if (theta >= minTheta && theta <= maxTheta) {
            double r = 0;
            r = Math.pow(base, theta / divisor);
            double x = toX(r, theta);
            double y = toY(r, theta);
            p.setX(x);
            p.setY(y);
            p.setColor(color);
            p.draw(canvas, color);
            double[] pos = {x, y};
            theta += increment;
            return pos;
        }
        double[] blank = new double[2];
        return blank;
    }

    public double[] cardiod(double a, boolean plus, boolean cos, Point p) {
        if (theta >= minTheta && theta <= maxTheta) {
            double r = 0;
            if (cos) {
                if (plus) {
                    r = a + (a * Math.cos(theta));
                } else {
                    r = a - (a * Math.cos(theta));
                }
            } else {
                if (plus) {
                    r = a + (a * Math.sin(theta));
                } else {
                    r = a - (a * Math.sin(theta));
                }
            }
            double x = toX(r, theta);
            double y = toY(r, theta);
            p.setX(x);
            p.setY(y);
            p.setColor(color);
            p.draw(canvas, color);
            double[] pos = {x, y};
            theta += increment;
            return pos;
        }
        double[] blank = new double[2];
        return blank;
    }

    public double[] lemniscate(double a, boolean cos, Point p, Point p2) {
        if (theta >= minTheta && theta <= maxTheta) {
            double r1 = 0;
            double r2 = 0;
            if (cos) {
                r1 = Math.sqrt(a * a * Math.cos(2 * theta));
                r2 = -Math.sqrt(a * a * Math.cos(2 * theta));
            } else {
                r1 = Math.sqrt(a * a * Math.sin(2 * theta));
                r2 = -Math.sqrt(a * a * Math.sin(2 * theta));
            }
            double x1 = toX(r1, theta);
            double y1 = toY(r1, theta);
            double x2 = toX(r2, theta);
            double y2 = toY(r2, theta);
            p.setX(x1);
            p.setY(y1);
            p.setColor(color);
            p.draw(canvas, color);
            p2.setX(x2);
            p2.setY(y2);
            p2.setColor(color);
            p2.draw(canvas, color);
            double[] pos = {x1, y1, x2, y2};
            theta += increment;
            return pos;
        }
        double[] blank = new double[2];
        return blank;
    }

    public double toX(double r, double theta) {
        return r * Math.cos(theta);
    }

    public double toY(double r, double theta) {
        return r * Math.sin(theta);
    }
}

/*
 * The MIT License
 *
 * Copyright (c) 2019 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
