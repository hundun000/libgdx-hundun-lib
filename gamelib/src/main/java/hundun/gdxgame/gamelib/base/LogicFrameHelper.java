package hundun.gdxgame.gamelib.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hundun
 * Created on 2022/08/31
 */
public class LogicFrameHelper {
    @Getter
    private final int logicFramePerSecond;
    @Getter
    private final float logicFrameLength;
    @Getter
    private int clockCount = 0;
    private float logicFrameAccumulator;

    @Getter
    @Setter
    private boolean logicFramePause;
    @Getter
    @Setter
    private float scale;
    public LogicFrameHelper(int logicFramePerSecond) {
        this.logicFramePerSecond = logicFramePerSecond;
        this.logicFrameLength = 1f / logicFramePerSecond;
        this.scale = 1.0f;
    }
    
    public boolean logicFrameCheck(float delta) {
        logicFrameAccumulator += delta;
        if (logicFrameAccumulator >= logicFrameLength) {
            logicFrameAccumulator -= logicFrameLength;
            if (!logicFramePause) {
                clockCount++;
                return true;
            }
        }
        return false;
    }
    
    public double frameNumToSecond(int frameNum) {
        return frameNum * logicFrameLength / scale;
    }

    public int secondToFrameNum(double second) {
        return (int) (logicFramePerSecond * second * scale);
    }
}
