package taskManagement;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TaskManagement extends JFrame {

	//カードレイアウト
	public static JPanel cardLayoutPanel;
	public static CardLayout cardLayout;
	//タスクを格納する変数
	public static List<Task> taskList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//セーブデータの読み込み
					new SaveData().Load();
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
		TaskListPanel taskListPanel = new TaskListPanel();

		//課題追加パネルを追加
		TaskAddPanel taskAddPanel = new TaskAddPanel();

		//毎週課題一覧パネルを追加
		WeekTaskListPanel weekTaskListPanel = new WeekTaskListPanel();

		cardLayoutPanel.add(taskListPanel, "taskList");
		cardLayoutPanel.add(taskAddPanel, "taskAdd");
		cardLayoutPanel.add(weekTaskListPanel, "weekTaskList");

		//パネルをcontentPaneに追加
		Container contentPane = getContentPane();
		contentPane.add(cardLayoutPanel);
	}

}
