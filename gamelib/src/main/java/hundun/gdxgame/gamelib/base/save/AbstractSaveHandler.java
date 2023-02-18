package hundun.gdxgame.gamelib.base.save;

import hundun.gdxgame.gamelib.base.IFrontend;

/**
 * @author hundun
 * Created on 2022/09/09
 */
public abstract class AbstractSaveHandler<T_SAVE> {
    
    private ISaveTool<T_SAVE> saveTool;
    protected IFrontend frontend;
    
    protected abstract void applySystemSetting(T_SAVE saveData);
    protected abstract void applyGameSaveData(T_SAVE saveData);
    protected abstract T_SAVE currentSituationToSaveData();
    protected abstract T_SAVE genereateStarterRootSaveData();
    public abstract void registerSubHandler(Object object);
    
    
    public AbstractSaveHandler(IFrontend frontend, ISaveTool<T_SAVE> saveTool) {
        this.saveTool = saveTool;
        this.frontend = frontend;
    }
    
    public void lazyInitOnGameCreate() {
        this.saveTool.lazyInitOnGameCreate();
    }
    
    public void systemSettingLoadOrNew() {

        T_SAVE saveData;
        if (saveTool.hasSave()) {
            saveData = saveTool.readRootSaveData();
        } else {
            saveData = this.genereateStarterRootSaveData();
        }

        this.applySystemSetting(saveData);
        frontend.log(this.getClass().getSimpleName(), "systemSettingLoad call");
    }
    
    public void gameLoadOrNew(boolean load) {

        T_SAVE saveData;
        if (load && saveTool.hasSave()) {
            saveData = saveTool.readRootSaveData();
        } else {
            saveData = this.genereateStarterRootSaveData();
        }

        this.applyGameSaveData(saveData);
        frontend.log(this.getClass().getSimpleName(), load ? "load game done" : "new game done");
    }
    
    public void gameSaveCurrent() {
        frontend.log(this.getClass().getSimpleName(), "saveCurrent called");
        saveTool.writeRootSaveData(this.currentSituationToSaveData());
    }
    
    public boolean gameHasSave() {
        return saveTool.hasSave();
    }
    
}
