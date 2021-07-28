package blood.bank.management;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
class mainFrame{
    String password;
    mainFrame(){
       JFrame frame=new JFrame("New Bud Blood Bank");
       ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Desktop\\Java Files\\Blood Bank Management\\images\\blood bank front.png");
       Image image=background.getImage();
       image.getScaledInstance(500,320, 0);
       frame.setContentPane(new ImagePanel(image));
       frame.setSize(900, 547);
       JPanel panel=new JPanel();
       JLabel label=new JLabel("Database Password:");
       Font font=new Font("Arial",Font.BOLD,18);
       panel.setBackground(Color.WHITE);
       panel.setBounds(210, 250, 200, 30);
       JPasswordField pass=new JPasswordField(30);
       pass.setBackground(Color.WHITE);
       pass.setEchoChar('*');
       pass.setBounds(420, 250, 200, 30);
       JButton setup=new JButton("Set Up");
       JButton refresh=new JButton("Refresh");
       JButton login=new JButton("Log In");
       login.setForeground(Color.blue);
       login.setBounds(380, 330, 80, 30);
       setup.setBounds(335, 460, 80, 30);
       setup.setForeground(Color.red);
       refresh.setBounds(435,460,80,30);
       refresh.setForeground(Color.red);
       label.setForeground(Color.BLUE);
       label.setFont(font);
       login.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               char[] op=pass.getPassword();
               password=new String(op);
               frame.dispose();
               JOptionPane.showMessageDialog(null,"Server Connected");
               new homePage(password);
           }
       });
       setup.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               int key=0;
               char[] op=pass.getPassword();
               password=new String(op);
               new setDatabase(password,key);
               frame.dispose();
           }
       });
       refresh.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               int key=1;
               char[] op=pass.getPassword();
               password=new String(op);
               new setDatabase(password,key);
               frame.dispose();
           }
       });
       panel.add(label);
       frame.add(panel);
       frame.add(setup);
       frame.add(setup);
       frame.add(refresh);
       frame.add(pass);
       frame.add(login);
       frame.setVisible(true);
       frame.setLayout(null);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
class setDatabase{
    String password;
    int num;
    setDatabase(String password,int key){
        String url="jdbc:mysql://localhost:3306";
        String user="root";
        this.password=password;
        String sql;
        try(Connection conn = DriverManager.getConnection(url,user,password);
         Statement stmt = conn.createStatement();
      ) {
           if(key==1){
               sql="drop database blood_bank";
               stmt.executeUpdate(sql);
               num=key;
           }
           sql ="Create Database blood_bank";
           stmt.executeUpdate(sql);	  
      } catch (SQLException e) {
         e.printStackTrace();
      }
      url="jdbc:mysql://localhost:3306/blood_bank";
      try(Connection conn = DriverManager.getConnection(url,user,password);
         Statement stmt = conn.createStatement();
      ) {
          sql="create table Donor "
                  + "(Name varchar(30),"
                  + "Age int,"
                  + "Sex char(1),"
                  + "Blood_Group varchar(30),"
                  + "Contact_No int(20),"
                  + "EMail varchar(30),"
                  + "Address varchar(30),"
                  + "Native varchar(30),"
                  + "Donated_Blood int);";
          stmt.executeUpdate(sql);
          sql="create table Collector "
                  + "(Name varchar(30),"
                  + "Age int,"
                  + "Sex char(1),"
                  + "Blood_Group varchar(30),"
                  + "Contact_No int(20),"
                  + "EMail varchar(30),"
                  + "Address varchar(30),"
                  + "Native varchar(30));";
          stmt.executeUpdate(sql);
          sql="create table accounts "
                  + "(Name varchar(30),"
                  + "Age int,"
                  + "Password varchar(30),"
                  + "Sex char(1),"
                  + "Blood_Group varchar(30),"
                  + "Contact_No int(20),"
                  + "EMail varchar(30),"
                  + "Address varchar(30));";
          stmt.executeUpdate(sql);
          sql="Create table Blood_Storage "
                  + "(Blood_Group varchar(10),"
                  + "Available int);";
          stmt.executeUpdate(sql);
          sql="insert into Blood_Storage "
                  + "values "
                  + "('A +ve',"+0+"),"
                  + "('B +ve',"+0+"),"  
                  + "('AB +ve',"+0+"),"
                  + "('O +ve',"+0+"),"
                  + "('A -ve',"+0+"),"
                  + "('B -ve',"+0+"),"
                  + "('AB -ve',"+0+"),"
                  + "('O -ve',"+0+");";
          stmt.executeUpdate(sql);
         JOptionPane.showMessageDialog(null,"Server Connected");
         new homePage(password);
      } catch (SQLException e) {
          if(num==1){
              JOptionPane.showMessageDialog(null,"Server Not Connected");
          }
          else{
              JOptionPane.showMessageDialog(null,"Didn't Setup yet");
          }
         e.printStackTrace();
      } 
    }
}
public class BloodBankManagement {
    public static void main(String[] args) {
        new mainFrame();
    }  
}
