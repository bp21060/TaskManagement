package taskManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TaskManagement extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskManagement frame = new TaskManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel p = new JPanel();
		GridLayout layout = new GridLayout(1,2);
		layout.setHgap(150);
		
		//追加、削除ボタン
		JButton addButton = new JButton("追加");
		JButton deleteButton = new JButton("削除");
		
		p.setLayout(layout);
		p.add(addButton);
		p.add(deleteButton);

		
		
		Container contentPane = getContentPane();
		contentPane.add(p,BorderLayout.PAGE_END);
	}

}
