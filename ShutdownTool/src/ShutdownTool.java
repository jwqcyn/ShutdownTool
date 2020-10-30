import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class ShutdownTool {
    //代码分为结构定义语句, 和 功能执行语句.
    //功能执行语句必须以分号结尾
    public static void main(String[] args) {
        new Windows();
        System.out.println("测试git拉取和更新");
    }

}

class Shutdown{
    Shutdown(String time){
        //关机
        String s = "shutdown -s -t "+time;
        try {
            Runtime.getRuntime().exec(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CloseShutdown{
    CloseShutdown(){
        //取消关机
        try {
            Runtime.getRuntime().exec("shutdown -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Windows{
    private JFrame jf;
    private JButton jb1,jb2;
    private JTextField jtf;
    private JLabel jl1,jl2;
    Windows(){
        jf = new JFrame("自动开关机设置");
        jf.setBounds(1000,300,400,200);
        jf.setLayout(new FlowLayout());

        jtf = new JTextField(30);
        jb1 = new JButton("设置");
        jb2 = new JButton("取消关机");

        jl1 = new JLabel("请设置关机时间");
        jl2 = new JLabel("分钟");

        jf.add(jl1);
        jf.add(jtf);
        jf.add(jl2);
        jf.add(jb1);
        jf.add(jb2);

        myEvent();

        jf.setVisible(true);
    }

    private void myEvent(){
        class jb{
            jb(){
//                判断输入的是否为数字
                Pattern pattern = Pattern.compile("[0-9]*");
                boolean matches = pattern.matcher(jtf.getText()).matches();
//                调用设置关机函数，传出时间
                if (matches == true){
                    int i = Integer.parseInt(jtf.getText()) *60;
                    String text = String.valueOf(i);
                    new CloseShutdown();
                    new Shutdown(text);
                    JOptionPane.showMessageDialog(jb1,"计算机系统将在"+jtf.getText()+"分钟后关机!","警告",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(jb1,"请输入整数字符","警告",JOptionPane.WARNING_MESSAGE);
                    jtf.setText("");
                }
            }
        }

        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });


        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new jb();
            }
        });

        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CloseShutdown();
                System.exit(0);
            }
        });
    }

//    添加注释，测试git
//    二次注释
}
