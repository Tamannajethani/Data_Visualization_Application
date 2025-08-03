import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OneDChartRenderer implements ChartRenderer {
    @Override
    public Node render(File csvFile ,String chartType)
    {
        if(chartType.equalsIgnoreCase("Pie Chart"))
        {
            return renderPieChart(csvFile);
        }
        else if(chartType.equalsIgnoreCase("Bar Graph"))
        {
            return renderBarChart(csvFile);

        }
        return new StackPane();
    }
    private PieChart renderPieChart(File file)
    {
        List<PieChart.Data> dataList=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            String line;
            boolean isFirstLine=true;

            while ((line= br.readLine()) != null)
            {
                if(isFirstLine)
                {
                    isFirstLine=false;
                    continue;
                }
                String[] parts=line.split(",");

                if(parts.length>=2)
                {
                    String label=parts[0].trim();
                    double value=Double.parseDouble(parts[1].trim());
                    dataList.add(new PieChart.Data(label,value));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        PieChart pieChart=new PieChart();
        pieChart.setData(javafx.collections.FXCollections.observableArrayList(dataList));
        pieChart.setTitle("Pie Chart");

        return pieChart;
    }

    private BarChart<String ,Number> renderBarChart(File file)
    {
        List<String> labels=new ArrayList<>();
        List<Double> values=new ArrayList<>();

        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            String line;
            boolean isFirstLine=true;

            while ((line= br.readLine()) != null)
            {
                if(isFirstLine)
                {
                    isFirstLine=false;
                    continue;
                }
                String[] parts=line.split(",");

                if(parts.length>=2)
                {
                    labels.add(parts[0].trim());
                    values.add(Double.parseDouble(parts[1].trim()));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        CategoryAxis xAxis=new CategoryAxis();
        NumberAxis yAxis=new NumberAxis();
        xAxis.setLabel("Category");
        yAxis .setLabel("Frequency");

        BarChart<String,Number> bargraph=new BarChart<>(xAxis,yAxis);
        bargraph.setTitle("Bar Graph");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i=0;i< labels.size();i++)
        {
            series.getData().add(new XYChart.Data<>(labels.get(i),values.get(i) ));
        }
        bargraph.getData().add(series);
        return bargraph;
    }
}
