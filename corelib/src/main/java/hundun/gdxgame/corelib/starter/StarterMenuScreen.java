package hundun.gdxgame.corelib.starter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;

import hundun.gdxgame.corelib.base.BaseHundunGame;
import hundun.gdxgame.corelib.base.BaseHundunScreen;
import hundun.gdxgame.gamelib.base.util.JavaFeatureForGwt;




public class StarterMenuScreen<T_GAME extends BaseHundunGame<T_SAVE>, T_SAVE> 
extends BaseHundunScreen<T_GAME, T_SAVE> {

    int BUTTON_WIDTH = 100;
    int BUTTON_BIG_HEIGHT = 100;
    int BUTTON_SMALL_HEIGHT = 75;
    
    Actor title;
    Actor buttonContinueGame;
    Actor buttonNewGame;
    Actor buttonIntoSettingScreen;
    Image backImage;
    
    public static class Factory {
        
        public static <T_GAME extends BaseHundunGame<T_SAVE>, T_SAVE> void simpleFill(
                StarterMenuScreen<T_GAME, T_SAVE> target,
                T_GAME game, 
                String titleText,
                AtlasRegion backTexture,
                InputListener buttonContinueGameInputListener, 
                InputListener buttonNewGameInputListener
                ) {
            Label titleLabel = new Label(
                    JavaFeatureForGwt.stringFormat("     %s     ", titleText), 
                    game.getMainSkin());
            titleLabel.setFontScale(1.5f);
            
            Image backImage = new Image(backTexture);
            
            Button buttonNewGame = new TextButton("New game", game.getMainSkin());
            buttonNewGame.addListener(buttonNewGameInputListener);
            
            Button buttonContinueGame = new TextButton("Continue game", game.getMainSkin());
            buttonContinueGame.addListener(buttonContinueGameInputListener);
            
            Button buttonIntoSettingScreen = new TextButton("Setting", game.getMainSkin());
            
            target.title = titleLabel;
            target.backImage = backImage;
            target.buttonContinueGame = buttonContinueGame;
            target.buttonNewGame = buttonNewGame;
            target.buttonIntoSettingScreen = buttonIntoSettingScreen;
        }
        
        public static <T_GAME extends BaseHundunGame<T_SAVE>, T_SAVE> StarterMenuScreen<T_GAME, T_SAVE> simpleBuild(
                T_GAME game, 
                Viewport sharedViewport,
                String titleText,
                AtlasRegion backTexture,
                InputListener buttonContinueGameInputListener, 
                InputListener buttonNewGameInputListener
                ) {
            StarterMenuScreen<T_GAME, T_SAVE> target = new StarterMenuScreen<>(game, sharedViewport);
            simpleFill(target, game, titleText, backTexture, buttonContinueGameInputListener, buttonNewGameInputListener);
            return target;
        }
    }
    
    public StarterMenuScreen(T_GAME game, Viewport sharedViewport
            ) {
        super(game, sharedViewport);
    }

    private void initScene2d() {

        backUiStage.addActor(backImage);




        uiRootTable.add(title)
                .row();
        

        if (game.getSaveHandler().hasContinuedGameplaySave()) {
            uiRootTable.add(buttonContinueGame)
                .height(BUTTON_BIG_HEIGHT)
                .fillY()
                .padTop(10)
                .row();
        }

        uiRootTable.add(buttonNewGame)
            .height(game.getSaveHandler().hasContinuedGameplaySave() ? BUTTON_SMALL_HEIGHT : BUTTON_BIG_HEIGHT)
            .fillY()
            .padTop(10)
            .row();

        
    }

    @Override
    public void show() {
        super.show();
        //addInputProcessor(uiStage);
        Gdx.input.setInputProcessor(uiStage);
        game.getBatch().setProjectionMatrix(uiStage.getViewport().getCamera().combined);
        
        initScene2d();
    }

    @Override
    public void dispose() {
        
    }


    @Override
    protected void create() {

    }

}