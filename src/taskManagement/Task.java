package taskManagement;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Task {
	String name;
	String detail;
	Calendar deadline = Calendar.getInstance();
	JLabel label;
	JButton detailButton = new JButton("詳細");
	JCheckBox completeButton = new JCheckBox("完了");
	JLabel timeleftLabel;

	public Task(String name, String detail, int year, int month, int day) {
		this.name = name;
		this.deadline.clear();
		this.deadline.set(year, month, day);
		this.detail = detail;
		this.label = new JLabel(name);
		if (deadline.compareTo(Calendar.getInstance()) <= 0) {
			this.timeleftLabel = new JLabel("期限切れてます！！！");
		} else {
			int timeleft = 0;
			Calendar checkDate = Calendar.getInstance();
			for (int i = 1;; i++) {
				checkDate.add(Calendar.DATE, 1);
				if (deadline.compareTo(checkDate) <= 0) {
					timeleft = i;
					break;
				}
			}
			this.timeleftLabel = new JLabel("期限まで残り" + timeleft + "日");
		}
	}

}
