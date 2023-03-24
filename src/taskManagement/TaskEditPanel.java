package taskManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JPanel;

public class TaskEditPanel extends TaskAddPanel {

	Task task;
	int number;

	public TaskEditPanel(Task task, int number) {
		super();
		this.task = task;
		this.number = number;
		title.setText("タスクの編集");
		nameField.setText(this.task.name);
		yearField.setText(String.valueOf(this.task.deadline.get(Calendar.YEAR)));
		monthField.setText(String.valueOf(this.task.deadline.get(Calendar.MONTH) + 1));
		dayField.setText(String.valueOf(this.task.deadline.get(Calendar.DATE)));
		detailField.setText(this.task.detail);
		addButton.setText("編集完了");
	}

	//TaskAddを新規追加から現状のものを上書きする形で追加するものに変更
	@Override
	public void taskadd() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//入力内容からタスクを読み込む
				String nameString = nameField.getText();
				String yearString = yearField.getText();
				String monthString = monthField.getText();
				String dayString = dayField.getText();
				String detailString = detailField.getText();
				if (!inputCheck(nameString, yearString, monthString, dayString, detailString)) {
					TaskManagement.taskList.set(number, new Task(nameString, detailString, Integer.parseInt(yearString),
							Integer.parseInt(monthString) - 1, Integer.parseInt(dayString)));
					//更新情報をセーブする
					new SaveData().taskSave();
					//taskListの内容更新
					JPanel taskListJPanel = new TaskListPanel();
					TaskManagement.cardLayoutPanel.add(taskListJPanel, "taskList");
				}
			}
		});

	}

}
