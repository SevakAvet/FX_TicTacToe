import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class GameLogic extends Controller {
	private static ObservableList<Node> btns;
	private static int[] btnsId;

	static {
		btns = Controller.btns;
		btnsId = new int[] { -1, -1, -1 };
	}

	public static boolean check() {
		for (int i = 0; i <= 6; i += 3) {
			if (checkRows(i)) {
				return true;
			}
		}

		for (int i = 0; i < 3; ++i) {
			if (checkColumns(i)) {
				return true;
			}
		}

		return checkDiagonals();
	}

	private static boolean checkColumns(int start) {
		Button one = (Button) btns.get(start);
		Button two = (Button) btns.get(start + 3);
		Button three = (Button) btns.get(start + 6);

		if (checkEquals(one, two, three)) {
			setBtnsId(start, start + 3, start + 6);
		}

		return checkEquals(one, two, three);
	}

	private static boolean checkRows(int start) {
		Button one = (Button) btns.get(start);
		Button two = (Button) btns.get(start + 1);
		Button three = (Button) btns.get(start + 2);

		if (checkEquals(one, two, three)) {
			setBtnsId(start, start + 1, start + 2);
		}

		return checkEquals(one, two, three);
	}

	private static boolean checkDiagonals() {
		Button one = (Button) btns.get(0);
		Button two = (Button) btns.get(4);
		Button three = (Button) btns.get(8);

		if (checkEquals(one, two, three)) {
			setBtnsId(0, 4, 8);
			return true;
		}
		
		one = (Button) btns.get(2);
		two = (Button) btns.get(4);
		three = (Button) btns.get(6);
		
		if (checkEquals(one, two, three)) {
			setBtnsId(2, 4, 6);
			return true;
		}
		
		return false;
	}

	private static boolean checkEquals(Button one, Button two, Button three) {
		String tOne = one.getText();
		String tTwo = two.getText();
		String tThree = three.getText();

		return tOne.equals(tTwo) && tTwo.equals(tThree) && 
			   !tOne.equals("") && !tTwo.equals("") && !tThree.equals("");
	}

	private static void setBtnsId(int one, int two, int three) {
		btnsId[0] = one;
		btnsId[1] = two;
		btnsId[2] = three;
	}

	public static int[] getBtnsId() {
		return btnsId;
	}
}
