package blood.bank.management;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
class Registration{
    static JComboBox address,bloodGroup;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8,l9,l10,l11;  
    JTextField tf1, tf2, tf5, tf6, tf7,tf8,tf9,tf11;  
    JButton btn1, btn2,btn3;  
    JPasswordField p1, p2; 
    String name,pass,blood,mail,nat,add;
    char sex;
    int contact,age;
    Registration(String datapass,int type)  
    {  
        JFrame frame=new JFrame("New Bud Blood Bank");
        frame.setVisible(true);  
        frame.setSize(600, 700);  
        frame.setLayout(null);  
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String s1[] = {"Select Blood Group","A +ve","B +ve","AB +ve","O +ve","A -ve","B -ve","AB -ve","O -ve"};
        bloodGroup=new JComboBox(s1);
         bloodGroup.addItemListener(new ItemListener(){
             public void itemStateChanged(ItemEvent e)
              {
                  if (e.getSource() == bloodGroup) {
                  blood=bloodGroup.getSelectedItem().toString();
              }
            }
         });
         JPanel o=new JPanel();
         JPanel p=new JPanel();
         String s2[] = {"Select Your Address","Dhaka","Chittagong","Rajshahi","Khulna","Barishal","Sylhet","Rongpur","Comilla",
                          "Mymensingh"};
        address=new JComboBox(s2);
         address.addItemListener(new ItemListener(){
             public void itemStateChanged(ItemEvent e)
              {
                  if (e.getSource() == address) {
                  add=address.getSelectedItem().toString();
              }
            }
         });
        o.add(address);
        p.add(bloodGroup);
        JCheckBox male = new JCheckBox("Male");  
        JCheckBox female = new JCheckBox("Female");  
        JCheckBox other = new JCheckBox("Other");  
        l1 = new JLabel("New Account Registration:");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Name:");  
        l3 = new JLabel("Sex:");  
        l4 = new JLabel("Create Passowrd:");  
        l5 = new JLabel("Confirm Password:");  
        l6 = new JLabel("Address:");  
        l7 = new JLabel("Native :");  
        l8 = new JLabel("Phone No:"); 
        l9=new JLabel("Email:");
        l10=new JLabel("Blood Group:");
        l11=new JLabel("Age:");
        tf11=new JTextField();
        tf1 = new JTextField();  
        tf2 = new JTextField();  
        p1 = new JPasswordField();  
        p2 = new JPasswordField();    
        tf6 = new JTextField();  
        tf7 = new JTextField();  
        tf8=new JTextField();
        btn1 = new JButton("Submit");  
        btn2 = new JButton("Clear"); 
        btn3=new JButton("Back");
        l1.setBounds(100, 30, 400, 30);  
        l2.setBounds(80, 70, 200, 30);  
        l3.setBounds(80, 110, 200, 30);  
        l4.setBounds(80, 150, 200, 30);  
        l5.setBounds(80, 190, 200, 30);  
        l6.setBounds(80, 230, 200, 30); 
        o.setBounds(300,230,200,30);
        l7.setBounds(80, 270, 200, 30);  
        l8.setBounds(80, 310, 200, 30); 
        l9.setBounds(80,350,200,30);
        l10.setBounds(80,430,200,30);
        l11.setBounds(80,390,200,30);
        tf1.setBounds(300, 70, 200, 30);  
        male.setBounds(300, 110,60, 30);
        female.setBounds(365,110,80,30);
        other.setBounds(450,110,70,30);
        p1.setBounds(300, 150, 200, 30);  
        p2.setBounds(300, 190, 200, 30);   
        tf6.setBounds(300, 270, 200, 30);  
        tf7.setBounds(300, 310, 200, 30); 
        tf8.setBounds(300,350,200,30);
        tf11.setBounds(300,390,200,30);
        p.setBounds(300,430,200,30);
        btn1.setBounds(200, 550, 100, 30);  
        btn2.setBounds(80, 550, 100, 30); 
        btn3.setBounds(397, 550, 100, 30);
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf1.setText("");
                tf6.setText("");
                tf7.setText("");
                tf8.setText("");
                tf11.setText("");
            }
        });
         male.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {                 
                if(e.getStateChange()==1){
                  sex='M';  
                }    
             }    
          });
         female.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {                 
                if(e.getStateChange()==1){
                  sex='F';  
                }    
             }    
          });
         other.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {                 
                if(e.getStateChange()==1){
                  sex='O';  
                }    
             }    
          });
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                char[] s3 = p1.getPassword();  
                char[] s4 = p2.getPassword();   
                String pword = new String(s3);  
                String passwordCheck = new String(s4);
                if(!(pword.equals(passwordCheck))){
                    JOptionPane.showMessageDialog(null,"Password didn't Match");
                }
               else{
                    pass=pword;
                    name = tf1.getText();      
                    nat = tf6.getText();  
                    String phone = tf7.getText(); 
                    mail=tf8.getText();
                    contact=Integer.valueOf(phone);
                    age=Integer.valueOf(tf11.getText().toString());
                    String url="jdbc:mysql://localhost:3306/blood_bank";
                    String password=datapass;
                    String user="root";
                    String sql;
                    try(Connection conn = DriverManager.getConnection(url,user,password);
                        Statement stmt = conn.createStatement();
                        ) {
                          sql="insert into accounts "
                                + "values "
                                 + "('"+name+"',"+age+",'"+pass+"','"+sex+"',"
                                  + "'"+blood+"',"+contact+",'"+mail+"','"+add+"');";
                          System.out.println(sql);
                          stmt.executeUpdate(sql);
                          if(type==1){
                              sql="insert into Donor "
                                   + "values "
                                   + "('"+name+"',"+age+",'"+sex+"',"
                                  + "'"+blood+"',"+contact+",'"+mail+"','"+add+"','"+nat+"',"+0+");";
                               stmt.executeUpdate(sql);
                                    
                          }
                          else{
                              sql="insert into Collector "
                                + "values "
                                 + "('"+name+"',"+age+",'"+sex+"',"
                                  + "'"+blood+"',"+contact+",'"+mail+"','"+add+"','"+nat+"');";
                              stmt.executeUpdate(sql);
                          }
                          JOptionPane.showMessageDialog(null,"Profile Created.");
                          frame.dispose();
                        }catch (Exception x) {
                          JOptionPane.showMessageDialog(null,"Error 404 not found");
                          x.printStackTrace();
                        }
                }
            }
        });
        frame.add(l1);  
        frame.add(l2);  
        frame.add(tf1);  
        frame.add(l3);   
        frame.add(male);
        frame.add(female);
        frame.add(other);
        frame.add(l4);  
        frame.add(p1);  
        frame.add(l5);  
        frame.add(p2);  
        frame.add(l6);  
        frame.add(o);
        frame.add(p);
        frame.add(l7);  
        frame.add(tf6);  
        frame.add(l8);  
        frame.add(tf7);
        frame.add(l9);
        frame.add(tf8);
        frame.add(l10);
        frame.add(l11);
        frame.add(tf11);
        frame.add(btn1);  
        frame.add(btn2); 
        frame.add(btn3);
        frame.revalidate();
    }  
}
class LogIn{
    String nameCont,passCont;
    LogIn(String datapass,int type){
        JFrame frame=new JFrame("New Bud Blood Bank");
        JTextField field=new JTextField(30);
        field.setBounds(160, 100, 150, 20);
        JButton login,back,newAcc;
        login=new JButton("Log in");
        back=new JButton("Back");
        newAcc=new JButton("Create new Profile");
        login.setBounds(120,170,70,25);
        login.setBackground(Color.cyan);
        back.setBounds(180,220,70,25);
        back.setBackground(Color.cyan);
        newAcc.setBounds(200, 170, 150, 25);
        newAcc.setBackground(Color.cyan);
        JPasswordField pass=new JPasswordField(30);
         pass.setBounds(160, 130, 150, 20);
         pass.setEchoChar('*');
        frame.setSize(450,300);
        Font font=new Font("Arial",Font.BOLD,13);
        JLabel labeluser=new JLabel("Name:");
        labeluser.setFont(font);
        JLabel labelpass=new JLabel("Password:");
        labelpass.setFont(font);
        labeluser.setBounds(80,100,80, 20);
        labelpass.setBounds(80, 130, 80, 20);
        frame.add(labeluser);
        frame.add(back);
        frame.add(newAcc);
        frame.add(login);
        frame.add(pass);
        frame.add(labelpass);
        frame.add(field);
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nameCont=field.getText();
                char[] op=pass.getPassword();
                passCont=new String(op);
                String url="jdbc:mysql://localhost:3306/blood_bank";
                    String password=datapass;
                    String user="root";
                    String sql;
                    String n="default",p="default";
                    try(Connection conn = DriverManager.getConnection(url,user,password);
                        Statement stmt = conn.createStatement();
                        ) {
                          ResultSet rs;
                          sql="select Name,password from accounts where Name='"+
                                  nameCont+"'and Password='"+passCont+"'";
                          rs=stmt.executeQuery(sql);
                          while(rs.next()){
                              n=rs.getString("Name");
                              p=rs.getString("password");
                          }
                          if(nameCont.equals(n) && passCont.equals(p)){
                              JOptionPane.showMessageDialog(null,"Log in Successful.");
                              new controlPanel(datapass,nameCont,passCont,type);
                              frame.dispose();
                          }
                          else{
                              JOptionPane.showMessageDialog(null,"No Profile Found!");    
                          }
                        }catch (Exception x) {
                          JOptionPane.showMessageDialog(null,"Error 404 not found");
                          x.printStackTrace();
                        }
            }
        });
        newAcc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Registration(datapass,type);
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class About{
    About(){
        JFrame frame=new JFrame("City of Brittia");
       ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Desktop\\Java Files\\Blood Bank Management\\images\\aboutMain.png");
       Image image=background.getImage();
       image.getScaledInstance(500,320, 0);
       frame.setContentPane(new ImagePanel(image));
       JButton back=new JButton("Back");
       back.setBounds(1100,610,80,30);
       back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
       frame.add(back);
       frame.setSize(1233,706);
       frame.setVisible(true);
       frame.setLayout(null);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
class homePanel{
    homePanel(String datapass){
       JFrame frame=new JFrame("New Bud Blood Bank");
       ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Desktop\\Java Files\\Blood Bank Management\\images\\Choose.png");
       Image image=background.getImage();
       image.getScaledInstance(500,320, 0);
       frame.setContentPane(new ImagePanel(image));
       JButton about=new JButton("About Us");
       about.setForeground(Color.blue);
       about.setBounds(710,470,100,30);
       Font font2=new Font("Arial",Font.BOLD,16);
       JButton Doner=new JButton("Donor");
       Doner.setBounds(200,250,120,50);
       Doner.setForeground(Color.red);
       Doner.setFont(font2);
       JButton collector=new JButton("Collector");
       collector.setBounds(596,250,120,50);
       collector.setForeground(Color.red);
       collector.setFont(font2);
       Doner.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              new LogIn(datapass,1);
          }
       });
       collector.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              new LogIn(datapass,2);
          }
       });
       about.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               new About();
           }
       });
       frame.add(about);
       frame.add(Doner);
       frame.add(collector);
       frame.setSize(926, 550);
       frame.setVisible(true);
       frame.setLayout(null);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
public class homePage {
    homePage(String datapass){
        new homePanel(datapass);
    }
}
