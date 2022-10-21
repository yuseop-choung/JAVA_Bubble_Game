package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

//1. 윈도우 창이 되었음
//2. 윈도우 창은 내부에 패널을 하나 갖고 있음
public class BubbleFrame extends JFrame{
	private JTextField textField;

	public BubbleFrame() {
		setSize(1000, 640);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(76, 88, 95, 23);
		getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setText("1111");
		textField.setBounds(76, 157, 106, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		setVisible(true);	//그림을 그려라	-> 작성하지 않으면 그림이 안그려짐
	}
	
	//main이 종료되지 않음 -> while 무한루프
	public static void main(String[] args) {
		new BubbleFrame();
	}
}
