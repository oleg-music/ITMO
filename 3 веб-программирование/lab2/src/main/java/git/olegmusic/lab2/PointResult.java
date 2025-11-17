package git.olegmusic.lab2;

public class PointResult {

    private final String xStr;
    private final String yStr;
    private final String rStr;

    private final boolean hit;
    private final String currentTime;
    private final long execTimeMs;

    public PointResult(String xStr, String yStr, String rStr,
                       boolean hit, String currentTime, long execTimeMs) {
        this.xStr = xStr;
        this.yStr = yStr;
        this.rStr = rStr;
        this.hit = hit;
        this.currentTime = currentTime;
        this.execTimeMs = execTimeMs;
    }

    public String getXStr() {
        return xStr;
    }

    public String getYStr() {
        return yStr;
    }

    public String getRStr() {
        return rStr;
    }

    public boolean isHit() {
        return hit;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public long getExecTimeMs() {
        return execTimeMs;
    }
}
