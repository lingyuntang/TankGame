package com.END;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    MyPanel mp;
    static Scanner myScanner = new Scanner(System.in); // 获取选项

    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        System.out.print("请输入信息（1为继续上一局，0为重新开始）：");
        String key = myScanner.next();

        mp = new MyPanel(key);
        this.add(mp);
        new Thread(mp).start();

        this.setSize(1300, 900);
        this.addKeyListener(mp);//监听键盘事件a
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recoder.keepNum();
                System.exit(0);
            }
        });
    }
}
