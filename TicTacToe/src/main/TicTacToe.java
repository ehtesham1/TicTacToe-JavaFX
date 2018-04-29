package main;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TicTacToe extends Application {
	// Indicate which player has a turn, initially it is the X player
	private char whoseTurn = 'X';

	// Create and initialize cell
	private Cell[][] cell = new Cell[3][3];

	// Create and initialize a status label
	private Label lblStatus = new Label("X's turn to play");
	private Label lblGame;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Pane to hold cell
		GridPane pane = new GridPane();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				pane.add(cell[i][j] = new Cell(), j, i);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		Image image = new Image(getClass().getResourceAsStream("header.jpg"));
		lblGame = new Label();
		lblGame.setGraphic(new ImageView(image));
		lblGame.setTextFill(Color.web("#0076a3"));

		borderPane.setTop(lblGame);
		
		BorderPane resultpane=new BorderPane();
		lblStatus.setFont(new Font("Arial",16));
		resultpane.setCenter(lblStatus);
		
		Button newgame =new Button("NewGame");
		newgame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				lblStatus.setText("X's turn to play");
				whoseTurn ='X';
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++){
						cell[i][j].setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
						cell[i][j].setToken(' ');
					}}
		});
		Button exit=new Button("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		resultpane.setRight(exit);
		resultpane.setLeft(newgame);
		
		borderPane.setBottom(resultpane);

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setTitle("TicTacToe"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage
	}

	/** Determine if the cell are all occupied */
	public boolean isFull() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (cell[i][j].getToken() == ' ')
					return false;

		return true;
	}

	/** Determine if the player with the specified token wins */
	public boolean isWon(char token) {
		for (int i = 0; i < 3; i++)
			if (cell[i][0].getToken() == token && cell[i][1].getToken() == token && cell[i][2].getToken() == token) {
				Image image;
				if (whoseTurn == 'X') {
					image = new Image(getClass().getResourceAsStream("x-vertical.jpg"));
				} else {
					image = new Image(getClass().getResourceAsStream("o-horizontal.jpg"));
				}
				cell[i][0].setBackground(setImage(image));
				cell[i][1].setBackground(setImage(image));
				cell[i][2].setBackground(setImage(image));
				return true;
			}

		for (int j = 0; j < 3; j++)
			if (cell[0][j].getToken() == token && cell[1][j].getToken() == token && cell[2][j].getToken() == token) {
				Image image;
				if (whoseTurn == 'X') {
					image = new Image(getClass().getResourceAsStream("x-horizontal.jpg"));
				} else {
					image = new Image(getClass().getResourceAsStream("o-vertical.jpg"));
				}
				cell[0][j].setBackground(setImage(image));
				cell[1][j].setBackground(setImage(image));
				cell[2][j].setBackground(setImage(image));
				return true;
			}

		if (cell[0][0].getToken() == token && cell[1][1].getToken() == token && cell[2][2].getToken() == token) {
			Image image;
			if (whoseTurn == 'X') {
				image = new Image(getClass().getResourceAsStream("x-diagnol-op.jpg"));
			} else {
				image = new Image(getClass().getResourceAsStream("o-diagnol-op.jpg"));
			}
			cell[0][0].setBackground(setImage(image));
			cell[1][1].setBackground(setImage(image));
			cell[2][2].setBackground(setImage(image));
			return true;
		}

		if (cell[0][2].getToken() == token && cell[1][1].getToken() == token && cell[2][0].getToken() == token) {
			Image image;
			if (whoseTurn == 'X') {
				image = new Image(getClass().getResourceAsStream("x-diagnol.jpg"));
			} else {
				image = new Image(getClass().getResourceAsStream("o-diagnol.jpg"));
			}
			cell[0][2].setBackground(setImage(image));
			cell[1][1].setBackground(setImage(image));
			cell[2][0].setBackground(setImage(image));

			return true;
		}

		return false;
	}

	private Background setImage(Image image) {
		return new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
	}

	// An inner class for a cell
	public class Cell extends Pane {
		// Token used for this cell
		private char token = ' ';

		public Cell() {
			setStyle("-fx-border-color: #41DAC6 ;  -fx-border-width: 6");
			setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
			this.setPrefSize(800, 800);
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		/** Return token */
		public char getToken() {
			return token;
		}

		/** Set a new token */
		public void setToken(char c) {
			token = c;

			if (token == 'X') {
				Image image = new Image(getClass().getResourceAsStream("x.jpg"));
				this.setBackground(setImage(image));
			} else if (token == 'O') {
				Image image = new Image(getClass().getResourceAsStream("o.jpg"));
				this.setBackground(setImage(image));
			}
		}

		/* Handle a mouse click event */
		private void handleMouseClick() {
			// If cell is empty and game is not over
			if (token == ' ' && whoseTurn != ' ') {
				setToken(whoseTurn); // Set token in the cell

				// Check game status
				if (isWon(whoseTurn)) {
					lblStatus.setText(whoseTurn + " won! The game is over");
					whoseTurn = ' '; // Game is over
				} else if (isFull()) {
					lblStatus.setText("Draw! The game is over");
					whoseTurn = ' '; // Game is over
				} else {
					// Change the turn
					whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
					// Display whose turn
					lblStatus.setText(whoseTurn + "'s turn");
				}
			}
		}

	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}