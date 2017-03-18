package pl.edu.agh.jkolodziej.micro.agent.intents;

/**
 * @author - Jakub Kołodziej
 *         Simple Intent which add two numbers
 */
public class AddIntent extends ServiceIntent {

    public AddIntent() {
    }

    private String worker;

    private Double sub1;
    private Double sub2;

    private Double result;

    private Long startTime;
    private Long endTime;


    @Override
    public void makeService() {
        this.result = sub1 + sub2;
    }

    public AddIntent(Double sub1, Double sub2) {
        this.sub1 = sub1;
        this.sub2 = sub2;
    }

    public Double getResult() {
        return result;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Double getSub1() {
        return sub1;
    }

    public void setSub1(Double sub1) {
        this.sub1 = sub1;
    }

    public Double getSub2() {
        return sub2;
    }

    public void setSub2(Double sub2) {
        this.sub2 = sub2;
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
}
