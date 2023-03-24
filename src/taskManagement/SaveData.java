package taskManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class SaveData {

	//セーブ
	public void taskSave() {
		try {
			//ファイル読み込み
			File saveFile = new File("taskSaveFile.csv");
			FileWriter fileWriter = new FileWriter(saveFile);

			//セーブデータの内容書き込み
			for (int i = 0; i < TaskManagement.taskList.size(); i++) {
				Task task = TaskManagement.taskList.get(i);
				fileWriter.write(
						task.name + "," + task.deadline.get(Calendar.YEAR) + "," + task.deadline.get(Calendar.MONTH)
								+ "," + task.deadline.get(Calendar.DATE) + "," + task.detail + "\r\n");
			}
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//毎週課題のセーブ
	public void weekTaskSave() {
		try {
			//ファイル読み込み
			File saveFile = new File("weekTaskSaveFile.csv");
			FileWriter fileWriter = new FileWriter(saveFile);

			//セーブデータの内容書き込み
			for (int i = 0; i < TaskManagement.weekTaskList.size(); i++) {
				WeekTask weekTask = TaskManagement.weekTaskList.get(i);
				fileWriter.write(
						weekTask.name + "," + weekTask.dayOfTheWeek + "," + weekTask.period + "," + weekTask.detail
								+ "\r\n");
			}
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//セーブデータのロード
	public void load() {
		taskLoad();
		weekTaskLoad();
	}

	//通常課題のロード
	public void taskLoad() {
		try {
			//ファイル読み込み
			File saveFile = new File("taskSaveFile.csv");

			if (saveFile.exists()) {
				FileReader fileReader = new FileReader(saveFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String content;
				String[] contentList = new String[5];

				while ((content = bufferedReader.readLine()) != null) {
					//カンマで分割してそれぞれを摘出する。
					contentList = content.split(",", 5);

					//期限がしっかりと日時になっているか確認する。
					for (int i = 1; i <= 3; i++) {
						for (int j = 0; j < contentList[i].length(); j++) {
							if (!Character.isDigit(contentList[i].charAt(j))) {
								System.out.println("セーブデータ内に格納されている期限が数字以外になっています");
								bufferedReader.close();
								return;
							}
						}
					}

					Task task = new Task(contentList[0], contentList[4], Integer.parseInt(contentList[1]),
							Integer.parseInt(contentList[2]), Integer.parseInt(contentList[3]));
					TaskManagement.taskList.add(task);
				}
				bufferedReader.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//毎週課題のロード
	public void weekTaskLoad() {
		try {
			//ファイル読み込み
			File saveFile = new File("weekTaskSaveFile.csv");

			if (saveFile.exists()) {
				FileReader fileReader = new FileReader(saveFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String content;
				String[] contentList = new String[4];

				while ((content = bufferedReader.readLine()) != null) {
					//カンマで分割してそれぞれを摘出する。
					contentList = content.split(",", 4);

					//開始曜日が適切な数字になっている確認する
					for (int j = 0; j < contentList[2].length(); j++) {
						if (!Character.isDigit(contentList[1].charAt(j))) {
							System.out.println("セーブデータ内に格納されている開始曜日が数字以外になっています");
							bufferedReader.close();
							return;
						}
						int i = Integer.valueOf(contentList[1]);
						if (i < 0 || i > 6) {
							System.out.println("セーブデータ内に格納されている開始曜日が適切な数字ではありません");
							bufferedReader.close();
							return;
						}
					}

					//期限がしっかりと日時になっているか確認する。
					for (int j = 0; j < contentList[2].length(); j++) {
						if (!Character.isDigit(contentList[2].charAt(j))) {
							System.out.println("セーブデータ内に格納されている期限が数字以外になっています");
							bufferedReader.close();
							return;
						}
					}

					WeekTask weekTask = new WeekTask(contentList[0], contentList[3], Integer.parseInt(contentList[1]),
							Integer.parseInt(contentList[2]));
					TaskManagement.weekTaskList.add(weekTask);
				}
				bufferedReader.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
