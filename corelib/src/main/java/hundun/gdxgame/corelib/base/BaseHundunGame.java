package hundun.gdxgame.corelib.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.transition.ScreenTransition;
import hundun.gdxgame.corelib.base.save.AbstractSaveHandler;
import hundun.gdxgame.corelib.base.util.JavaFeatureForGwt;
import lombok.Getter;


public abstract class BaseHundunGame<T_SAVE> extends ManagedGame<ManagedScreen, ScreenTransition> {
    public boolean debugMode;
    private final int constMainViewportWidth;
    private final int constMainViewportHeight;

    @Getter
    private SpriteBatch batch;

    
    
    @Getter
    private Skin mainSkin;


    // ------ init in createStage1(), or keep null ------
    @Getter
    protected AbstractSaveHandler<T_SAVE> saveHandler;
    protected String mainSkinFilePath;
    
    
    public BaseHundunGame(int viewportWidth, int viewportHeight) {
        this.constMainViewportWidth = viewportWidth;
        this.constMainViewportHeight = viewportHeight;
    }
    
    protected void createStage1() {
        this.batch = new SpriteBatch();
        if (mainSkinFilePath != null) {
            this.mainSkin = new Skin(Gdx.files.internal(mainSkinFilePath));
        }
        this.saveHandler.lazyInitOnGameCreate();
    };
    protected abstract void createStage2();
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
	
	
	@SuppressWarnings("unchecked")
    protected <T extends BaseHundunScreen<?, ?>> T getScreen(Class<T> clazz) {
        return (T) JavaFeatureForGwt.requireNonNull(getScreenManager().getScreen(clazz.getSimpleName()));
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
