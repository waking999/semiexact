package au.edu.cdu.se.is;

public class ISAlgoParam {
    private long allowedRunningTime;
    private int unacceptedResultSize;
    private int acceptedResultSize;
    private int bestResultSize;
    private int threshold;

    int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    long getAllowedRunningTime() {
        return allowedRunningTime;
    }

    public void setAllowedRunningTime(long allowedRunningTime) {
        this.allowedRunningTime = allowedRunningTime;
    }

    public int getUnacceptedResultSize() {
        return unacceptedResultSize;
    }

    public void setUnacceptedResultSize(int unacceptedResultSize) {
        this.unacceptedResultSize = unacceptedResultSize;
    }

    public int getAcceptedResultSize() {
        return acceptedResultSize;
    }

    public void setAcceptedResultSize(int acceptedResultSize) {
        this.acceptedResultSize = acceptedResultSize;
    }

    public int getBestResultSize() {
        return bestResultSize;
    }

    public void setBestResultSize(int bestResultSize) {
        this.bestResultSize = bestResultSize;
    }


}
