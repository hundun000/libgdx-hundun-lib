package hundun.gdxgame.gamelib.base.save;

/**
 * @author hundun
 * Created on 2022/04/08
 */
public interface ISaveTool<T_SAVE> {
    void lazyInitOnGameCreate();
    boolean hasRootSave();
    void writeRootSaveData(T_SAVE saveData);
    T_SAVE readRootSaveData();
}