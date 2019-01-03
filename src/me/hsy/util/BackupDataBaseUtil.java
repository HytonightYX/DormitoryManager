package me.hsy.util;

import me.hsy.MainApp;

import java.io.*;

/**
 * 备份数据库工具类
 * @author HytonightYX
 * @date 2019/1/3 16:43
 */
public class BackupDataBaseUtil {

// 样例命令： mysqldump --port=3306 -uroot -padmin --column-statistics=0 --default-character-set=utf8 --  dormitory > C:\back\b.sql

    /**
     * @param username 数据库用户名
     * @param userpassword 数据库用户密码
     * @param dbName 数据库名称
     * @param backupsPath 备份路径
     * @param backupsSqlFileName 备份文件名称 name.sql
     * @return pathSql
     */
    public static boolean dbBackUp(String username, String userpassword, String dbName, String backupsPath,
                                  String backupsSqlFileName) {
        // 生成备份文件
        String pathSql = "";
        if (backupsSqlFileName.endsWith(".sql")) {
            pathSql = backupsPath + "\\" + backupsSqlFileName + ".sql";
        } else {
            pathSql = backupsPath + "\\" + backupsSqlFileName + ".sql";
        }

        String mysql = "mysqldump --port=3306 --column-statistics=0 --default-character-set=utf8" +
                " -u" + username +
                " -p" + userpassword +
                " -- " + dbName;

        File saveFile = new File(backupsPath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;

        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathSql), "utf8"));
            Process process = Runtime.getRuntime().exec(mysql);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            //0 表示线程正常终止。
            if (process.waitFor() == 0) {
                new AlertInfoUtil("消息", "备份成功！").showAndWait();
                MainApp.secondStage.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("备份失败！检查是否将路径设置为系统盘根目录C:\\");
            new AlertInfoUtil("警告", "备份失败！检查是否将路径设置为系统盘根目录C:\\").showAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("备份失败！");
            new AlertInfoUtil("警告", "备份失败！").showAndWait();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
