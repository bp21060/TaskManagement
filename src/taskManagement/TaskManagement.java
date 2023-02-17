package taskManagement;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TaskManagement extends JFrame {

	JPanel cardLayoutPanel;
	CardLayout cardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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

		//メイン画面遷移
		cardLayoutPanel = new JPanel();
		cardLayout = new CardLayout();
		cardLayoutPanel.setLayout(cardLayout);

		//課題一覧パネルを追加
		TaskListPanel taskListPanel = new TaskListPanel(cardLayoutPanel, cardLayout);

		//課題追加パネルを追加
		JPanel taskAddPanel = new TaskAddPanel(cardLayoutPanel, cardLayout);

		cardLayoutPanel.add(taskListPanel, "taskList");
		cardLayoutPanel.add(taskAddPanel, "taskAdd");

		//削除追加ボタンパネルと課題一覧パネルをcontentPaneに追加
		Container contentPane = getContentPane();
		contentPane.add(cardLayoutPanel);
	}

}
