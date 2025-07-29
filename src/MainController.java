import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;



public class MainController {
    @FXML
    private ComboBox<String> dataTypeCombo;

    @FXML
    private ComboBox<String> chartTypeCombo;


    @FXML
    private StackPane chartArea;

    @FXML
    private Label uploadStatusLabel;

    @FXML

    private File uploadedFile;

    private final  ChartRenderer oneDRenderer=new OneDChartRenderer();

    @FXML
    public void initialize()
    {
        dataTypeCombo.getItems().addAll("1D Data","2D Data","GeoSpatial Data");

        dataTypeCombo.setOnAction(e->{
            chartTypeCombo.getItems().clear();
            switch (dataTypeCombo.getValue())
            {
                case "1D Data":
                    chartTypeCombo.getItems().addAll("Pie Chart","Histogram");
                    break;
                case "2D Data":
                    chartTypeCombo.getItems().addAll("Bar Chart","Line Chart","Scatter Plot");
                    break;
                case "GeoSpatial Data":
                    chartTypeCombo.getItems().add("Map");
                    break;
            }
        });

    }
    @FXML
    private void handleUploadButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            uploadedFile = file;
            uploadStatusLabel.setText("CSV uploaded successfully!");
            uploadStatusLabel.setText("CSV uploaded successfully: " + file.getName());
        }
    }
@FXML
        private void handleVisualizeButton()
        {
            if(uploadedFile==null)
            {
                showAlert("No file uploaded");
                return;
            }

            String dataType=dataTypeCombo.getValue();
            String chartType=chartTypeCombo.getValue();

            if(dataType ==null || chartType == null)
            {
                showAlert("Please selct both data type and chart type");
                return;
            }

            Node chartNode=null;
            switch (dataType)
            {
                case "1D Data":
                    chartNode=oneDRenderer.render(uploadedFile,chartType);
                    break;

                case "2D Data":
                    //chartNode=oneDRenderer.render(uploadedFile,chartType);
                    break;

                case "GeoSpatial Data":
                   // chartNode=oneDRenderer.render(uploadedFile,chartType);
                    break;
            }
            chartArea.getChildren().clear();
            if (chartNode!=null)
            {
                chartArea.getChildren().add(chartNode);
            }
            else {
                showAlert("Unsupported chart or failed to render");
            }
        }
private void showAlert(String msg)
{
    Alert alert=new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setContentText(msg);
    alert.showAndWait();
}


}

