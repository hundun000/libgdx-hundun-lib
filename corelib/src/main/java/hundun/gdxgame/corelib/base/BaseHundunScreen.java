package hundun.gdxgame.corelib.base;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crashinvaders.vfx.VfxManager;

import de.eskalon.commons.screen.ManagedScreen;
import hundun.gdxgame.gamelib.base.LogicFrameHelper;
import lombok.Getter;



/**
 * @author hundun
 * Created on 2021/11/02
 * @param <T_GAME>
 * @param <T_SAVE>
 */
public abstract class BaseHundunScreen<T_GAME extends BaseHundunGame<T_SAVE>, T_SAVE> extends ManagedScreen {
    @Getter
    protected final T_GAME game;
    
    protected final Viewport sharedViewport;
    protected Stage uiStage;
    protected Stage popupUiStage;
    protected Stage backUiStage;
    protected VfxManager vfxManager;
    protected Table uiRootTable;
    protected Table popupRootTable;
    
    // ------ lazy init ------
    
    public BaseHundunScreen(T_GAME game, Viewport sharedViewport) {
        this.game = game;
        //OrthographicCamera camera = new OrthographicCamera();
        //this.sharedViewport = new FitViewport(game.getWidth(), game.getHeight(), camera);
        this.sharedViewport = sharedViewport;
        baseInit();
    }

    protected void baseInit() {
        this.uiStage = new Stage(sharedViewport, game.getBatch());
        this.popupUiStage = new Stage(sharedViewport, game.getBatch());
        this.backUiStage = new Stage(sharedViewport, game.getBatch());

        uiRootTable = new Table();
        uiRootTable.setFillParent(true);
        uiStage.addActor(uiRootTable);

        popupRootTable = new Table();
        popupRootTable.setFillParent(true);
        popupUiStage.addActor(popupRootTable);

        // VfxManager is a host for the effects.
        // It captures rendering into internal off-screen buffer and applies a chain of defined effects.
        // Off-screen buffers may have any pixel format, for this example we will use RGBA8888.
        vfxManager = new VfxManager(Format.RGBA8888);
    }
    
    protected void onLogicFrame() {
        // base-class do nothing
    }

    @Override
    public void render(float delta) {
        sharedViewport.apply();
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.logicFrameHelper != null) {
            boolean isLogicFrame = game.logicFrameHelper.logicFrameCheck(delta);
            if (isLogicFrame) {
                onLogicFrame();
            }
        }
        
        backUiStage.act();
        uiStage.act();
        popupUiStage.act();
        
        // ====== be careful of draw order ======
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        
        // ------ only backUi and UI use vfx ------
        backUiStage.getViewport().apply();
        backUiStage.draw();
        
        belowUiStageDraw(delta);
        uiStage.getViewport().apply();
        uiStage.draw();
        aboveUiStageDraw(delta);
        
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
        
        // ------ popupUi out of vfx ------
        popupUiStage.getViewport().apply();
        popupUiStage.draw();
        renderPopupAnimations(delta, game.getBatch());
    }
    
    protected void belowUiStageDraw(float delta) {
        // base-class do nothing
    }

    protected void aboveUiStageDraw(float delta) {
        // base-class do nothing
    }

    protected void renderPopupAnimations(float delta, SpriteBatch spriteBatch) {
        // base-class do nothing
    }
    
    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void resize(int width, int height) {
//        Gdx.app.log(this.getClass().getSimpleName(), JavaFeatureForGwt.stringFormat(
//                "resize by width = %s, height = %s", 
//                width,
//                height
//                ));
        this.backUiStage.getViewport().update(width, height, true);
        this.uiStage.getViewport().update(width, height, true);
        this.popupUiStage.getViewport().update(width, height, true);
    }
}
