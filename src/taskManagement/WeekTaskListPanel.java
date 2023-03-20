package taskManagement;

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

public class WeekTaskListPanel extends TaskListPanel {

	public WeekTaskListPanel() {
		super();
	}

	//メイン画面
	@Override
	public JPanel mainPanel() {

		JPanel mainPanel = new JPanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		//間隔開ける
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		List<JComponent> weekTaskList = new ArrayList<>();
		taskAmount = TaskManagement.weekTaskList.size();

		for (int i = 0; i < taskAmount; i++) {
			//タスクリストの追加
			WeekTask weekTask = TaskManagement.weekTaskList.get(i);
			int number = i;
			weekTaskList.add(weekTask.label);
			weekTaskList.add(weekTask.detailButton);
			weekTaskList.add(weekTask.completeButton);
			//detailボタンの設定
			weekTask.detailButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//taskEditの作成
					JPanel weekTaskEditJPanel = new WeekTaskEditPanel(weekTask, number);
					TaskManagement.cardLayoutPanel.add(weekTaskEditJPanel, "taskEdit");
					//taskEditの表示
					TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "taskEdit");
				}
			});
		}

		//水平グループ
		SequentialGroup hGroup = layout.createSequentialGroup();

		for (int i = 0; i < 3; i++) {
			ParallelGroup pGroup = layout.createParallelGroup();
			for (int j = 0; j < taskAmount; j++) {
				pGroup.addComponent(weekTaskList.get(3 * j + i));
			}
			hGroup.addGroup(pGroup);
		}

		layout.setHorizontalGroup(hGroup);

		//垂直グループ
		SequentialGroup vGroup = layout.createSequentialGroup();

		for (int i = 0; i < taskAmount; i++) {
			ParallelGroup pGroup2 = layout.createParallelGroup();
			for (int j = 0; j < 3; j++) {
				pGroup2.addComponent(weekTaskList.get(3 * i + j));
			}
			vGroup.addGroup(pGroup2);
		}

		layout.setVerticalGroup(vGroup);
		return mainPanel;

	}

	//課題追加パネル遷移ボタン
	@Override
	public JButton addButton() {
		JButton addButton = new JButton("毎週課題の追加");

		//課題追加画面への遷移ボタンの操作
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "weekTaskAdd");
			}
		});

		return addButton;
	}

	//課題削除ボタン
	@Override
	public JButton deleteButton() {
		JButton deleteButton = new JButton("毎週課題の削除");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = taskAmount - 1; i >= 0; i--) {
					//完了したタスクの削除
					if (TaskManagement.weekTaskList.get(i).completeButton.isSelected()) {
						TaskManagement.weekTaskList.remove(i);
					}
				}

				/*
				//更新情報をセーブする
				new SaveData().Save();
				*/

				//taskListの内容更新
				JPanel weekTaskListJPanel = new WeekTaskListPanel();
				TaskManagement.cardLayoutPanel.add(weekTaskListJPanel, "weekTaskList");
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "weekTaskList");
			}
		});
		return deleteButton;
	}

	//表示するタスクの種類を切り替えるボタン
	@Override
	public JButton taskSwitchingButton() {
		JButton taskSwitchingButton = new JButton("課題");
		//課題追加画面への遷移ボタンの操作
		taskSwitchingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaskManagement.cardLayout.show(TaskManagement.cardLayoutPanel, "taskList");
			}
		});

		return taskSwitchingButton;
	}
}
