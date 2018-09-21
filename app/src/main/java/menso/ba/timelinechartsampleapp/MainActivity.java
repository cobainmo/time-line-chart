package menso.ba.timelinechartsampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import menso.ba.timelinechartsampleapp.timelinechart.TimelineChartDataModel;
import menso.ba.timelinechartsampleapp.timelinechart.TimelineChartView;

public class MainActivity extends AppCompatActivity {

    LinearLayout holder, holder1, holder2, holder3, holder4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadHolders();

        TimelineChartView chartView = new TimelineChartView(this, populateView());
        holder.addView(chartView);

        chartView = new TimelineChartView(this, populateView());
        chartView.setHours(13,22);
        holder1.addView(chartView);

        chartView = new TimelineChartView(this, populateView());
        chartView.setHours(5,19);
        holder2.addView(chartView);

        chartView = new TimelineChartView(this, populateView());
        chartView.setHours(7,19);
        holder3.addView(chartView);

        chartView = new TimelineChartView(this, populateView());
        chartView.setHours(5,19);
        holder4.addView(chartView);



    }
    //Generating random data
    private List<TimelineChartDataModel> populateView(){
        Random r = new Random();
        List<TimelineChartDataModel> dataModels = new ArrayList<>();
        String[] working_status = new String[]{"WORKING","MEETING","BREAK"};
        for(int i=0; i<8; i++){
            float random = 7 + r.nextFloat() * (18 - 7);
            dataModels.add(new TimelineChartDataModel(random,random + r.nextFloat() * (18 - random),working_status[r.nextInt(2 - 0) + 0]));
        }

        return dataModels;
    }

    private void loadHolders(){
        holder = (LinearLayout) findViewById(R.id.chart_holder);
        holder1 = (LinearLayout) findViewById(R.id.chart_holder1);
        holder2 = (LinearLayout) findViewById(R.id.chart_holder2);
        holder3 = (LinearLayout) findViewById(R.id.chart_holder3);
        holder4 = (LinearLayout) findViewById(R.id.chart_holder4);
    }
}
