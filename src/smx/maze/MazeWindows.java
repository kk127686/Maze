package smx.maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeWindows extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu_file,menu_other;
    private JMenuItem menu_open,menu_charge,menu_help,menu_about;
    public MazeWindows(){
        setTitle("迷宫");
        setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\09-28-2018-学习\\面向对象程序设计\\课程设计\\smx\\迷宫文件\\person.gif"));
        setLayout(new BorderLayout());
        setCenter(1000,720);
        initMenu();
        initView();
        initEvent();
        setVisible(true);
    }



    private void initView() {
    }
    private void initEvent() {
        menu_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog=new JDialog();
                jDialog.setTitle("关于");
                Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
                int xPos=((int)dimension.getWidth()-300)/2;
                int yPos=((int)dimension.getHeight()-300)/2;
                jDialog.setBounds(xPos,yPos,300,300);
                Box box=Box.createVerticalBox();
                box.add(new JLabel("关于"));
                box.add(new JLabel("关于"));
                box.add(new JLabel("关于"));
                box.add(new JLabel("关于"));
                box.add(new JLabel("关于"));
                jDialog.add(box);
                jDialog.show();
            }
        });
    }
    private void initMenu() {
        menuBar=new JMenuBar();
        menu_file=new JMenu("文件");
        menu_other=new JMenu("其他");

        menuBar.add(menu_file);
        menuBar.add(menu_other);

        menu_open=new JMenuItem("打开");
        menu_open.setToolTipText("打开之前的地图");
        menu_charge=new JMenuItem("刷新地图");
        menu_help=new JMenuItem("游戏帮助");
        menu_about=new JMenuItem("关于");

        menu_file.add(menu_open);
        menu_file.add(menu_charge);

        menu_other.add(menu_help);
        menu_other.add(menu_about);

        setJMenuBar(menuBar);


    }
    private void setCenter(int width,int height){
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth=(int)dimension.getWidth();
        int screenHeight=(int)dimension.getHeight();
        int xPos=(screenWidth-width)/2;
        int yPos=(screenHeight-height)/2;
        setBounds(xPos,yPos,width,height);
    }




}
