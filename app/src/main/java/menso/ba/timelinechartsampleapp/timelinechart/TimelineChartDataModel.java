package menso.ba.timelinechartsampleapp.timelinechart;
/*  ################################
    Mensur Djogic, September 2018
    https://github.com/cobainmo
    ###############################
*/
public class TimelineChartDataModel {
    private float start_time;
    private float end_time;
    private String time_status; // I used string since it was convenient for me, you can change it to int and declare statuses with integer

    public TimelineChartDataModel (float start_time, float end_time, String time_status){
        this.start_time = start_time;
        this.end_time = end_time;
        this.time_status = time_status;
    }

    public float getStart_time() {
        return start_time;
    }

    public float getEnd_time() {
        return end_time;
    }

    public String getTime_status() {
        return this.time_status;
    }
}