package com.END;

import java.io.*;
import java.util.Vector;

public class Recoder {
    // 击毁数目
    private static int allDeadEnemy = 0;
    // IO对象读写文件存储战绩
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static String recordFile = "src/myRecord.txt";
    // 敌方坦克集合
    private static Vector<Enemy> enemies;
    // 定义一个保存Node的Vector
    private static Vector<Node> nodes = new Vector<>();

    // 用于读取文件恢复敌方坦克信息
    public static Vector<Node> getNodesAndDeadSum() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allDeadEnemy = Integer.parseInt(br.readLine()); // 首行读取
            // 循环读取剩余文件信息
            String line = "";
            while ((line = br.readLine()) != null) {
                // 处理取得的信息，创建Node对象存入nodes集合
                String[] enemyRecord = line.split(" ");
                Node node = new Node(Integer.parseInt(enemyRecord[0])
                        , Integer.parseInt(enemyRecord[1])
                        , Integer.parseInt(enemyRecord[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return nodes;
    }

    public static void setEnemies(Vector<Enemy> enemies) {
        Recoder.enemies = enemies;
    }

    // 存储信息
    public static void keepNum() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allDeadEnemy + "\r\n");
            bw.flush();

            // 遍历敌人坦克集合，保存信息
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if(enemy.isLive) {
                    // 获取坦克信息保存进文件
                    String record = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirect();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println("保存失败");
        } finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("流退出失败");
            }
        }
    }

    public static int getAllDeadEnemy() {
        return allDeadEnemy;
    }

    public static void addNum() {
        allDeadEnemy++;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static void setAllDeadEnemy(int allDeadEnemy) {
        Recoder.allDeadEnemy = allDeadEnemy;
    }
}
