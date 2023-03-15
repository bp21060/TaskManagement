package taskManagement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class WeekTask {
	String name;
	String detail;
	//タスクが自動追加される曜日
	int dayOfTheWeek;
	//タスクが自動追加される日から期限までの日にち
	int period;
	JLabel label;
	JButton detailButton = new JButton("詳細");
	JCheckBox completeButton = new JCheckBox("削除");

	public WeekTask(String name, String detail, int dayOfTheWeek, int period) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.detail = detail;
		this.dayOfTheWeek = dayOfTheWeek;
		this.period = period;
		this.label = new JLabel(name);
	}
}
