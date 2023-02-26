package hundun.gdxgame.gamelib.starter.save;
/**
 * @author hundun
 * Created on 2023/03/02
 */
public interface IRootSaveExtension<T_ROOT_SAVE, T_SYSTEM_SAVE, T_GAMEPLAY_SAVE> {

    // delegate getter
    T_SYSTEM_SAVE getSystemSave(T_ROOT_SAVE rootSaveData);
    T_GAMEPLAY_SAVE getGameplaySave(T_ROOT_SAVE rootSaveData);
    
    // delegate constructor
    T_ROOT_SAVE newRootSave(T_GAMEPLAY_SAVE gameplaySave, T_SYSTEM_SAVE systemSettingSave);
    T_GAMEPLAY_SAVE newGameplaySave();
    T_SYSTEM_SAVE newSystemSave();
    
}