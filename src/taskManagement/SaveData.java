package taskManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveData {

	//セーブ
	public void Save() {
		try {
			//ファイル読み込み
			File saveFile = new File("saveFile.csv");
			FileWriter fileWriter = new FileWriter(saveFile);

			//セーブデータの内容書き込み
			for (int i = 0; i < TaskManagement.taskList.size(); i++) {
				Task task = TaskManagement.taskList.get(i);
				fileWriter.write(
						task.name + "," + task.year + "," + task.month + "," + task.day + "," + task.detail + "\r\n");
			}
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//セーブデータのロード
	public void Load() {
		try {
			//ファイル読み込み
			File saveFile = new File("saveFile.csv");

			//デバック
			System.out.println("1");

			if (saveFile.exists()) {
				FileReader fileReader = new FileReader(saveFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String content;
				String[] contentList = new String[5];

				//デバック
				System.out.println("2");

				while ((content = bufferedReader.readLine()) != null) {
					//カンマで分割してそれぞれを摘出する。
					contentList = content.split(",", 5);

					//デバック
					System.out.println("3");

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

					//デバック
					System.out.println("4");

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

}
