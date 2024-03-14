package gameSnake;
import javax.swing.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Snake");
		
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		snakegame snake = new snakegame(600,600);
		frame.add(snake);
		frame.pack();
		snake.requestFocus();
	}

}
