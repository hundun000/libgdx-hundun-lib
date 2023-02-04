package hundun.gdxgame.corelib.base.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * @author hundun
 * Created on 2022/08/30
 */
public class DrawableFactory {
    public static Drawable createBorderBoard(int width, int height, float grayColor, int borderWidth) {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(TextureFactory.createBorderBoard(width, height, grayColor, borderWidth)));
        return drawable;
    }
    
    public static Drawable createAlphaBoard(int width, int height, Color color, float alpha) {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(TextureFactory.createAlphaBoard(width, height, color, alpha)));
        return drawable;
    }

    public static Drawable getSimpleBoardBackground() {
        return createBorderBoard(10, 10, 0.8f, 1);
    }
    
    public static Drawable getSimpleBoardBackground(int width, int height) {
        return createBorderBoard(width, height, 0.8f, (int) (width * 0.1));
    }
    
    public static Drawable getViewportBasedBoard(int viewportWidth, int viewportHeight, float rate) {
        return createBorderBoard((int)(viewportWidth * rate), (int)(viewportHeight * rate), 0.8f, 1);
    }
    
    public static Drawable getViewportBasedAlphaBoard(int viewportWidth, int viewportHeight) {
        return createAlphaBoard(viewportWidth, viewportHeight, Color.WHITE, 0.9f);
    }
}
