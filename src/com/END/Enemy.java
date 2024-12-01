package com.END;

import java.util.Vector;

public class Enemy extends Tank implements Runnable{
    Vector<Shot> enemyShots = new Vector<>();
    Vector<Enemy> enemies = new Vector<>();
    Shot shot;
    boolean loop = true;

    public Enemy(int x, int y) {
        super(x, y);
    }

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    // 检测是否碰撞
    public boolean isCrash() {
        // 自己的方向
        switch (getDirect()) {
            case 0:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    // 不和自己比
                    if(enemy != this) {
                        // 别人上下时
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                            // 自己左上角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            // 自己右上角坐标
                            if(this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        // 别人左右时
                        if(enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                            // 自己左上角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            // 自己右上角坐标
                            if(this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    // 不和自己比
                    if(enemy != this) {
                        // 别人上下时
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                            // 自己右上角坐标
                            if(this.getX() + 60>= enemy.getX()
                                    && this.getX() + 60<= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            // 自己右下角坐标
                            if(this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 40
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        // 别人左右时
                        if(enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                            // 自己右上角坐标
                            if(this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            // 自己右下角坐标
                            if(this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 60
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    // 不和自己比
                    if(enemy != this) {
                        // 别人上下时
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                            // 自己左下角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 60) {
                                return true;
                            }
                            // 自己右下角坐标
                            if(this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 40
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        // 别人左右时
                        if(enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                            // 自己左下角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 40) {
                                return true;
                            }
                            // 自己右下角坐标
                            if(this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 60
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    // 不和自己比
                    if(enemy != this) {
                        // 别人上下时
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2) {
                            // 自己左上角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            // 自己左下角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        // 别人左右时
                        if(enemy.getDirect() == 1 || enemy.getDirect() == 3) {
                            // 自己左上角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            // 自己左下角坐标
                            if(this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }

        return false;
    }

    // 设置子弹初始的x和y坐标
    public void enemyBullet() {

        // 控制子弹数量为8
        if(enemyShots.size() >= 8) {
            return;
        }

        // 子弹发出时的方向
        switch (getDirect()) {
            case 0: // 向上
                shot = new Shot(getX() + 20, getY(), getDirect(), 4);
                break;
            case 1: // 向右
                shot = new Shot(getX() + 60, getY() + 20, getDirect(), 4);
                break;
            case 2: // 向下
                shot = new Shot(getX() + 20, getY() + 60, getDirect(), 4);
                break;
            case 3: // 向左
                shot = new Shot(getX(), getY() + 20, getDirect(), 4);
                break;
        }

        // 将子弹加入到子弹集合中
        enemyShots.add(shot);
        // 启动子弹线程
        new Thread(shot).start();
    }

    @Override
    public void run() {
        while(loop) {
            int n = 0;

            // 子弹自动化装箱
            enemyBullet();

            // 根据方向自己移动
            switch (getDirect()) {
                // 一个方向走完40次再进入下一次循环，防止坦克一直转向
                // 设置边界，使坦克不会移动到墙外，而break可以使坦克碰到墙后立马转向（不用面壁）
                case 0: // 向上
                    while (n != 40) {
                        if(getY() > 0 && !isCrash()) {
                            moveUp();
                        }else {
                            break;
                        }

                        try {
                            Thread.sleep(20);
                            n++; // 跳出循环条件
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 1: // 向右
                    while (n != 40) {
                        if(getX() + 60 < 1100 && !isCrash()) {
                            moveRight();
                        }else {
                            break;
                        }

                        try {
                            Thread.sleep(20);
                            n++; // 跳出循环条件
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 2: // 向下
                    while (n != 40) {
                        if(getY() + 60 < 850 && !isCrash()) {
                            moveDown();
                        }else {
                            break;
                        }

                        try {
                            Thread.sleep(20);
                            n++; // 跳出循环条件
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 3: // 向左
                    while (n != 40) {
                        if (getX() > 0 && !isCrash()) {
                            moveLeft();
                        } else {
                            break;
                        }

                        try {
                            Thread.sleep(20);
                            n++; // 跳出循环条件
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                default:
                    System.out.println("错误");
                    break;
            }

            // 随机改变方向 0~3
            setDirect((int)(Math.random() * 4));

            // 如果坦克死亡，结束循环
            if (!isLive) {
                loop = false;
            }
        }
    }
}
