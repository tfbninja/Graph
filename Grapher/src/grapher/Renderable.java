package grapher;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author wkranz
 */
public interface Renderable {

    void draw(Canvas canvas, Color color);
}
