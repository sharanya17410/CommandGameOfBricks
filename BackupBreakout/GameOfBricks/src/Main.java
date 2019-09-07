import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
//import Breakout.UserAction;
//import Breakout.UserAction;

public class Main extends Application{
	
	private enum UserAction {
		NONE, LEFT, RIGHT;
	}

	 public int APP_W = 800;
	public  int APP_H = 600;
	
	Ball ball;
//	Paddle bat;
	
//	
	
	private static final int BAT_W = 180;
	private static final int BAT_H = 20;
	
	
	
	
	private static final int BRICK_W = 50;
	private static final int BRICK_H = 10;
	
	
	
/*Creating ball with radius*/
	private static final int BALL_RADIUS = 10;
//	private Circle ball = new Circle(BALL_RADIUS);

	private boolean ballUp = true, ballLeft = false;
	private Timeline timeline = new Timeline();
	
//	private Rectangle bat = new Rectangle(BAT_W, BAT_H);
//	private Rectangle brick = new Rectangle(BRICK_W, BRICK_H);
	
	/*Creating a timeline for clock*/
	private Timeline timeline2 = new Timeline();

	private boolean running = true;
	private Circle circle = new Circle();
	private Rectangle rect_paddle = new Rectangle(BAT_W, BAT_H);
	Coordinates brickCoordinates = new Coordinates(APP_W - 300, APP_H - 100);
	Brick brick = new Brick(brickCoordinates, BRICK_W, BRICK_H, Color.DARKRED);

	Coordinates batCoordinates = new Coordinates(APP_W / 2, APP_H - 250);
	Paddle bat = new Paddle(BAT_W, BAT_H, Color.BLACK, batCoordinates,rect_paddle);
	private UserAction action = UserAction.NONE;
	
//	Coordinates coordinates = new Coordinates(APP_W/2,APP_H/2);
	//coordinates.setLocationX(1.22);
	//coordinates.setLocationY(0);
	//Ball ball= new Ball(coordinates, 5,BALL_RADIUS,Color.ALICEBLUE);
	
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(APP_W, APP_H);
//		Coordinates batCoordinates = new Coordinates(APP_W / 2, APP_H - 250);
//		bat = new Paddle(BAT_W, BAT_H, Color.ALICEBLUE, batCoordinates);
//		
//		Coordinates brickCoordinates = new Coordinates(APP_W - 300, APP_H - 100);
//		brick = new Brick(brickCoordinates, BRICK_W, BRICK_H, Color.DARKRED);

		
	
		
		root.getChildren().addAll(brick.rectangle, ball.shape,bat.paddle);
		
		
		/*Ball & Game*/
		KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
			if (!running)
				return;

			switch (action) {
			case LEFT:
				if (bat.coordinates.getLocationX() - 5 >= 0) {
					bat.updateX("subtract");
					break;
				}
			case RIGHT:
				if (bat.coordinates.getLocationX() + BAT_W + 5 <= APP_W) {
					bat.updateX("add");
					break;
				}
			case NONE:
				break;
			}
			if(ballLeft) {
				ball.updateX("subtract");
			} else {
				ball.updateX("add");
			}
			
			if(ballUp) {
				ball.updateY("subtract");
			} else {
				ball.updateY("add");
			}
			

			if (ball.coordinates.getLocationX() - BALL_RADIUS == 0)
				ballLeft = false;
			else if (ball.coordinates.getLocationX() + BALL_RADIUS == APP_W)
				ballLeft = true;

			if (ball.coordinates.getLocationY() - BALL_RADIUS == 0)
				ballUp = false;
			
/*Ball*/
		/*Hits the brick*/
			if (ball.coordinates.getLocationY() + BALL_RADIUS == APP_H - 100
					&& ball.coordinates.getLocationX() + BALL_RADIUS <= brick.coordinates.getLocationX() + BRICK_W
					&& ball.coordinates.getLocationX() - BALL_RADIUS >= brick.coordinates.getLocationX()) {
			
				continueGame();
			
			}

			if (ball.coordinates.getLocationY() - BALL_RADIUS == APP_H - 90
					&& ball.coordinates.getLocationX() + BALL_RADIUS <= brick.coordinates.getLocationX() + BRICK_W
					&& ball.coordinates.getLocationX() - BALL_RADIUS >= brick.coordinates.getLocationX()) {
			
				continueGame();
			
			}
		/*Hits the brick*/
		/*Doesnt Hit the brick*/
			if (ball.coordinates.getLocationY() + BALL_RADIUS == APP_H - 250
					&& ball.coordinates.getLocationX() + BALL_RADIUS <= bat.coordinates.getLocationX() + BAT_W
					&& ball.coordinates.getLocationX() - BALL_RADIUS >= bat.coordinates.getLocationX()) {
				ballUp = true;
			}

			if (ball.coordinates.getLocationY() + BALL_RADIUS == APP_H)
		
				ballUp = true;

			if (ball.coordinates.getLocationY() - BALL_RADIUS == APP_H - 240
					&& ball.coordinates.getLocationX() + BALL_RADIUS <= bat.coordinates.getLocationX() + BAT_W
					&& ball.coordinates.getLocationX() - BALL_RADIUS >= bat.coordinates.getLocationX())
				// restartGame();
				ballUp = false;
		/*Doesnt Hit the brick*/
		});
		timeline.getKeyFrames().add(frame);
		//timeline2.getKeyFrames().add(frame2);

		timeline.setCycleCount(Timeline.INDEFINITE);
		//timeline2.setCycleCount(Timeline.INDEFINITE);
		return root;
	}
	
	
	private void continueGame() {
		// TODO Auto-generated method stub
		
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		VBox vbox = new VBox(new Text("Restart game?"));
	    vbox.setAlignment(Pos.CENTER);
	    Button yesButton = new Button();
	    vbox.getChildren().addAll(yesButton);
		vbox.setAlignment(Pos.CENTER);
		//vbox.setPadding(new Insets(50));
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
		yesButton.setText("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				startGame();
				dialogStage.close();
			}
		});
		running = false;
		timeline.stop();
	//	timeline2.stop(); 
	}
	
	private void startGame() {
		// TODO Auto-generated method stub
		ballUp = true;
//		ball.setTranslateX(APP_W / 2);
//		ball.setTranslateY(APP_H / 2);

		
		
		timeline.play();
		timeline2.play();
		running = true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Coordinates ballCoordinates = new Coordinates(APP_W / 2, APP_H / 2);
		ball = new Ball(ballCoordinates,BALL_RADIUS,Color.BLACK,circle);
		Scene scene = new Scene(createContent());
		scene.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case A:
				action = UserAction.LEFT;
				break;
			case D:
				action = UserAction.RIGHT;
				break;
			default:
				break;
			}
		});

		primaryStage.setTitle("GAME OF BRICKS");
		primaryStage.setScene(scene);
		primaryStage.show();
		startGame();
	}

	public static void main(String[] args) {
		launch(args);
	}


	
}