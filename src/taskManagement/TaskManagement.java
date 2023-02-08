package taskManagement;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskManagement extends JFrame implements ActionListener {

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

		//課題一覧パネルを追加
		TaskListPanel taskListPanel = new TaskListPanel();
		JScrollPane taskScroll = new JScrollPane(taskListPanel.MakePanel());
		taskScroll.getVerticalScrollBar().setUnitIncrement(20);

		//課題追加パネルを追加
		JPanel taskAddPanel = new JPanel();

		//画面遷移
		cardLayoutPanel = new JPanel();
		cardLayout = new CardLayout();
		cardLayoutPanel.setLayout(cardLayout);

		cardLayoutPanel.add(taskScroll, "taskList");
		cardLayoutPanel.add(taskAddPanel, "taskAdd");

		//追加、削除ボタンのパネルを追加
		JPanel p = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(100);
		Container contentPane = getContentPane();

		JButton addButton = new JButton("課題の追加");
		addButton.addActionListener(this);
		addButton.setActionCommand("taskAdd");

		JButton deleteButton = new JButton("完了した課題の削除");
		p.setLayout(layout);
		p.add(addButton);
		p.add(deleteButton);

		//削除追加ボタンパネルと課題一覧パネルをcontentPaneに追加
		contentPane.add(p, BorderLayout.PAGE_END);
		contentPane.add(cardLayoutPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		cardLayout.show(cardLayoutPanel, command);
	}

}
