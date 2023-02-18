package hundun.gdxgame.gamelib.base;
/**
 * @author hundun
 * Created on 2022/08/29
 */
public interface IFrontEnd {

    String[] fileGetChilePathNames(String folder);

    String fileGetContent(String string);
    
    void log(String logTag, String format, Object... args);
}
