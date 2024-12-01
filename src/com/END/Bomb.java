package com.END;

public class Bomb {
    int x, y; // 炸弹坐标
    int life = 9; // 炸弹生命
    boolean isLive = true; // 是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 减少生命值
    public void lifeDown() {
        if(life > 0) {
            life--;
        }else {
            isLive = false;
        }
    }

}
