package taskManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class WeekTaskReflect {

	public WeekTaskReflect() {
		// 現在日時と最終使用日を取得
		Calendar today = Calendar.getInstance();
		Calendar day = getLastUseDate();

		//現在日を一時間後にする
		today.add(Calendar.HOUR, 1);

		//繰り返しは30日までしか行わない
		for (int i = 0; i < 30; i++) {
			//一日進める
			day.add(Calendar.DATE, 1);

			//デバック
			//System.out.println("day=" + day.get(Calendar.YEAR) + "," + day.get(Calendar.MONTH) + ","+ day.get(Calendar.DATE) + ",");

			//今日より未来を指していたら終了
			if (day.after(today)) {

				//デバック
				//System.out.println("終了");

				break;
			}
			//対象日の曜日を取得
			int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);

			//デバック
			//System.out.println(dayOfWeek);

			for (int j = 0; j < TaskManagement.weekTaskList.size(); j++) {
				WeekTask weekTask = TaskManagement.weekTaskList.get(j);
				if (dayOfWeek == weekTask.dayOfWeek) {
					//毎週課題を通常課題に変換
					Calendar deadline = day;
					deadline.add(Calendar.DATE, weekTask.period);
					Task task = new Task(weekTask.name, weekTask.detail,
							deadline.get(Calendar.YEAR), deadline.get(Calendar.MONTH), deadline.get(Calendar.DATE));
					TaskManagement.taskList.add(task);

					//dayの値を元に戻す
					day.add(Calendar.DATE, -1 * weekTask.period);

					//セーブ
					new SaveData().taskSave();
				}
			}
		}

		//現在日時を最終使用日に更新
		setLastUseDate(today);

	}

	public Calendar getLastUseDate() {
		//最終仕様日(問題があった場合は現在日時を返答するため初期値は現在日時)
		Calendar lastUseDate = Calendar.getInstance();
		try {
			//ファイル読み込み
			File saveFile = new File("lastUseDate.csv");

			if (saveFile.exists()) {
				FileReader fileReader = new FileReader(saveFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String content;
				String[] contentList = new String[3];

				while ((content = bufferedReader.readLine()) != null) {
					//カンマで分割してそれぞれを摘出する。
					contentList = content.split(",", 3);

					//期限がしっかりと日時になっているか確認する。
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < contentList[i].length(); j++) {
							if (!Character.isDigit(contentList[i].charAt(j))) {
								System.out.println("セーブデータ内に格納されている最終仕様日が数字以外になっています");
								bufferedReader.close();
								return Calendar.getInstance();
							}
						}
					}
				}

				lastUseDate.set(Integer.parseInt(contentList[0]), Integer.parseInt(contentList[1]),
						Integer.parseInt(contentList[2]));

				bufferedReader.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastUseDate;
	}

	public void setLastUseDate(Calendar lastUseDate) {
		try {
			//ファイル読み込み
			File saveFile = new File("lastUseDate.csv");
			FileWriter fileWriter = new FileWriter(saveFile);

			//セーブデータの内容書き込み
			fileWriter.write(lastUseDate.get(Calendar.YEAR) + "," + lastUseDate.get(Calendar.MONTH) + ","
					+ lastUseDate.get(Calendar.DATE) + "\r\n");
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
