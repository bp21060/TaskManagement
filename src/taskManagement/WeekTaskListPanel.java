package taskManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class WeekTaskListPanel extends TaskListPanel {

	public WeekTaskListPanel() {
		super();
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
