package com.END;

import java.util.Vector;

public class Hero extends Tank {
    Vector<Shot> shots = new Vector<>();
    Shot shot;

    public Hero(int x, int y) {
        super(x, y);
    }

    // 设置子弹初始的x和y坐标
    public void HeroBullet() {

        // 控制子弹数量为5
        if(shots.size() == 5) {
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
        shots.add(shot);
        // 启动子弹线程
        new Thread(shot).start();
    }
}
