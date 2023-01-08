package hundun.gdxgame.corelib.starter;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import hundun.gdxgame.corelib.base.save.AbstractSaveHandler;
import hundun.gdxgame.corelib.base.save.ISaveTool;

/**
 * 
 */
public class StarterSaveHandler<T_ROOT_SAVE, T_SYSTEM_SAVE, T_GAMEPLAY_SAVE> extends AbstractSaveHandler<T_ROOT_SAVE> {

    private boolean gameSaveInited = false;
    private boolean systemSettingInited = false;
    private final List<ISubGameplaySaveHandler<T_GAMEPLAY_SAVE>> subGameplaySaveHandlers = new ArrayList<>();
    private final List<ISubSystemSettingSaveHandler<T_SYSTEM_SAVE>> subSystemSettingSaveHandlers = new ArrayList<>();
    private final IFactory<T_ROOT_SAVE, T_SYSTEM_SAVE, T_GAMEPLAY_SAVE> factory;
    
    public StarterSaveHandler(
            IFactory<T_ROOT_SAVE, T_SYSTEM_SAVE, T_GAMEPLAY_SAVE> factory, 
            ISaveTool<T_ROOT_SAVE> saveTool
            ) {
        super(saveTool);
        this.factory = factory;
    }
    
    @Override
    protected void applySystemSetting(T_ROOT_SAVE rootSaveData) {
        systemSettingInited = true;
        if (factory.getSystemSave(rootSaveData) != null) {
            subSystemSettingSaveHandlers.forEach(it -> it.applySystemSetting(factory.getSystemSave(rootSaveData)));
        }
    }
    
    @Override
    protected T_ROOT_SAVE currentSituationToSaveData() {
        T_GAMEPLAY_SAVE gameplaySave;
        if (gameSaveInited) {
            gameplaySave = factory.newGameplaySave();
            subGameplaySaveHandlers.forEach(it -> it.currentSituationToSaveData(gameplaySave));
        } else {
            gameplaySave = null;
        }
        
        T_SYSTEM_SAVE systemSettingSave;
        if (systemSettingInited) {
            systemSettingSave = factory.newSystemSave();
            subSystemSettingSaveHandlers.forEach(it -> it.currentSituationToSystemSetting(systemSettingSave));
        } else {
            systemSettingSave = null;
        }
        return factory.newRootSave(
                gameplaySave,
                systemSettingSave 
                );
    }
    
    @Override
    protected T_ROOT_SAVE genereateNewGameSaveData() {
        T_ROOT_SAVE rootSaveData = factory.newRootSave(null, null);
        return rootSaveData;
    }

    @Override
    protected void applyGameSaveData(T_ROOT_SAVE rootSaveData) {
        gameSaveInited = true;
        if (factory.getGameplaySave(rootSaveData) != null) {
            subGameplaySaveHandlers.forEach(it -> it.applyGameSaveData(factory.getGameplaySave(rootSaveData)));
        }
    }

    public static interface IFactory<T_ROOT_SAVE, T_SYSTEM_SAVE, T_GAMEPLAY_SAVE> {

        // delegate getter
        T_SYSTEM_SAVE getSystemSave(T_ROOT_SAVE rootSaveData);
        T_GAMEPLAY_SAVE getGameplaySave(T_ROOT_SAVE rootSaveData);
        
        // delegate constructor
        T_ROOT_SAVE newRootSave(T_GAMEPLAY_SAVE gameplaySave, T_SYSTEM_SAVE systemSettingSave);
        T_GAMEPLAY_SAVE newGameplaySave();
        T_SYSTEM_SAVE newSystemSave();
        
    }
    
    public static interface ISubGameplaySaveHandler<T_GAMEPLAY_SAVE> {
        void applyGameSaveData(T_GAMEPLAY_SAVE gameplaySave);
        void currentSituationToSaveData(T_GAMEPLAY_SAVE gameplaySave);
    }
    
    public static interface ISubSystemSettingSaveHandler<T_SYSTEM_SAVE> {
        void applySystemSetting(T_SYSTEM_SAVE systemSettingSave);
        void currentSituationToSystemSetting(T_SYSTEM_SAVE systemSettingSave);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void registerSubHandler(Object object) {
        if (object instanceof ISubGameplaySaveHandler) {
            subGameplaySaveHandlers.add((ISubGameplaySaveHandler<T_GAMEPLAY_SAVE>)object);
            Gdx.app.log(this.getClass().getSimpleName(), object.getClass().getSimpleName() + " register as " + ISubGameplaySaveHandler.class.getSimpleName());
        }
        if (object instanceof ISubSystemSettingSaveHandler) {
            subSystemSettingSaveHandlers.add((ISubSystemSettingSaveHandler<T_SYSTEM_SAVE>)object);
            Gdx.app.log(this.getClass().getSimpleName(), object.getClass().getSimpleName() + " register as " + ISubSystemSettingSaveHandler.class.getSimpleName());
        }
    }
}
