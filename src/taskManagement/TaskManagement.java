package taskManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskManagement extends JFrame {

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

		JPanel p = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(100);
		Container contentPane = getContentPane();

		//追加、削除ボタンのパネルを追加
		JButton addButton = new JButton("課題の追加");
		JButton deleteButton = new JButton("完了した課題の削除");

		p.setLayout(layout);
		p.add(addButton);
		p.add(deleteButton);

		//課題一覧パネルを追加
		TaskListPanel taskListPanel = new TaskListPanel();
		JScrollPane taskScroll = new JScrollPane(taskListPanel.MakePanel());
		taskScroll.getVerticalScrollBar().setUnitIncrement(20);

		//削除追加ボタンパネルと課題一覧パネルをcontentPaneに追加
		contentPane.add(p, BorderLayout.PAGE_END);
		contentPane.add(taskScroll, BorderLayout.CENTER);
	}

}
