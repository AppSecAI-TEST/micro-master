package pl.edu.agh.jkolodziej.micro.agent.intents;

import org.nzdis.micro.Intent;
import pl.edu.agh.jkolodziej.micro.agent.enums.ConnectionType;
import pl.edu.agh.jkolodziej.micro.agent.enums.TaskDestination;
import pl.edu.agh.jkolodziej.micro.agent.enums.TaskType;

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

    protected String connectionType;
    protected String taskDestination;
    protected String taskType;
    protected int wifiPowerSignal;
    protected Long resolution;
    protected Long fileSize;

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

    public ConnectionType getConnectionType() {
        return ConnectionType.valueOf(connectionType);
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType.name();
    }

    public TaskDestination getTaskDestination() {
        return TaskDestination.valueOf(taskDestination);
    }

    public void setTaskDestination(TaskDestination taskDestination) {
        this.taskDestination = taskDestination.name();
    }

    public TaskType getTaskType() {
        return TaskType.valueOf(taskType);
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType.name();
    }

    public int getWifiPowerSignal() {
        return wifiPowerSignal;
    }

    public void setWifiPowerSignal(int wifiPowerSignal) {
        this.wifiPowerSignal = wifiPowerSignal;
    }

    public Long getResolution() {
        return resolution;
    }

    public void setResolution(Long resolution) {
        this.resolution = resolution;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * each <code>ServiceIntent</code>should make some action - realize some kind of service
     */
    public abstract void makeService();
}
