package pl.edu.agh.jkolodziej.micro.agent.intents;

import org.nzdis.micro.Intent;

/**
 * @author - Jakub Kołodziej
 *         Abstract Intent which will be exchanged between micro agents
 */
public abstract class ServiceIntent implements Intent {
    public String data;
    protected String worker;
    protected Long startTime;
    protected Long endTime;
    protected long startBattery;
    protected long endBattery;
    protected String result;

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

    public long getStartBattery() {
        return startBattery;
    }

    public void setStartBattery(long startBattery) {
        this.startBattery = startBattery;
    }

    public long getEndBattery() {
        return endBattery;
    }

    public void setEndBattery(long endBattery) {
        this.endBattery = endBattery;
    }

    /**
     * each <code>ServiceIntent</code>should make some action - realize some kind of service
     */
    public abstract void makeService();
}
