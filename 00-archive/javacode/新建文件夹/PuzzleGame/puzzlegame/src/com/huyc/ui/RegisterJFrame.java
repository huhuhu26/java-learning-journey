package com.huyc.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame{

    public RegisterJFrame(){
        initJFrame();
        this.setVisible(true);
    }


    private void initJFrame(){
        //设置界面宽高
        this.setSize(488,500);
        //设置标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
