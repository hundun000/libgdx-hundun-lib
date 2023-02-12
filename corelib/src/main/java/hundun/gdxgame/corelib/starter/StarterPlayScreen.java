package hundun.gdxgame.corelib.starter;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;

import hundun.gdxgame.corelib.base.BaseHundunGame;
import hundun.gdxgame.corelib.base.BaseHundunScreen;
import hundun.gdxgame.corelib.base.LogicFrameHelper;
import hundun.gdxgame.corelib.starter.listerner.IGameAreaChangeListener;
import hundun.gdxgame.corelib.starter.listerner.ILogicFrameListener;
import lombok.Getter;


/**
 * @author hundun
 * Created on 2021/12/06
 * @param <T_GAME>
 */
public abstract class StarterPlayScreen<T_GAME extends BaseHundunGame<T_SAVE>, T_SAVE>
        extends BaseHundunScreen<T_GAME, T_SAVE> {

    @Getter
    private String area;
    @Getter
    private final String startArea;

    protected List<ILogicFrameListener> logicFrameListeners = new ArrayList<>();
    protected List<IGameAreaChangeListener> gameAreaChangeListeners = new ArrayList<>();


    public StarterPlayScreen(T_GAME game, String startArea, 
            Viewport viewport,
            int LOGIC_FRAME_PER_SECOND
            ) {
        super(game, viewport);
        this.startArea = startArea;
        this.logicFrameHelper = new LogicFrameHelper(LOGIC_FRAME_PER_SECOND); 
    }

    public void setAreaAndNotifyChildren(String current) {
        String last = this.area;
        this.area = current;

        for (IGameAreaChangeListener gameAreaChangeListener : gameAreaChangeListeners) {
            gameAreaChangeListener.onGameAreaChange(last, current);
        }

    }


    @Override
    public void show() {
        super.show();
        
        Gdx.input.setInputProcessor(uiStage);
        game.getBatch().setProjectionMatrix(uiStage.getViewport().getCamera().combined);

        backUiStage.clear();
        popupRootTable.clear();
        lazyInitBackUiAndPopupUiContent();

        uiRootTable.clear();
        lazyInitUiRootContext();

        lazyInitLogicContext();

        // start area
        setAreaAndNotifyChildren(startArea);
        Gdx.app.log(this.getClass().getSimpleName(), "show done");
    }


    protected abstract void lazyInitLogicContext();

    protected abstract void lazyInitUiRootContext();

    protected abstract void lazyInitBackUiAndPopupUiContent();

    @Override
    protected void onLogicFrame() {
        super.onLogicFrame();
        
        for (ILogicFrameListener logicFrameListener : logicFrameListeners) {
            logicFrameListener.onLogicFrame();
        }
    }
    
}
