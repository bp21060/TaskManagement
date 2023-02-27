package taskManagement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Task {
	String name;
	String detail;
	int year;
	int month;
	int day;
	JLabel label;
	JButton detailButton = new JButton("詳細");
	JCheckBox completeButton = new JCheckBox("完了");

	public Task(String name, String detail, int year, int month, int day) {
		this.name = name;
		this.detail = detail;
		this.year = year;
		this.month = month;
		this.day = day;
		this.label = new JLabel(name);
	}

}
