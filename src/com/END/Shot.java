package com.END;

public class Shot implements Runnable{
    int x; // 子弹x坐标
    int y; // y坐标
    int direct; // 方向
    int speed; // 速度
    boolean isLive = true; // 存活状态

    public Shot(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 根据 direct 的值来调整 x 和 y 坐标
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
                default:
                    System.out.println("错误");
                    break;
            }
        } while (x > 0 && x < 1100 && y > 0 && y < 850);

        isLive = false;
    }
}
