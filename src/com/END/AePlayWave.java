package com.END;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Closeable;

// 播放音乐
public class AePlayWave implements Runnable {
    private String filename;

    public AePlayWave(String wavfile) {
        this.filename = wavfile;
    }

    @Override
    public void run() {
        File soundFile = new File(filename);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try (SourceDataLine auline = (SourceDataLine) AudioSystem.getLine(info)) {
                auline.open(format);
                auline.start();

                byte[] abData = new byte[512];
                int nBytesRead;
                while ((nBytesRead = audioInputStream.read(abData, 0, abData.length)) != -1) {
                    auline.write(abData, 0, nBytesRead);
                }

                auline.drain(); // 确保所有数据都被播放
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace(); // 捕获文件格式错误
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
