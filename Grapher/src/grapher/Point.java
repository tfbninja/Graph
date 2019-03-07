package grapher;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Tim Barber
 */
public class Point implements Renderable {

    private double x;
    private double y;
    private double centerX;
    private double centerY;
    private Color color;

    private double lastX;
    private double lastY;

    private boolean first = true;

    public Point() {
        x = 0;
        y = 0;
        centerX = 0;
        centerY = 0;
        this.color = Color.CRIMSON;
        lastX = x;
        lastY = y;
    }

    public Point(double x, double y, double cX, double cY, Color color) {
        this.x = x;
        this.y = y;
        centerX = cX;
        centerY = cY;
        this.color = color;
        lastX = x;
        lastY = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setX(double value) {
        x = value;
    }

    public void setY(double value) {
        y = value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(1.0);
        if (first) {
            lastX = x;
            lastY = y;
            first = false;
        }
        gc.strokeLine(centerX + lastX, centerY + lastY, centerX + x, centerY + y);

        lastX = x;
        lastY = y;
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
