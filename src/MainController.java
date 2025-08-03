import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

public class MainController {
    @FXML
    private ComboBox<String> dataTypeCombo;

    @FXML
    private ComboBox<String> chartTypeCombo;


    @FXML
    private StackPane chartArea;

    @FXML
    private Button exportButton;

    @FXML
    private Label uploadStatusLabel;

    @FXML

    private File uploadedFile;

    private final  ChartRenderer oneDRenderer=new OneDChartRenderer();
    private final ChartRenderer twoDRender=new TwoDChartRenderer();
    @FXML
    public void initialize()
    {
        exportButton.setVisible(false);
        dataTypeCombo.getItems().addAll("1D Data","2D Data");

        dataTypeCombo.setOnAction(e->{
            chartTypeCombo.getItems().clear();
            switch (dataTypeCombo.getValue())
            {
                case "1D Data":
                    chartTypeCombo.getItems().addAll("Pie Chart","Bar Graph");
                    break;
                case "2D Data":
                    chartTypeCombo.getItems().addAll("Line Chart","Scatter Plot" ,"Bubble Chart");
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
    private void handleExportButton()
    {
        if(chartArea.getChildren().isEmpty())
        {
            showAlert("No chart to export.");
            return;
        }

        Node chartNode= chartArea.getChildren().get(0);
        WritableImage image= chartNode.snapshot(null,null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart As Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image","*.png"));
        File file= fileChooser.showSaveDialog(null);

        if(file != null)
        {
            try{
                ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file);
                showInfoAlert("Chart exported to : " + file.getAbsolutePath());
            }catch (Exception e)
            {
                e.printStackTrace();
                showAlert("Failed to export chart. ");
            }
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
                    chartNode= twoDRender.render(uploadedFile,chartType);
                    break;

            }
            chartArea.getChildren().clear();
            if (chartNode!=null)
            {
                chartArea.getChildren().add(chartNode);
                exportButton.setVisible(true);
            }
            else {
                exportButton.setVisible(false);
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

private void showInfoAlert(String msg)
{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("Export Successful");
    alert.setContentText(msg);
    alert.showAndWait();
}

}

