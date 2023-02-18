package hundun.gdxgame.corelib.base;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import hundun.gdxgame.gamelib.base.IFrontend;
import hundun.gdxgame.gamelib.base.util.JavaFeatureForGwt;

/**
 * @author hundun
 * Created on 2023/02/21
 */
public class LibgdxFrontend implements IFrontend {

    private static final String FOLDER_CHILD_HINT_FILE_NAME = "list.txt";

    @Override
    public String[] fileGetChilePathNames(String folder) {
        FileHandle file = Gdx.files.internal(folder);
        FileHandle listFile = file.child(LibgdxFrontend.FOLDER_CHILD_HINT_FILE_NAME);
        String listContent = fileGetContent(listFile.path());
        String[] result = listContent.split("\r?\n|\r");
        Gdx.app.log(this.getClass().getSimpleName(), "fileGetChilePathNames result = " + Arrays.toString(result));
        return result;
    }

    @Override
    public String fileGetContent(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        String result = file.readString();
        Gdx.app.log(this.getClass().getSimpleName(), "fileGetContent result.length = " + result.length());
        return result;
    }

    @Override
    public void log(String logTag, String format, Object... args) {
        Gdx.app.log(logTag, JavaFeatureForGwt.stringFormat(format, args));
    }

}
