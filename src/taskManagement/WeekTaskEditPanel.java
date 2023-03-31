package taskManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class WeekTaskEditPanel extends WeekTaskAddPanel {

	WeekTask task;
	int number;

	public WeekTaskEditPanel(WeekTask weekTask, int number) {
		super();
		this.task = weekTask;
		this.number = number;
		title.setText("タスクの編集");
		nameField.setText(this.task.name);
		periodField.setText(String.valueOf(this.task.period));
		setDayOfWeekButton(weekTask);
		detailField.setText(this.task.detail);
		addButton.setText("編集完了");
	}

	//TaskAddを新規追加から現状のものを上書きする形で追加するものに変更
	@Override
	public void taskAdd() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//入力内容からタスクを読み込む
				String nameString = nameField.getText();
				String periodString = periodField.getText();
				String detailString = detailField.getText();
				if (!inputCheck(nameString, periodString, detailString)) {
					//曜日を数字に変換
					int dayOfTheWeek = dayOfTheWeekChange();
					//更新
					TaskManagement.weekTaskList.set(number,
							new WeekTask(nameString, detailString, dayOfTheWeek, Integer.parseInt(periodString)));

					//更新情報をセーブする
					new SaveData().weekTaskSave();
					//taskListの内容更新
					JPanel weekTaskListJPanel = new WeekTaskListPanel();
					TaskManagement.cardLayoutPanel.add(weekTaskListJPanel, "weekTaskList");
				}
			}
		});

	}

	//数字から曜日に変換するメソッド
	public void setDayOfWeekButton(WeekTask weekTask) {
		switch (weekTask.dayOfWeek) {
		case 7:
			saturday.setSelected(true);
			break;
		case 6:
			friday.setSelected(true);
			break;
		case 5:
			thursday.setSelected(true);
			break;
		case 4:
			wednesday.setSelected(true);
			break;
		case 3:
			tuseday.setSelected(true);
			break;
		case 1:
			sunday.setSelected(true);
			break;
		default:
			monday.setSelected(true);
		}
	}

}
