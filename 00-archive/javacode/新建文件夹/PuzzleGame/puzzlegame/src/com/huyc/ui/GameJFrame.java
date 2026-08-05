package com.huyc.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public final class GameJFrame extends JFrame implements KeyListener, ActionListener {

    public GameJFrame() {
        initJFrame();
        //初始化菜单
        initJMenuBar();

        //初始化数据
        initDate();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    int x = 0;
    int y = 0;
    String path = "PuzzleGame\\image\\animal\\animal3\\";

    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int[][] imageArr = new int[4][4];

    //创建选项下的条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem beautifulItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem motionItem = new JMenuItem("运动");

    private void initDate() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        Random r = new Random();
        int number;
        for (int i = 0; i < tempArr.length; i++) {
            number = r.nextInt(15);
            int temp = tempArr[number];
            tempArr[number] = tempArr[i];
            tempArr[i] = temp;
        }
        number = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tempArr[number] == 0) {
                    x = i;
                    y = j;
                }
                imageArr[i][j] = tempArr[number];
                number++;
            }
        }
    }

    int step = 0;

    private void initImage() {
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        int number = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (imageArr[i][j] == 0) {
                    continue;
                }
                //创建一个图片ImageIcon对象
                ImageIcon icon = new ImageIcon(path + imageArr[i][j] + ".jpg");
                //创建BLabel管理图片
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 143, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(1));
                //添加管理图片
                this.add(jLabel);

            }
        }

        //初始化背景图片
        JLabel backGround = new JLabel(new ImageIcon("PuzzleGame\\image\\background.png"));
        backGround.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backGround);

        //刷新界面
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        JMenuBar jMenubar = new JMenuBar();
        //创建菜单上两个选项
        JMenu factionMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeImage = new JMenu("更换图片");

        //添加条目
        factionMenu.add(changeImage);
        factionMenu.add(replayItem);
        factionMenu.add(reLoginItem);
        factionMenu.add(closeItem);
        aboutJMenu.add(accountItem);
        changeImage.add(beautifulItem);
        changeImage.add(animalItem);
        changeImage.add(motionItem);
        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        beautifulItem.addActionListener(this);
        animalItem.addActionListener(this);
        motionItem.addActionListener(this);
        //添加到菜单
        jMenubar.add(factionMenu);
        jMenubar.add(aboutJMenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenubar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(603, 630);
        //设置标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //取消居中默认
        this.setLayout(null);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (victory()) {
            return;
        }

        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel backGround = new JLabel(new ImageIcon("PuzzleGame\\image\\background.png"));
            backGround.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backGround);
            //刷新
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()) {
            return;
        }

        int code = e.getKeyCode();

        if (code == 37) {
            if (y == 3) {
                return;
            }
            System.out.println("向左移动");
            imageArr[x][y] = imageArr[x][y + 1];
            imageArr[x][y + 1] = 0;
            y++;
            step++;
            initImage();

        } else if (code == 38) {
            if (x == 3) {
                return;
            }
            System.out.println("向上移动");
            imageArr[x][y] = imageArr[x + 1][y];
            imageArr[x + 1][y] = 0;
            x++;
            step++;
            //重新加载图片
            initImage();
        } else if (code == 39) {
            if (y == 0) {
                return;
            }
            System.out.println("向右移动");
            imageArr[x][y] = imageArr[x][y - 1];
            imageArr[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            if (x == 0) {
                return;
            }
            System.out.println("向下移动");
            imageArr[x][y] = imageArr[x - 1][y];
            imageArr[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            imageArr = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (imageArr[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    int a;
    Random r = new Random();

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            //计步数清零
            step = 0;
            //再次打乱二维数组的数据
            initDate();
            //再次加载图片
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登陆");
            //关闭当前对象
            this.setVisible(false);
            //创建登陆界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //创建管理图片对象
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //添加弹窗
            jDialog.getContentPane().add(jLabel);
            //给弹窗设置大小
            jDialog.setSize(344, 344);
            //让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //弹窗剧中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);
        } else if (obj == beautifulItem) {

            a = r.nextInt(13) + 1;
            path = "puzzlegame\\image\\girl\\gail" + a + ".png";

        }else if(obj == animalItem){
            a = r.nextInt(8) + 1;
            path = "puzzlegame\\image\\girl\\gail" + a + ".png";
        }else if(obj == motionItem){
            a = r.nextInt(10) + 1;
            path = "puzzlegame\\image\\girl\\gail" + a + ".png";
        }
    }
}
