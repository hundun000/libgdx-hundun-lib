package hundun.gdxgame.corelib.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.transition.ScreenTransition;
import hundun.gdxgame.gamelib.base.save.AbstractSaveHandler;
import hundun.gdxgame.gamelib.base.util.JavaFeatureForGwt;
import lombok.Getter;


public abstract class BaseHundunGame<T_SAVE> extends ManagedGame<ManagedScreen, ScreenTransition> {
    public boolean debugMode;
    protected final int constMainViewportWidth;
    protected final int constMainViewportHeight;

    @Getter
    protected SpriteBatch batch;

    
    
    @Getter
    protected Skin mainSkin;

    @Getter
    protected final LibgdxFrontend frontend;
    // ------ init in createStage1(), or keep null ------
    @Getter
    protected AbstractSaveHandler<T_SAVE> saveHandler;
    protected String mainSkinFilePath;
    
    
    public BaseHundunGame(int viewportWidth, int viewportHeight) {
        this.constMainViewportWidth = viewportWidth;
        this.constMainViewportHeight = viewportHeight;
        this.frontend = new LibgdxFrontend();
    }
    
    /**
     * 只依赖Gdx static的成员
     */
    protected void createStage1() {
        this.batch = new SpriteBatch();
        if (mainSkinFilePath != null) {
            this.mainSkin = new Skin(Gdx.files.internal(mainSkinFilePath));
        }
        this.saveHandler.lazyInitOnGameCreate();
    };
    /**
     * 只依赖Stage1的成员
     */
    protected abstract void createStage2();
    /**
     * 自由依赖
     */
    protected abstract void createStage3();
    
	@Override
	public void create() {
	    super.create();
	    
	    createStage1();
        createStage2();
        createStage3();
	}
	
	
    // ====== ====== ======



	@Override
	public void dispose () {
		batch.dispose();
	}


    @Override
    public int getWidth() {
        return constMainViewportWidth;
    }
    
    @Override
    public int getHeight() {
        return constMainViewportHeight;
    }

}
