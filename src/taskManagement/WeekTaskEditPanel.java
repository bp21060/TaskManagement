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
		dayOfTheWeekComboBox.setSelectedItem(dayOfTheWeekChange(weekTask));
		periodField.setText(String.valueOf(this.task.period));
		detailField.setText(this.task.detail);
		addButton.setText("編集完了");
	}

	//TaskAddを新規追加から現状のものを上書きする形で追加するものに変更
	@Override
	public void Taskadd() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//入力内容からタスクを読み込む
				String nameString = nameField.getText();
				String periodString = periodField.getText();
				String detailString = detailField.getText();
				if (!InputCheck(nameString, periodString, detailString)) {
					//曜日を数字に変換
					int dayOfTheWeek = dayOfTheWeekChange();
					//更新
					TaskManagement.weekTaskList.set(number,
							new WeekTask(nameString, detailString, dayOfTheWeek, Integer.parseInt(periodString)));

					//更新情報をセーブする
					//new SaveData().Save();
					//taskListの内容更新
					JPanel weekTaskListJPanel = new WeekTaskListPanel();
					TaskManagement.cardLayoutPanel.add(weekTaskListJPanel, "weekTaskList");
				}
			}
		});

	}

	//数字から曜日に変換するメソッド
	public String dayOfTheWeekChange(WeekTask weekTask) {
		String result;
		switch (weekTask.dayOfTheWeek) {
		case 6:
			result = "土曜日";
			break;
		case 5:
			result = "金曜日";
			break;
		case 4:
			result = "木曜日";
			break;
		case 3:
			result = "水曜日";
			break;
		case 2:
			result = "火曜日";
			break;
		case 1:
			result = "月曜日";
			break;
		default:
			result = "日曜日";
		}

		return result;
	}

}
