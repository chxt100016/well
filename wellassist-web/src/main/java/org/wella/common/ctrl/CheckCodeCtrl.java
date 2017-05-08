package org.wella.common.ctrl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CheckCodeCtrl {
    private int width = 70;
    private int height = 30;
    private int codeLength = 4;
    private String[] charArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "W", "Z"};
    private String[] numberArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public CheckCodeCtrl() {
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String generateRandomNumberCode() {
        String sRand = "";
        Random random = new Random();

        for(int i = 0; i < this.codeLength; ++i) {
            sRand = sRand + random.nextInt(10);
        }

        return sRand;
    }

    public String generateRandomMixedCode() {
        String sRand = "";
        Random random = new Random();

        for(int i = 0; i < this.codeLength; ++i) {
            sRand = sRand + this.charArray[random.nextInt(this.charArray.length)];
        }

        return sRand;
    }

    public String generateRandomNumCode() {
        String sRand = "";
        Random random = new Random();

        for(int i = 0; i < this.codeLength; ++i) {
            sRand = sRand + this.numberArray[random.nextInt(this.numberArray.length)];
        }

        return sRand;
    }

    public BufferedImage getImage(String checkCode) {
        BufferedImage image = new BufferedImage(this.width, this.height, 1);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.width, this.height);
        g.setFont(new Font("Times New Roman", 0, 20));
        g.setColor(new Color(255, 255, 255));

        for(int i = 0; i < checkCode.length(); ++i) {
            g.setColor(new Color(0, 0, 0));
            g.drawString(String.valueOf(checkCode.charAt(i)), 13 * i + 8, 20);
        }

        g.dispose();
        return image;
    }

    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}