import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class driver extends Application {
	static ArrayList<String> list = new ArrayList<String>();
	static String s = "";
	static int table[][];
	static String out = "";
	static WordBreak WB = new WordBreak();

	public static void main(String[] args) {
		launch(args);

	}

	// method to scan from file
	public static void fileInput(ArrayList<String> dict) throws IOException {
		File file = new File("dict.txt");
		// define a buffer reader to read text
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				dict.add(line);
				System.out.println(dict.get(i));

				i++;

			}
		}

		finally {
			br.close();
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane main = new GridPane();

		TextArea ta = new TextArea();

		GridPane right = new GridPane();

		TextField tfinsert = new TextField();
		Button insert = new Button("INSERT");
		insert.setPrefSize(100, 30);
		right.add(tfinsert, 0, 0);
		right.add(insert, 1, 0);

		insert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String insertStr = tfinsert.getText();
				s = insertStr;

			}

		});

		Button b1 = new Button("File Input");
		b1.setPrefSize(100, 30);
		right.add(b1, 1, 1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					fileInput(list);
				} catch (IOException e) {
					System.out.print("File Doesnt Exist");

				}

			}

		});

		Button b3 = new Button("Print Table");
		b3.setPrefSize(100, 30);
		right.add(b3, 1, 2);
		b3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				table = WordBreak.table(s, list);
				StringBuffer tableString = new StringBuffer();
				for (int[] row : table) {
					tableString.append(Arrays.toString(row) + "\n");

				}

				ta.setText(tableString.toString());

			}

		});

		main.add(ta, 0, 0);

		Button b2 = new Button("Print Answers");
		b2.setPrefSize(100, 30);
		right.add(b2, 1, 3);

		b2.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				StringBuffer buffer = new StringBuffer();
				WordBreak.print(list, s, table, out, 0, s.length() - 1, buffer);
				if (buffer.length() == 0)
					buffer.append(" This String Cant Be Segemented using this dictonary");
				// button 4 for existing the printing window
				Button b4 = new Button("exit");
				VBox vert = new VBox();

				vert.getChildren().add(new Label(buffer.toString()));

				// code for spacing and aligning the vertical box
				vert.setPadding(new Insets(10, 10, 10, 10));
				HBox horz = new HBox(b4);
				// code for alinging the hozrizntal box for the exit button
				horz.setPadding(new Insets(10, 10, 10, 10));
				vert.getChildren().add(horz);
				// centering the exit
				horz.setAlignment(Pos.CENTER);
				Scene scene = new Scene(vert);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				b4.setOnAction(e1 -> {
					stage.hide();
				});
			}
		});

		main.add(right, 1, 0);
		primaryStage.setTitle("Project");
		Scene scene = new Scene(main, 650, 280);
		main.prefHeightProperty().bind(scene.heightProperty());
		main.prefWidthProperty().bind(scene.widthProperty());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}


