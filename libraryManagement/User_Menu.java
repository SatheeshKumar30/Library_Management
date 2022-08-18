package libraryManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class User_Menu {


public  void user_menu(String UID) {
    
	Login login_menu=new Login();
	DB_Connection db=new DB_Connection();
    JFrame main=new JFrame("User Functions"); //Give dialog box name as User functions
    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box
   
    JButton view_but=new JButton("View Books");//creating instance of JButton  
    view_but.setBounds(20,20,120,25);//x axis, y axis, width, height 
    view_but.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
             
            JFrame f = new JFrame("Books Available"); //View books stored in database
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             
            Connection connection = db.connect();
            String sql="select * from BOOKS"; //Retreive data from database
            try {
                Statement stmt = connection.createStatement(); //connect to database
                 stmt.executeUpdate("USE LIBRARY"); // use librabry
                stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=stmt.executeQuery(sql);
                
                main.dispose();
              
                
                
                JTable book_list= new JTable(); //show data in table format
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                  
                JButton back=new JButton("Back");
                back.setBounds(660, 320,120, 25);
                back.addActionListener(new ActionListener() {
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					user_menu(UID);
    					f.dispose();
    				}
    			});
                
                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
                f.add(back);
                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of view books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                
                 JOptionPane.showMessageDialog(null, e1);
            }               
             
    }
    }
    );
     
    JButton my_book=new JButton("My Books");//creating instance of JButton  
    my_book.setBounds(150,20,120,25);//x axis, y axis, width, height 
    my_book.addActionListener(new ActionListener() { //Perform action
        public void actionPerformed(ActionEvent e){
             
               
            JFrame f = new JFrame("My Books"); //View books issued by user
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            int UID_int = Integer.parseInt(UID); //Pass user ID
 
           
            Connection connection = db.connect(); //connect to database
            //retrieve data
            try {
            String sql="select distinct issued.IID,issued.BID,issued.ISSUED_DATE,issued.RETURN_DATE,issued.PERIOD,issued.FINE,books.bname,books.genre,books.price from issued,books " + "where ((issued.uid=" + UID_int + ") and (books.bid in (select bid from issued where issued.uid="+UID_int+"))) group by iid";

            
            JButton back=new JButton("Back");
            back.setBounds(660, 320,120, 25);
            back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					user_menu(UID);
					f.dispose();
				}
			});
            
           
                Statement stmt = connection.createStatement();
                
                 stmt.executeUpdate("USE LIBRARY");
                stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                
                ResultSet rs=stmt.executeQuery(sql);
                
                main.dispose();
                
                
                
                JTable book_list= new JTable(); //store data in table format
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                //enable scroll bar
                JScrollPane scrollPane = new JScrollPane(book_list);
                
                f.add(back);
                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of my books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                
                 JOptionPane.showMessageDialog(null, e1);
            }               
                 
    }
    }
    );
     
    JButton logout=new JButton("Log out"); //creating instance of JButton to return books
    logout.setBounds(20,60,120,25);  
    logout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        	main.dispose();  
        	login_menu.login();
              
                       
    }
    });
    main.add(logout); 
    main.add(my_book); //add my books
    main.add(view_but); // add view books
    main.setSize(300,200);//400 width and 500 height  
    main.setLayout(null);//using no layout managers  
    main.setVisible(true);//making the frame visible 
    main.setLocationRelativeTo(null);
    }
}