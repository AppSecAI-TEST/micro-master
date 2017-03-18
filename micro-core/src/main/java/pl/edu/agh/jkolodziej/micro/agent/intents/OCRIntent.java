package pl.edu.agh.jkolodziej.micro.agent.intents;

/**
 * @author - Jakub Kołodziej
 */
public class OCRIntent extends ServiceIntent {
    private String worker;

    public String data;

    private String result;

    private Long startTime;
    private Long endTime;

    public OCRIntent() {
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public void makeService() {
    }
}
