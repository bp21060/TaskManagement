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

	public Task(String name, String detail, int year, int month, int day) {
		this.name = name;
		this.deadline.clear();
		this.deadline.set(year, month, day);
		this.detail = detail;
		this.label = new JLabel(name);
	}

}
