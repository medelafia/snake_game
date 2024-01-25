import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PaintApp extends JFrame implements KeyListener{
    private static int SCORE = 0;
    private JLabel score ;
    private static JTextField scoreField ;
    private JButton stopBtn ;
    private JButton resume;
    private JButton restart ;
    private JButton start;
    private JButton newGame ;
    private JPanel header = new JPanel();
    private PaintField gameField;
    private JButton back;
    PaintApp(){
        gameField = new PaintField();
        this.addKeyListener(this);
        MainConfiguration();
    }
    public void MainConfiguration(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.componantConfiguration();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocusInWindow();
    }
    public void componantConfiguration(){
        header.setBounds(10,10,460,30);
        headerConfiguration();
        this.add(header);
        gameField.setBounds(10,50,460,400);
        this.add(gameField);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public  void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            gameField.getSnakeCels().get(0).setDIRECTION("right");
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            gameField.getSnakeCels().get(0).setDIRECTION("left");
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            gameField.getSnakeCels().get(0).setDIRECTION("up");
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            gameField.getSnakeCels().get(0).setDIRECTION("down");
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(gameField.isStoped())
                gameField.resume();
            else
                gameField.stopGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void headerConfiguration(){
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        back = new JButton(new ImageIcon("C:\\Users\\fujitsu\\Documents\\NetBeansProjects\\Graphics\\src\\home.png"));
        header.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                PaintApp.this.dispose();
            }
        });
        score = new JLabel("score");
        header.add(score);
        scoreField = new JTextField(2);
        scoreField.setText(String.valueOf(SCORE));
        scoreField.setEnabled(false);
        scoreField.setForeground(Color.white);
        scoreField.setBackground(Color.BLACK);
        header.add(scoreField);
        stopBtn = new JButton("stop");
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.stopGame();
           }
        });
        header.add(stopBtn);
        resume = new JButton("resume");
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.resume();
                PaintApp.this.requestFocus();
            }
        });
        header.add(resume);
        newGame = new JButton("new Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.newGame();
                PaintApp.this.requestFocus();
                setScoreToZero();
            }
        });
        header.add(newGame);
    }
    public static void setScore(){
        SCORE +=1 ;
        scoreField.setText(String.valueOf(Integer.parseInt(scoreField.getText())+1));
    }
    public static void setScoreToZero(){
        SCORE = 0;
        scoreField.setText(String.valueOf(0));
    }

}
