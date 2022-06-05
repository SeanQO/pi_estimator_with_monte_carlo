package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.MonteCarlo;

public class PiGui {
    @FXML
    private Label points;
    @FXML
    private Label error;
    @FXML
    private Label calcPi;
    @FXML
    private Label time;
    @FXML
    private AnchorPane mainPane;

    @FXML
    public void funct(ActionEvent event) throws IOException {
        long timeTaked = 0;
        for (int i = 1000000; timeTaked < 5000; i = i + 100000) {
            long time1 = System.currentTimeMillis();
            MonteCarlo mc = new MonteCarlo(i);
            // calculate PI for a certain epsilon
            mc.calcEpsilon();
            long time2 = System.currentTimeMillis();
            timeTaked = time2 - time1;
            time.setText(timeTaked + "milis");
            int pointsT = mc.getIn()+mc.getOut();
            points.setText("" + pointsT);
            error.setText(""+mc.getError());
            calcPi.setText(""+ mc.calcPi());
        }
    }
}
