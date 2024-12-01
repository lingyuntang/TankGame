package com.END;


public class Tank {
    private int x;// 坦克横轴
    private int y;// 坦克纵轴
    private int direct;// 坦克方向
    private int speed;// 坦克速度
    boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    // 向上移动
    public void moveUp() {
        y -= speed;
    }

    // 向右移动
    public void moveRight() {
        x += speed;
    }

    // 向下移动
    public void moveDown() {
        y += speed;
    }

    // 向左移动
    public void moveLeft() {
        x -= speed;
    }
}
