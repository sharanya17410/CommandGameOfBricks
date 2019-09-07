import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Paddle implements Sprite{
	
	Coordinates coordinates;
	private double length;
	private double breadth;
	private Color color;
	public Shape paddle;
	Paddle(double length,double breadth,Color color,Coordinates coordinates,Shape shape){
		this.coordinates=coordinates;
		this.length=length;
		this.breadth=breadth;
		this.color=color;
		this.paddle=new Rectangle(length,breadth);
		System.out.println(coordinates.getLocationX()+" "+coordinates.getLocationX());
		paddle.setTranslateX(coordinates.getLocationX());
		paddle.setTranslateY(coordinates.getLocationY());
		this.paddle.setFill(color);
	}
	
	@Override
	public void updateX(String command) {
		if(command.equals("add")) {
			coordinates.setLocationX(coordinates.getLocationX()+5);
			
			
		}
		else {
			coordinates.setLocationX(coordinates.getLocationX()-5);
			
		}
		drawX(coordinates.getLocationX());
		// TODO Auto-generated method stub
		
//		this.locationX+=5;
//		this.locationY+=5;
//		this.draw(locationX, locationY);
	}

	@Override
	public void drawX(double locX) {
		// TODO Auto-generated method stub
		
		paddle.setTranslateX(locX);
		//paddle.setTranslateY(locY);
	}

	@Override
	public void updateY(String command) {
		// TODO Auto-generated method stub
		coordinates.setLocationX(0);
		drawY(coordinates.getLocationY());
	}

	@Override
	public void drawY(double locY) {
		// TODO Auto-generated method stub
		paddle.setTranslateY(locY);
	}
}