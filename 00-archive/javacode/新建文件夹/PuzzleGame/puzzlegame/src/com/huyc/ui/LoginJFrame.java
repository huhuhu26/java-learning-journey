package com.huyc.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame {

    public LoginJFrame(){
        initJFrame();



        this.setVisible(true);
    }

    private void initJFrame(){
        //设置界面宽高
        this.setSize(488,430);
        //设置标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        //设置背景图片
        JFrame background = new JFrame(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        this.add(background);

        JButton jbt = new JButton("注册");
        jbt.setBounds(50,50,70,50);
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击");
            }
        });
        this.getContentPane().add(jbt);
    }

}
