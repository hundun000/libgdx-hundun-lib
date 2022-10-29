package hundun.gdxgame.corelib.base;

/**
 * @author hundun
 * Created on 2022/08/30
 * @param <T>
 */
public abstract class BaseViewModelContext {

    protected abstract void lazyInitOnGameCreate();
    protected abstract void disposeAll();

}
