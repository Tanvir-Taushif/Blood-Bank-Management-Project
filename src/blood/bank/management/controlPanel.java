package blood.bank.management;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
class donorFront{
    String group,a;
    int num;
    donorFront(String datapass,String name,String pass){
       JFrame frame=new JFrame("New Bud Blood Bank");
       ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Desktop\\Java Files\\Blood Bank Management\\images\\Donor_Panel.png");
       Image image=background.getImage();
       image.getScaledInstance(500,320, 0);
       frame.setContentPane(new ImagePanel(image));
       String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   sql="select Age,Contact_No,Blood_Group from accounts where Name='"+name+"' and Password='"+pass+"';";
                   ResultSet rs=stmt.executeQuery(sql);
                   while(rs.next()){
                       a=String.valueOf(rs.getInt("Age"));
                       num=rs.getInt("Contact_No");
                       group=rs.getString("Blood_Group");
                   }
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
        JLabel welcome=new JLabel("Welcome "+name);
       welcome.setBounds(90,80, 200, 30);
       JLabel gr=new JLabel("Blood Group : "+group);
       gr.setBounds(70,200,250,30);
       JLabel age=new JLabel("Age : "+a);
       age.setBounds(70,250,150,30);
       age.setForeground(Color.black);
       gr.setForeground(Color.black);
       JLabel don=new JLabel("Total Donation(Bags) :");
       don.setBounds(70,300,180,30);
       don.setForeground(Color.black);
       Font font=new Font("Arial",Font.BOLD,17);
       Font font2=new Font("Arial",Font.BOLD,16);
       Font font3=new Font("Arial",Font.BOLD,18);
       gr.setFont(font2);
       don.setFont(font2);
       age.setFont(font2);
       welcome.setFont(font);
       welcome.setForeground(Color.black);
       JTextField f1=new JTextField();
       f1.setBounds(255,300,80,30);
       JButton view1=new JButton("View");
       view1.setBounds(160, 380, 70, 30);
       JButton donate=new JButton("Donate Blood");
       donate.setBounds(530,370,150,40);
       donate.setForeground(Color.red);
       JLabel lab=new JLabel("Availabe Blood of Type "+group+" :");
       lab.setForeground(Color.red);
       lab.setFont(font3);
       lab.setBounds(360,250,400,30);
       JTextField f2=new JTextField();
       f2.setBounds(650,250,90,30);
       JButton view2=new JButton("View");
       view2.setBounds(780,250,80, 30);
       frame.setSize(930,550);
       JButton back=new JButton("Back");
       back.setBounds(565, 450, 80, 30);
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               frame.dispose();
           }
       });
       view1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   sql="select  Donated_Blood from donor where Contact_No="+num+";";
                   ResultSet rs=stmt.executeQuery(sql);
                   int d_blood=0;
                   while(rs.next()){
                      d_blood=rs.getInt("Donated_Blood");
                   }
                   f1.setText(String.valueOf(d_blood));
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       view2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   sql="select  Available from blood_Storage where Blood_Group='"+group+"';";
                   ResultSet rs=stmt.executeQuery(sql);
                   int a_blood=0;
                   while(rs.next()){
                      a_blood=rs.getInt("Available");
                   }
                   f2.setText(String.valueOf(a_blood));
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       donate.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   sql="select  Available from blood_Storage where Blood_Group='"+group+"';";
                   ResultSet rs=stmt.executeQuery(sql);
                   int blood_stor=0;
                   while(rs.next()){
                      blood_stor=rs.getInt("Available");
                   }
                   sql="select  Donated_Blood from donor where Contact_No="+num+";";
                   rs=stmt.executeQuery(sql);
                   int blood_don=0;
                   while(rs.next()){
                      blood_don=rs.getInt("Donated_Blood");
                   }
                   blood_stor++;
                   blood_don++;
                   sql="update donor "
                               + "set Donated_Blood="+blood_don+
                               " where Contact_No="+num+";";
                   stmt.executeUpdate(sql);
                   sql="update Blood_Storage "
                               + "set Available="+blood_stor+
                               " where Blood_Group='"+group+"';";
                   stmt.executeUpdate(sql);
                   JOptionPane.showMessageDialog(null,"One Bag Blood Donated");
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       frame.add(lab);
       frame.add(f2);
       frame.add(view2);
       frame.add(welcome);
       frame.add(gr);
       frame.add(f1);
       frame.add(age);
       frame.add(don);
       frame.add(view1);
       frame.add(donate);
       frame.add(back);
       frame.revalidate();
       frame.setLayout(null);
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
class Collectorfront{
    String sql,blood,place,group;
    int banknum;
    static JComboBox add,occ,bg;
    JTable jt2;
    int pop_num,tourist_num;
    JScrollPane sp;
    Collectorfront(String datapass,String name,String pass,int type){
        JFrame frame=new JFrame("New Bud Blood Bank");
       ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Desktop\\Java Files\\Blood Bank Management\\images\\collector.png");
       Image image=background.getImage();
       image.getScaledInstance(500,320, 0);
       frame.setContentPane(new ImagePanel(image));
       Font font=new Font("Arial",Font.BOLD,17);
       String s1[] = {"Select Blood Group","A +ve","B +ve","AB +ve","O +ve","A -ve","B -ve","AB -ve","O -ve","Any"};
       String s3[] = {"Select Blood Group","A +ve","B +ve","AB +ve","O +ve","A -ve","B -ve","AB -ve","O -ve"};
        occ=new JComboBox(s1);
         occ.addItemListener(new ItemListener(){
             public void itemStateChanged(ItemEvent e)
              {
                  if (e.getSource() == occ) {
                  blood=occ.getSelectedItem().toString();
              }
            }
         });
         bg=new JComboBox(s3);
         bg.addItemListener(new ItemListener(){
             public void itemStateChanged(ItemEvent e)
              {
                  if (e.getSource() == bg) {
                  group=bg.getSelectedItem().toString();
              }
            }
         });
         JPanel o=new JPanel();
         JPanel p=new JPanel();
         JPanel g=new JPanel();
         String s2[] = {"Select Your Address","Dhaka","Chittagong","Rajshahi","Khulna","Barishal","Sylhet","Rongpur","Comilla",
                          "Mymensingh","Any"};
        add=new JComboBox(s2);
         add.addItemListener(new ItemListener(){
             public void itemStateChanged(ItemEvent e)
              {
                  if (e.getSource() == add) {
                  place=add.getSelectedItem().toString();
              }
            }
         });
        o.add(add);
        p.add(occ);
        g.add(bg);
        String[][] tableData=new String[200][5];
        String column[]={"Name","Blood Group","Age","Area","Contact No"};
        Font fontf=new Font("Arial",Font.BOLD,25);
       JLabel welcome=new JLabel("Welcome "+name);
       welcome.setBounds(700,50, 200, 30);
       welcome.setFont(font);
       welcome.setForeground(Color.white);
       JButton back=new JButton("Back");
       back.setBounds(750,470, 100, 30);
       JLabel sr=new JLabel("Search Donor ");
       JLabel bloodStorage=new JLabel("Search Blood Storage");
       JButton search=new JButton("Search");
       JButton refresh=new JButton("Refresh");
       JButton search2=new JButton("Search");
       JButton collect=new JButton("Collect");
       sr.setForeground(Color.white);
       sr.setFont(font);
       sr.setBounds(170,100,200,30);
       bloodStorage.setBounds(700,100,200,30);
       search2.setBounds(750,370,100,30);
       collect.setBounds(750,410,100,30);
       bloodStorage.setFont(font);
       bloodStorage.setForeground(Color.white);
       JLabel numDonor=new JLabel("Availabe Donor :");
       JLabel numBlood=new JLabel("Availabe Blood(Bags):");
       numDonor.setForeground(Color.white);
       numBlood.setForeground(Color.white);
       numDonor.setBounds(700,275,150,20);
       numBlood.setBounds(700,250,150,20);
       o.setBounds(170,150,170,32);
       p.setBounds(380,150,170,32);
       g.setBounds(710, 150, 170, 32);
       search.setBounds(170,470,100,30);
       refresh.setBounds(30,250,100,30);
       JTextField f1=new JTextField();
       JTextField f2=new JTextField();
       f1.setBounds(840,275,50,20);
       f2.setBounds(840,250,50,20);
       search.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
        String url="jdbc:mysql://localhost:3306/blood_bank";
       String password=datapass;
       String user="root";
       try(Connection conn = DriverManager.getConnection(url,user,password);
         Statement stmt = conn.createStatement();
        ) {
           int x=0;
           int nb;
           if(blood.equals("Any") && place.equals("Any")){
               sql="select Name,Blood_Group,Age,Address,Contact_No from donor;";
           }
           else if(blood.equals("Any")){
                sql="select Name,Blood_Group,Age,Address,Contact_No from donor where Address='"+place+"';";
           }
           else if(place.equals("Any")){
                sql="select Name,Blood_Group,Age,Address,Contact_No from donor where Blood_Group='"+blood+"';";
           }
           else {
               sql="select Name,Blood_Group,Age,Address,Contact_No from donor where Blood_Group='"+blood+"' and Address='"+place+"';";
           }
           ResultSet rs=stmt.executeQuery(sql);
           while(rs.next()){
               tableData[x][0]=rs.getString("Name");
               tableData[x][1]=rs.getString("Blood_Group");
               tableData[x][2]=String.valueOf(rs.getInt("Age"));
               tableData[x][3]=rs.getString("Address");
               tableData[x][4]="0"+String.valueOf(rs.getInt("Contact_No"));
               x++;
           }
               jt2=new JTable(tableData,column);
               jt2.setBounds(170,200,480,200);
               sp=new JScrollPane(jt2);    
               sp.setBounds(170, 200, 480, 200);
               frame.add(sp);
           }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       search2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   sql="select Available from Blood_Storage where Blood_Group='"+group+"';";
                   ResultSet rs=stmt.executeQuery(sql);
                   int flag=0;
                   while(rs.next()){
                       flag=rs.getInt("Available");
                   }
                   sql="select *from Donor where Blood_Group='"+group+"';";
                   rs=stmt.executeQuery(sql);
                   int blFlag=0;
                   while(rs.next()){
                       blFlag++;
                   }
                  f1.setText(String.valueOf(blFlag));
                  f2.setText(String.valueOf(flag));
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       collect.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               String url="jdbc:mysql://localhost:3306/blood_bank";
               String password=datapass;
               String user="root",sql="";
               try(Connection conn = DriverManager.getConnection(url,user,password);
                 Statement stmt = conn.createStatement();) {
                   int avBlood=0;
                   sql="select Available from Blood_Storage where Blood_Group='"+group+"';";
                   ResultSet rs=stmt.executeQuery(sql);
                   while(rs.next()){
                       avBlood=rs.getInt("Available");
                   }
                   if(avBlood==0){
                       JOptionPane.showMessageDialog(null,"No blood available for the given type");
                   }
                   else{
                       sql="update Blood_Storage "
                               + "set Available="+(avBlood-1)+
                               " where Blood_Group='"+group+"';";
                       stmt.executeUpdate(sql);
                       JOptionPane.showMessageDialog(null,"One Bag Blood Collected");
                   }
               }catch (Exception x) {
                JOptionPane.showMessageDialog(null,"Error 404 not found");
                x.printStackTrace();
           }
           }
       });
       refresh.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               frame.dispose();
               new Collectorfront(datapass,name,pass,type);
           }
       });
       back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               frame.dispose();
           }
       });
       frame.add(f1);
       frame.add(f2);
       frame.add(o);
       frame.add(p);
       frame.add(g);
       frame.add(search);
       frame.add(search2);
       frame.add(collect);
       frame.add(bloodStorage);
       frame.add(numDonor);
       frame.add(numBlood);
       frame.add(refresh);
       frame.add(welcome);
       frame.add(back);
       frame.add(sr);
       frame.revalidate();
       frame.setSize(929,549);
       frame.setVisible(true);
       frame.setLayout(null);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
public class controlPanel {
    controlPanel(String datapass,String nameCont,String passCont,int type){
        if(type==1){
            new donorFront(datapass,nameCont,passCont);
        }
        else{
            new Collectorfront(datapass,nameCont,passCont,type);
        }
    }
}
