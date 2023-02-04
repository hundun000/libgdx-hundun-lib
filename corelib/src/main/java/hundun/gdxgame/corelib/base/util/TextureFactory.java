package hundun.gdxgame.corelib.base.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author hundun
 * Created on 2023/02/07
 */
public class TextureFactory {
    public static Texture createBorderBoard(int width, int height, float grayColor, int borderWidth) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB565);
        pixmap.setColor(grayColor + 0.1f, grayColor + 0.1f, grayColor + 0.1f, 1.0f);
        pixmap.fill();
        pixmap.setColor(grayColor, grayColor, grayColor, 1.0f);
        pixmap.fillRectangle(borderWidth, borderWidth, width - borderWidth * 2, height - borderWidth * 2);
        Texture texture = new Texture(pixmap);
        return texture;
    }
    
    public static Texture createAlphaBoard(int width, int height, Color color, float alpha) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA4444);
        pixmap.setColor(color.r, color.g, color.b, alpha);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        return texture;
    }

    public static Texture getSimpleBoardBackground() {
        return createBorderBoard(10, 10, 0.8f, 1);
    }
    
    public static Texture getSimpleBoardBackground(int width, int height) {
        return createBorderBoard(width, height, 0.8f, (int) (width * 0.1));
    }
    
    public static Texture getViewportBasedBoard(int viewportWidth, int viewportHeight, float rate) {
        return createBorderBoard((int)(viewportWidth * rate), (int)(viewportHeight * rate), 0.8f, 1);
    }
    
    public static Texture getViewportBasedAlphaBoard(int viewportWidth, int viewportHeight) {
        return createAlphaBoard(viewportWidth, viewportHeight, Color.WHITE, 0.9f);
    }
}
