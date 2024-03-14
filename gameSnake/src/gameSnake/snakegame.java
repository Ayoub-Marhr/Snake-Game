package gameSnake;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class snakegame extends JPanel implements ActionListener,KeyListener{
	private class Tile{
		int x;
		int y;
	
	Tile(int x,int y){
		
		this.x=x;
		this.y=y;
	}
		
	}
	int borderWidth;
	int bordeHeight;
	int tilesize =20;
	//snake
	Tile snakeHead;
	ArrayList<Tile> snakeBody;
	//tile
	Tile food;
	//random
	Random random;
	//game logic
	Timer gameLoop;
	int velocityX;
	int velocityY;
	boolean gameover=false;

	snakegame(int width, int height){
		this.bordeHeight=height;
		this.borderWidth=width;
		
		setPreferredSize(new Dimension(this.borderWidth,this.bordeHeight));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		snakeHead = new Tile(5,5);
		food= new Tile(10,10);
		random = new Random();
		placeFood();
		
		velocityX=0;
		velocityY=0;
		gameLoop = new Timer(100,this);
		gameLoop.start();
		snakeBody =new ArrayList<Tile>();
		}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		//food
		g.setColor(Color.red);
		g.fill3DRect(food.x*tilesize, food.y*tilesize, tilesize, tilesize,true);
		//snake Head
		g.setColor(Color.green);
		g.fill3DRect(snakeHead.x*tilesize,snakeHead.y*tilesize,tilesize,tilesize,true);
		//snake body
		for (int i =0;i<snakeBody.size();i++) {
			Tile snakePart= snakeBody.get(i);
			g.fill3DRect(snakePart.x*tilesize, snakePart.y*tilesize, tilesize, tilesize,true);
		}
		g.setFont(new Font("Arial",Font.PLAIN,16));
		if(gameover) {
			g.setColor(Color.red);
			g.drawString("Game Over "+String.valueOf(snakeBody.size()),tilesize -16 ,tilesize);
		}
		else 
			g.drawString("Score : "+String.valueOf(snakeBody.size()), tilesize -16 ,tilesize);
	}
	public void  placeFood() {
		food.x=random.nextInt(borderWidth/tilesize);
		food.x=random.nextInt(bordeHeight/tilesize);

	}
	public boolean collision(Tile t1,Tile t2) {
		return t1.x==t2.x && t1.y==t2.y;
	}
	public void move() {
		//eating the food
		
		if(collision(snakeHead,food)) {
			snakeBody.add(new Tile(food.x,food.y));
			placeFood();
		}
		//snake Head
		
		//snake Body
		for(int i=snakeBody.size()-1;i>=0;i--) {
			Tile snakePart=snakeBody.get(i);
			if(i==0) {
				snakePart.x=snakeHead.x;
				snakePart.y=snakeHead.y;
			}
			else {
				Tile prevSnakePart = snakeBody.get(i-1);
				
				snakePart.x=prevSnakePart.x;
				snakePart.y=prevSnakePart.y;
				
		}
		}
		snakeHead.x+=velocityX;
		snakeHead.y+=velocityY;
		//game over conditions
		for(int i=0;i<snakeBody.size();i++) {
			Tile snakePart =snakeBody.get(i);
			if(collision(snakeHead,snakePart)) {
				gameover = true;
			}
		}
		if(snakeHead.x*tilesize<0 || snakeHead.x*tilesize> 600 || snakeHead.y*tilesize<0 || snakeHead.y > 600) {
			gameover = true;
			System.out.println("1");
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		move();
		repaint();
		if (gameover) {
			gameLoop.stop();
		}
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1) {
			velocityX=0;
			velocityY=-1;
		}
		else if (e.getKeyCode()== KeyEvent.VK_DOWN && velocityY!=-1) {
			velocityX=0;
			velocityY+=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1) {
			velocityX-=1;
			velocityY=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1) {
			velocityX+=1;
			velocityY=0;
		}
	}
	//we do not need this methods
	public void keyTyped(KeyEvent e) {		
	}

	

	public void keyReleased(KeyEvent e) {		
	}
	
}
