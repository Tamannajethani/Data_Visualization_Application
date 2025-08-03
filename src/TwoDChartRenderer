import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TwoDChartRenderer implements ChartRenderer{

    @Override
    public Node render(File csvFile,String chartType) {
        try{
            List<String[]> rows =new ArrayList<>();
            BufferedReader br=new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line= br.readLine())!=null)
            {
                rows.add(line.split(","));
            }
            br.close();
            if(rows.size() < 2 || rows.get(0).length<2)
            {
                System.out.println("Not enough data for 2D chart.");
                return new Pane();
            }
            String xLabel=rows.get(0)[0];
            String yLabel=rows.get(0)[0];
            NumberAxis xAxis=new NumberAxis();
            xAxis.setLabel(xLabel);

            NumberAxis yAxis=new NumberAxis();
            yAxis.setLabel(yLabel);


            if(chartType.equalsIgnoreCase("Scatter Plot"))
            {
                XYChart.Series<Number,Number> series =new XYChart.Series<>();
                series.setName("Data");

                for(int i=1;i<rows.size() ;i++)
                {
                    String[] row=rows.get(i);
                    try{
                        double x=Double.parseDouble(row[0]);
                        double y=Double.parseDouble(row[1]);
                        series.getData().add(new XYChart.Data<>(x,y));
                    }catch (NumberFormatException e){
                        // skip invalid rows
                    }
                }
                ScatterChart<Number,Number> scatterChart=new ScatterChart<>(xAxis,yAxis);
                scatterChart.setTitle("Scatter Plot");
                scatterChart.getData().add(series);
                return scatterChart;
            }
            else if(chartType.equalsIgnoreCase("Line Chart"))
            {
                XYChart.Series<Number,Number> series =new XYChart.Series<>();
                series.setName("Data");

                for(int i=1;i<rows.size() ;i++)
                {
                    String[] row=rows.get(i);
                    try{
                        double x=Double.parseDouble(row[0]);
                        double y=Double.parseDouble(row[1]);
                        series.getData().add(new XYChart.Data<>(x,y));
                    }catch (NumberFormatException e){
                        // skip invalid rows
                    }
                }
                LineChart<Number,Number> lineChart=new LineChart<>(xAxis,yAxis);
                lineChart.setTitle("Line Chart");
                lineChart.getData().add(series);
                return lineChart;
            }
            else if (chartType.equalsIgnoreCase("Bubble Chart")) {
               if(rows.get(0).length<3)
               {
                   System.out.println("Bubble chart requires three column : X,Y,Size.");
                return new Pane();
               }

               ScatterChart<Number,Number> bubbleChart =new ScatterChart<>(xAxis,yAxis);
               bubbleChart.setTitle("Bubble Chart");
                XYChart.Series<Number,Number> bubbleSeries =new XYChart.Series<>();
                bubbleSeries.setName("Bubbles");

                for(int i=1;i<rows.size() ;i++)
                {
                    String[] row=rows.get(i);
                    try{
                        double x=Double.parseDouble(row[0]);
                        double y=Double.parseDouble(row[1]);
                        double size=Double.parseDouble(row[2]);

                        XYChart.Data<Number,Number> dataPoint =new XYChart.Data<>(x,y);
                        Circle circle=new Circle(size);
                        circle.setFill((Color.DODGERBLUE));
                        dataPoint.setNode(circle);
                        bubbleSeries.getData().add(dataPoint);
                    }catch (NumberFormatException e){
                        // skip invalid rows
                    }
                }
                bubbleChart.getData().add(bubbleSeries);
                return bubbleChart;
            }
            else {
                System.out.println("Unsupported 2D chart type: " + chartType);
                return new Pane();
        }
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Pane();
        }

    }
}
