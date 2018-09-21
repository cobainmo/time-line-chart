package menso.ba.timelinechartsampleapp.timelinechart;
/*  ################################
    Mensur Djogic, September 2018
    https://github.com/cobainmo
    ###############################
*/
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.List;

import menso.ba.timelinechartsampleapp.R;

public class TimelineChartView extends View {
    Paint paint = new Paint();
    //Canvas WIDTH and HEIGHT
    private int canvas_width;
    private int canvas_height;

    //List of timeline chart data model that will be drawn on timeline
    List<TimelineChartDataModel> times_list;

    private Canvas canvas;

    private float hours_of_day = 0;

    int start_hour = 10; //Starting hour on timeline (first number that will be displayed on horizontal grid)
    int end_hour = 16; //End hour

    /*
        Timeline GRID settings. Basic values like grid color, thickness of line, text label size, etc

     */
    int TIMELINE_GRID_COLOR = getResources().getColor(R.color.color_white);
    int TIMELINE_GRID_PADDING=10;
    int TIMELINE_GRID_THICKNESS = 5;
    int TIMELINE_GRID_HEIGHT = 80;
    int TIMELINE_TEXT_SIZE = 30;
    int TIMELINE_15MINUTE_THICKNESS =2;
    int TIMELINE_15MINUTE_HEIGHT =60;


    //Some default colors
    String COLOR_RED = "#e63c23";
    String COLOR_BLUE = "#007fe4";
    String COLOR_GREEN = "#88d533";
    String COLOR_GRAY= "#CCC";

    public TimelineChartView(Context context, List<TimelineChartDataModel> all_times) {
        super(context);
        this.times_list = all_times;

    }

    public void setHours(int start_hour, int end_hour){
        this.start_hour = start_hour;
        this.end_hour = end_hour;
    }

    /*
        Setting GRID values in case you don't want default ones
     */
    public void setTimelineGrid(int grid_color, int grid_padding, int grid_thickness, int grid_height, int grid_text_size, int quarterHour_thickness, int quarterHour_height){
        this.TIMELINE_GRID_COLOR = grid_color;
        this.TIMELINE_GRID_PADDING = grid_padding;
        this.TIMELINE_GRID_THICKNESS = grid_thickness;
        this.TIMELINE_GRID_HEIGHT = grid_height;
        this.TIMELINE_TEXT_SIZE = grid_text_size;
        this.TIMELINE_15MINUTE_THICKNESS = quarterHour_thickness;
        this.TIMELINE_15MINUTE_HEIGHT = quarterHour_height;
    }

    //Creating background timeline grid that displays time from {start_time} to {start_time}+{hours_of_day}
    private void drawBackgroundTimelineGrid(){

        //Setting timeline grid color
        paint.setColor(TIMELINE_GRID_COLOR);
        //Creating timeline grid bottom line
        this.canvas.drawRect(TIMELINE_GRID_PADDING, canvas_height,canvas_width-hours_of_day+TIMELINE_GRID_PADDING , canvas_height-TIMELINE_GRID_THICKNESS, paint );

        paint.setTextSize(TIMELINE_TEXT_SIZE);
        //Creating hourly grid and in between hour column adding 15 minute column. After that i draw text  with hours above everything
        int total_hour_columns = end_hour-start_hour;
        for(int i=0; i<total_hour_columns; i++){
            canvas.drawRect((hours_of_day*i+TIMELINE_GRID_THICKNESS)+TIMELINE_GRID_PADDING, canvas_height,hours_of_day*i+TIMELINE_GRID_PADDING , canvas_height-TIMELINE_GRID_HEIGHT, paint );
            //Do not draw 15 minute column for last hour
            if(i<total_hour_columns-1){
                for(int j=1; j<4; j++){
                    this.canvas.drawRect((hours_of_day*i+TIMELINE_GRID_THICKNESS)+TIMELINE_GRID_PADDING+((hours_of_day/4) *j)+TIMELINE_15MINUTE_THICKNESS, canvas_height,hours_of_day*i+TIMELINE_GRID_PADDING+((hours_of_day/4) *j) , canvas_height-TIMELINE_15MINUTE_HEIGHT, paint );
                }
            }
            //Drawing hours label on top of grid lines
            canvas.drawText((i+start_hour)+"", (hours_of_day*i+TIMELINE_GRID_THICKNESS), canvas_height-(TIMELINE_GRID_HEIGHT+TIMELINE_TEXT_SIZE), paint);

        }
    }

    @Override

    public void onDraw(Canvas canvas) {
        //Calculating how many hours for assigned time period
        hours_of_day = canvas_width/(end_hour-start_hour);
        this.canvas = canvas;
        //Drawing background grid before charts draw
        drawBackgroundTimelineGrid();

        for(int i=0; i<times_list.size(); i++){
            if(times_list.get(i).getTime_status().equals("WORKING"))
                paint.setColor(Color.parseColor(COLOR_GREEN));
            else if(times_list.get(i).getTime_status().equals("MEETING"))
                paint.setColor(Color.parseColor(COLOR_BLUE));
            else if(times_list.get(i).getTime_status().equals("BREAK"))
                paint.setColor(Color.parseColor(COLOR_RED));
            else
                paint.setColor(Color.parseColor(COLOR_GRAY));
            //Draw chart rectangle and color it to right status
            this.canvas.drawRect((times_list.get(i).getStart_time()-start_hour)*hours_of_day, canvas_height-20, (times_list.get(i).getEnd_time()-start_hour)*hours_of_day, canvas_height-60, paint );

        }

    }

    public void drawTimeline(){
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.canvas_width = w;
        this.canvas_height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

}