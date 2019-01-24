package Interface;

import Model.Counter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
private Counter cnt;

    @FXML
    private Label timerLbl;

    @FXML
    void startClick() {
        cnt = new Counter(timerLbl);
    }

    @FXML
    void stopClick() {
        cnt.stopCount();
        cnt.getResult();
    }
}

