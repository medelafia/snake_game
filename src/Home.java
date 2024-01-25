import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    ImageIcon image = new ImageIcon("C:\\Users\\fujitsu\\Documents\\NetBeansProjects\\Graphics\\src\\snake.jpg");
    private JLabel label = new JLabel();
    private JLabel gameTitle = new JLabel("snake game");
    private JButton play = new JButton("play");
    private  JButton level = new JButton("Level");
    private JButton exit = new JButton("exit");
    public Home() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLabel();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void setLabel() {
        label.setBounds(0,0,600,400);
        label.setIcon(image);
        label.setLayout(null);
        label.add(gameTitle);
        gameTitle.setFont(new Font(null , Font.BOLD , 30));
        gameTitle.setForeground(Color.RED);
        gameTitle.setVerticalAlignment(SwingConstants.CENTER);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setBounds(label.getWidth() / 2 - 100 , 70 , 200,40);
        label.add(play);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintApp paintApp = new PaintApp();
                Home.this.setVisible(false);
            }
        });
        play.setBounds(label.getWidth()/2 - 100 , label.getHeight()/3, 200 , 40);
        play.setBackground(Color.RED);
        play.setForeground(Color.white);
        label.add(level);
        level.setBounds(label.getWidth()/2 - 100 , label.getHeight()/3 + 50 , 200 , 40);
        level.setBackground(Color.RED);
        level.setForeground(Color.white);
        label.add(exit);
        exit.setBounds(label.getWidth()/2 -100 , ( label.getHeight())/3 + 100, 200 , 40);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home.this.dispose();
            }
        });
        exit.setBackground(Color.RED);
        exit.setForeground(Color.white);
        this.add(this.label);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
