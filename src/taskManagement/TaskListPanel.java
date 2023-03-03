package taskManagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TaskListPanel extends JPanel {

	public TaskListPanel() {

		//タスク一覧タスクの表示
		JPanel mainPanel = new JPanel();
		GroupLayout layout2 = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout2);

		//間隔開ける
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);

		List<JComponent> taskList = new ArrayList<>();
		int taskAmount = TaskManagement.taskList.size();

		for (int i = 0; i < taskAmount; i++) {
			//タスクリストの追加
			Task task = TaskManagement.taskList.get(i);
			int number = i;
			taskList.add(task.label);
			taskList.add(task.detailButton);
			taskList.add(task.completeButton);
			//detailボタンの設定
			task.detailButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//taskEditの作成
					JPanel taskEditJPanel = new TaskEditPanel(task, number);
					TaskManagement.cardLayoutPanel.add(taskEditJPanel, "taskEdit");
					//taskEditの表示
					TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "taskEdit");
				}
			});
		}

		//水平グループ
		SequentialGroup hGroup = layout2.createSequentialGroup();

		for (int i = 0; i < 3; i++) {
			ParallelGroup pGroup = layout2.createParallelGroup();
			for (int j = 0; j < taskAmount; j++) {
				pGroup.addComponent(taskList.get(3 * j + i));
			}
			hGroup.addGroup(pGroup);
		}

		layout2.setHorizontalGroup(hGroup);

		//垂直グループ
		SequentialGroup vGroup = layout2.createSequentialGroup();

		for (int i = 0; i < taskAmount; i++) {
			ParallelGroup pGroup2 = layout2.createParallelGroup();
			for (int j = 0; j < 3; j++) {
				pGroup2.addComponent(taskList.get(3 * i + j));
			}
			vGroup.addGroup(pGroup2);
		}

		layout2.setVerticalGroup(vGroup);

		//スクロールの追加
		JScrollPane taskScroll = new JScrollPane(mainPanel);
		taskScroll.getVerticalScrollBar().setUnitIncrement(20);

		//追加、削除ボタンのパネルを追加
		JPanel operationButton = new JPanel();
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(100);

		JButton addButton = new JButton("課題の追加");
		//課題追加画面への遷移ボタンの操作
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, command);
			}
		});
		addButton.setActionCommand("taskAdd");

		JButton deleteButton = new JButton("完了した課題の削除");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = taskAmount - 1; i >= 0; i--) {
					//完了したタスクの削除
					if (TaskManagement.taskList.get(i).completeButton.isSelected()) {
						TaskManagement.taskList.remove(i);
					}
				}
				//更新情報をセーブする
				new SaveData().Save();

				//taskListの内容更新
				JPanel taskListJPanel = new TaskListPanel();
				TaskManagement.cardLayoutPanel.add(taskListJPanel, "taskList");
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "taskList");
			}
		});
		operationButton.setLayout(layout);
		operationButton.add(addButton);
		operationButton.add(deleteButton);

		//全体の画面を表示
		this.setLayout(new BorderLayout());
		this.add(taskScroll, BorderLayout.CENTER);
		this.add(operationButton, BorderLayout.PAGE_END);
	}

}
