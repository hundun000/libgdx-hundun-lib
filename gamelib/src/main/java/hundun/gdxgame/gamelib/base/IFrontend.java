package hundun.gdxgame.gamelib.base;
/**
 * @author hundun
 * Created on 2022/08/29
 */
public interface IFrontend {

    String[] fileGetChilePathNames(String folder);

    String fileGetContent(String filePath);
    
    void log(String logTag, String format, Object... args);
}
