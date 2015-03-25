import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private Label x_score;

	@FXML
	private Label o_score;

	@FXML
	private Button btnRestart;

	@FXML
	private Button btnClearScore;

	@FXML
	private GridPane grid_pane;

	private static boolean isZero = false;
	protected static ObservableList<Node> btns;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btns = grid_pane.getChildren();
		Button btn;

		for (int i = 0; i < btns.size(); ++i) {
			btn = (Button) btns.get(i);
			btn.setId(String.valueOf(i));
			btn.setOnAction(this);
		}
		
		btnClearScore.setOnAction(this);
		btnRestart.setOnAction(this);
	}

	private void clearScore() {
		x_score.setText("0");
		o_score.setText("0");
		restart();
	}

	private void restart() {
		Button btn;
        for (Node node : btns) {
            btn = (Button) node;
            btn.setText("");
        }

		setButtonEnabled(true);
	}

	private void setButtonEnabled(boolean enable) {
		Button btn;

        for (Node node : btns) {
            btn = (Button) node;
            btn.disableProperty().set(!enable);
        }
	}

	@Override
	public void handle(ActionEvent event) {
		Button btn = (Button) event.getSource();

		if (btn == btnClearScore) {
			clearScore();
		} else if (btn == btnRestart) {
			restart();
		} else {
			if (!btn.getText().equals("X") && !btn.getText().equals("O")) {
				if (isZero) {
					btn.setText("O");
				} else {
					btn.setText("X");
				}

				if (GameLogic.check()) {
					int[] btnsId = GameLogic.getBtnsId();
					
					for(int id : btnsId){
						System.out.println(id);
					}
					
					setButtonEnabled(false);

					if (isZero) {
						int o_score_int = Integer.parseInt(o_score.getText());
						++o_score_int;
						o_score.setText(String.valueOf(o_score_int));
					} else {
						int x_score_int = Integer.parseInt(x_score.getText());
						++x_score_int;
						x_score.setText(String.valueOf(x_score_int));
					}

				}

				isZero = !isZero;
			}
		}

	}
}
