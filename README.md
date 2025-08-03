# Data_Visualization_Application

This JavaFX-based desktop application allows users to visualize data from CSV files in a variety of chart types. Designed to be user-friendly and interactive, the tool supports exporting charts as PNG images and works well for both 1D and 2D datasets.

## Features

- Upload any CSV file.
- Visualize data as:
  - Pie Chart
  - Bar Graph
  - Line Chart
  - Scatter Plot
  - Bubble Chart
    
- Export any generated chart as a PNG image.
- Intelligent UI: export button appears only after a chart is rendered.
- Clean modular structure with interface-based renderers

## Getting Started

### Requirements

- Java 17 or above
- JavaFX SDK (e.g., JavaFX 17)
- An IDE like IntelliJ IDEA or Eclipse

### Running the Project

1. Clone or download this repository.
2. Make sure your IDE is configured with the JavaFX SDK:
   - Add VM options (for JavaFX runtime):
     ```
     --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
     ```
3. Run the `Main` class to launch the app.

## Export Feature 

- After a chart is displayed, click "Export Chart" to save the image as .png. A confirmation message will appear with the save location.

## Author
Tamanna Jethani
