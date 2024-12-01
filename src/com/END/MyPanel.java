package com.END;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable{
    Hero hero; // 己方坦克
    Vector<Enemy> enemies = new Vector<>(); // 敌方坦克集合
    int enemySize = 5; // 敌方坦克数量
    static int LOCATION = 1;
    Vector<Bomb> bombs = new Vector<>(); // 炸弹集合
    Vector<Node> nodes = new Vector<>(); // 用于恢复敌人信息的集合
    // 炸弹图片，显示爆炸效果
    Image image1;
    Image image2;
    Image image3;

    public MyPanel(String key) {
        // 判断文件是否存在
        File file = new File(Recoder.getRecordFile());
        if(file.exists()) {
            nodes = Recoder.getNodesAndDeadSum();
        }else {
            System.out.println("文件不存在，只能重开");
            key = "0";
        }

        // 音乐
        new Thread(new AePlayWave("src/GameMusic.wav")).start();

        // 初始化一个己方坦克
        hero = new Hero(300, 600);
        hero.setSpeed(2);

        switch (key) {
            case "0": // 重开
                Recoder.setAllDeadEnemy(0);
                // 初始化敌方坦克
                for (int i = 0; i < enemySize; i++) {
                    LOCATION ++; // 使敌方坦克生成在不同位置
                    Enemy enemy = new Enemy(60 * LOCATION, 100); // 创建敌方坦克
                    enemy.setDirect(2); // 设置方向
                    enemy.setSpeed(2);// 设置速度
                    new Thread(enemy).start(); // 打开坦克线程
                    enemies.add(enemy); // 将该敌方坦克加入到敌方坦克集合中
                    enemy.setEnemies(enemies); // 让敌方坦克中的坦克集合属性指向该真正的集合
                }

                // 让Recoder类中的坦克集合属性指向该真正的集合
                Recoder.setEnemies(enemies);

                // 初始化图片对象
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));
                break;

            case "1": // 继续上一局
                // 初始化敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    LOCATION ++; // 使敌方坦克生成在不同位置
                    Enemy enemy = new Enemy(node.getX(), node.getY()); // 创建敌方坦克
                    enemy.setDirect(node.getDirect()); // 设置方向
                    enemy.setSpeed(2);// 设置速度
                    new Thread(enemy).start(); // 打开坦克线程
                    enemies.add(enemy); // 将该敌方坦克加入到敌方坦克集合中
                    enemy.setEnemies(enemies); // 让敌方坦克中的坦克集合属性指向该真正的集合
                }

                // 让Recoder类中的坦克集合属性指向该真正的集合
                Recoder.setEnemies(enemies);

                // 初始化图片对象
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));
                break;

            default:
                System.out.println("选项错误");
        }
    }

    // 显示页面
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1100, 850); // 背景，默认黑色

        // 显示战绩
        showInfo(g);

        // 画出己方坦克
        if(hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        // 画出己方子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
                drawBullet(shot.getX(), shot.getY(), g, 1);
            } else {
                hero.shots.remove(i);
                i--; // 删除元素后，索引向前移动一位，避免跳过下一个元素
            }
        }

        // 画出炸弹
        for (Bomb bomb : bombs) {
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
        }

        // 画出敌方坦克
        for (Enemy enemy : enemies) {
            if (enemy.isLive) {
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 0);
                // 画出敌方子弹
                for (int i = 0; i < enemy.enemyShots.size(); i++) {
                    Shot enemyShot = enemy.enemyShots.get(i);
                    if (enemyShot.isLive) {
                        drawBullet(enemyShot.getX(), enemyShot.getY(), g, 0);
                    } else {
                        enemy.enemyShots.remove(i);
                        i--; // 删除元素后，索引向前移动一位，避免跳过下一个元素
                    }
                }
            }
        }
    }

    /**
     * 绘制坦克
     *
     * @param x      坦克左上角 x 坐标
     * @param y      坦克左上角 y 坐标
     * @param g      画笔
     * @param direct 坦克方向
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // 设置坦克颜色
        switch (type) {
            case 0: // 我方坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 敌方坦克
                g.setColor(Color.yellow);
                break;
        }

        // 绘制坦克部件
        switch (direct) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false); // 左轮
                g.fill3DRect(x + 30, y, 10, 60, false); // 右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 坦克身体
                g.fillOval(x + 10, y + 20, 20, 20); // 坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮管
                break;
            case 1: // 向右
                g.fill3DRect(x, y, 60, 10, false); // 上轮
                g.fill3DRect(x, y + 30, 60, 10, false); // 下轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 坦克身体
                g.fillOval(x + 20, y + 10, 20, 20); // 坦克盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // 炮管
                break;
            case 2: // 向下
                g.fill3DRect(x, y, 10, 60, false); // 左轮
                g.fill3DRect(x + 30, y, 10, 60, false); // 右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 坦克身体
                g.fillOval(x + 10, y + 20, 20, 20); // 坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 炮管
                break;
            case 3: // 向左
                g.fill3DRect(x, y, 60, 10, false); // 上轮
                g.fill3DRect(x, y + 30, 60, 10, false); // 下轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 坦克身体
                g.fillOval(x + 20, y + 10, 20, 20); // 坦克盖子
                g.drawLine(x + 30, y + 20, x, y + 20); // 炮管
                break;
        }
    }

    // 绘制子弹，和坦克差不多
    public void drawBullet(int x, int y, Graphics g, int type) {
        // 设置子弹颜色
        switch (type) {
            case 0: // 我方坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 敌方坦克
                g.setColor(Color.yellow);
                break;
        }
        // 绘制子弹部件
        g.fill3DRect(x, y, 2, 2, false);
    }

    // 暂时没用
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // 处理方向键按下情况
    @Override
    public void keyPressed(KeyEvent e) {
        // 改变方向重绘坦克并改变坐标
        if (e.getKeyCode() == KeyEvent.VK_W) {
            // 向上移动时，确保坦克的上边缘不超出
            if (hero.getY() > 0) {
                hero.moveUp();
            }
            hero.setDirect(0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            // 向右移动时，确保坦克的右边缘不超出
            if (hero.getX() + 60 < 1100) {
                hero.moveRight();
            }
            hero.setDirect(1);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            // 向下移动时，确保坦克的下边缘不超出
            if (hero.getY() + 60 < 850) {
                hero.moveDown();
            }
            hero.setDirect(2);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 向左移动时，确保坦克的左边缘不超出
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
            hero.setDirect(3);
        }

        // 子弹发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
                hero.HeroBullet();
        }
    }

    // 检测是否击中我方坦克
    public boolean checkHitHero() {
        for (Enemy enemy : enemies) {
            for (Shot enemyShot : enemy.enemyShots) {
                if(hero.isLive && enemyShot.isLive) {
                    hitTank(enemyShot, hero);
                }
            }
        }

        return !hero.isLive;
    }

    // 判断是否击中敌方坦克
    public void checkHitEnemy() {
        if (hero.shot != null && hero.shot.isLive) {
            // 遍历敌方坦克
            for (Enemy enemy : enemies) {
                // 遍历我方子弹集合
                for (Shot shot : hero.shots) {
                    // 先判断敌方是否存活
                    if (enemy.isLive) {
                        hitTank(shot, enemy);
                    }
                }
            }
        }
    }

    // 检测是否击中工具
    public void hitTank(Shot s, Tank tank) {
        if (tank.isLive && s.isLive) {
            switch (tank.getDirect()) {
                case 0:
                case 2:
                    if (s.x > tank.getX() && s.x < tank.getX() + 40
                            && s.y > tank.getY() && s.y < tank.getY() + 60) {
                        s.isLive = false;
                        tank.isLive = false;
                        // 如果击毁是敌方坦克，则记录+1
                        if(tank instanceof Enemy) {
                            Recoder.addNum();
                        }
                        // 被击中后创建炸弹加入炸弹集合
                        Bomb bomb = new Bomb(tank.getX(), tank.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > tank.getX() && s.x < tank.getX() + 60
                            && s.y > tank.getY() && s.y < tank.getY() + 40) {
                        s.isLive = false;
                        tank.isLive = false;
                        // 如果击毁是敌方坦克，则记录+1
                        if(tank instanceof Enemy) {
                            Recoder.addNum();
                        }
                        // 被击中后创建炸弹加入炸弹集合
                        Bomb bomb = new Bomb(tank.getX(), tank.getY());
                        bombs.add(bomb);
                    }
                    break;
                default:
                    System.out.println("错误");
            }
        }
    }


    // 更新炸弹的状态，减少生命并移除已消失的炸弹
    public void refreshBomb() {
        // 遍历炸弹集合
        Iterator<Bomb> bombIterator = bombs.iterator();
        while (bombIterator.hasNext()) {
            Bomb bomb = bombIterator.next();
            bomb.lifeDown();  // 更新炸弹生命

            // 如果炸弹生命为0，移除炸弹
            if (bomb.life == 0) {
                bombIterator.remove();
            }
        }
    }

    // 显示我方坦克击毁敌方数目
    public void showInfo(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 25));

        g.drawString("您已累计击毁", 1120, 30);
        drawTank(1120, 60, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Recoder.getAllDeadEnemy() + "", 1180, 100);

    }

    @Override
    public void run() {
        while (true) {
            int live = 1;

            // 让线程暂停20ms，模拟游戏的帧率
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 判断是否击中敌方坦克和我方坦克
            checkHitEnemy();
            if(checkHitHero()) {
                System.out.println("你输了！");
                live = 0;
            }

            // 更新炸弹状态
            refreshBomb();

            // 重绘界面
            this.repaint();

            // 被击中退出
            if (live == 0) {
                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
