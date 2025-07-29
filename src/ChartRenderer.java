import javafx.scene.Node;
import java.io.File;

public interface ChartRenderer {
    Node render(File csvFile,String chartType);
    
}
