package libraryManagement;



import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import net.proteanit.sql.DbUtils;

public class Admin_Menu  {

	
	public static class ex{
        public static int days=0;
            }

	Create_Library_Database db_create=new Create_Library_Database();
	DB_Connection db=new DB_Connection();
	Login login_menu=new Login();
	
	
public  void admin_menu() {
    
    
    JFrame main=new JFrame("Admin Functions"); //Give dialog box name as admin functions
     
    JButton create_but=new JButton("Create/Reset");//creating instance of JButton to create or reset database
    create_but.setBounds(450,60,120,25); 
    create_but.addActionListener(new ActionListener() { 

        public void actionPerformed(ActionEvent e){
             
            db_create.create(); //Call create function
            JOptionPane.showMessageDialog(null,"Database Created/Reset!"); //Open a dialog box and display the message
             
        }
    });
     
     
    JButton view_but=new JButton("View Books");//creating instance of JButton to view books
    view_but.setBounds(20,20,120,25);
    view_but.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
           
        	JFrame f = new JFrame("Books Available"); 
             
        	 JButton back=new JButton("Back");
             back.setBounds(660, 320,120, 25);
             back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					admin_menu();
					f.dispose();
				}
			});
             
            Connection connection = db.connect(); //connect to database
            String sql="select * from BOOKS"; //select all books 
            try {
                Statement stmt = connection.createStatement();
                 stmt.executeUpdate("USE LIBRARY"); //use database
                stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=stmt.executeQuery(sql); 
                main.dispose();
//                rs.beforeFirst();
                
              
                	
                JTable book_list= new JTable(); //view data in table format
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
               
               
                JScrollPane scrollPane = new JScrollPane(book_list);  //mention scroll bar
               scrollPane.createHorizontalScrollBar();
                f.add(back);
                f.add(scrollPane); //add scroll pane
                f.setSize(800, 400); //set size for frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);
                }
             catch (SQLException e1) {
                 JOptionPane.showMessageDialog(null, e1);
            }               
             
        }
    });
     
    
    JButton users_but=new JButton("View Users");//creating instance of JButton to view users
    users_but.setBounds(150,20,120,25); 
    users_but.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){

        		JFrame f = new JFrame("Users List");
                
                JButton back=new JButton("Back");
                back.setBounds(660, 320,120, 25); 
                back.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						admin_menu();
						f.dispose();
					}
				});
                 
                Connection connection = db.connect();
                String sql="select * from users"; //retrieve all users
                try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY"); //use database
                    stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs=stmt.executeQuery(sql);
                    
                    main.dispose();
                    JTable book_list= new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                    //mention scroll bar
                    
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    
                    f.add(back);
                    f.add(scrollPane); //add scrollpane
                    f.setSize(800, 400); //set size for frame
                    f.setVisible(true);
                   
                    f.setLocationRelativeTo(null);
                      
                } catch (SQLException e1) {
                    
                     JOptionPane.showMessageDialog(null, e1);
                }       
                 
                 
        	}
        });  
     
    
    JButton issued_but=new JButton("View Issued Books");//creating instance of JButton to view the issued books
    issued_but.setBounds(280,20,160,25);
    issued_but.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             
        	JFrame f = new JFrame("Users List");
               
                JButton back=new JButton("Back");
                back.setBounds(660, 320,120, 25);
                back.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						admin_menu();
						f.dispose();
					}
				});
                 
                Connection connection = db.connect();
                String sql="select * from issued";
                try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                    stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs=stmt.executeQuery(sql);
                    
                    main.dispose();
                    
                   
                    JTable book_list= new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                     
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    f.add(back);
                    f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                  
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    
                     JOptionPane.showMessageDialog(null, e1);
                }       
                             
        	}
        });
     
    
     
    JButton add_user=new JButton("Add User"); //creating instance of JButton to add users
    add_user.setBounds(20,60,120,25);  
    add_user.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter User Details"); //Frame to enter user details
//                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1,l2;  
                l1=new JLabel("Username");  //label 1 for user name
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Password");  //label 2 for password
                l2.setBounds(30,50, 100,30); 
                 
                //set text field for user name 
                JTextField F_user = new JTextField();
                F_user.setBounds(110, 15, 200, 30);
                 
                //set text field for password
                JPasswordField F_pass=new JPasswordField();
                F_pass.setBounds(110, 50, 200, 30);
                //set radio button for admin
                JRadioButton a1 = new JRadioButton("Admin");
                a1.setBounds(55, 80, 200,30);
                //set radio button for user
                JRadioButton a2 = new JRadioButton("User");
                a2.setBounds(130, 80, 200,30);
                //add radio buttons
                ButtonGroup bg=new ButtonGroup();    
                bg.add(a1);
                bg.add(a2);  
                 
                                 
                JButton create_but=new JButton("Create");//creating instance of JButton for Create 
                create_but.setBounds(130,130,80,25);//x axis, y axis, width, height 
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                     
                    String username = F_user.getText();
                    @SuppressWarnings("deprecation")
					String password = F_pass.getText();
                    Boolean admin = false;
                     
                    if(a1.isSelected()) {
                        admin=true;
                    }
                     
                    Connection connection = db.connect();
                     
                    try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                     stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,ADMIN) VALUES ('"+username+"','"+password+"',"+admin+")");
                     JOptionPane.showMessageDialog(null,"User added!");
                     g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                     
                 
                    g.add(create_but);
                    g.add(a2);
                    g.add(a1);
                    g.add(l1);
                    g.add(l2);
                    g.add(F_user);
                    g.add(F_pass);
                    g.setSize(350,200);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                 
                 
    }
    });
         
     
    JButton add_book=new JButton("Add Book"); //creating instance of JButton for adding books
    add_book.setBounds(150,60,120,25);  
    add_book.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){

               
        	//set frame wot enter book details
                JFrame g = new JFrame("Enter Book Details");
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Book Name");  //label 1 for book name
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Genre");  //label 2 for genre
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Price");  //label 3 for price
                l3.setBounds(30,90, 100,30); 
                
                l4=new JLabel("Number of Book");
                l4.setBounds(30, 140,100, 30);
                //set text field for book name
                
                JTextField F_bname = new JTextField();
                F_bname.setBounds(140, 15, 200, 30);
                 
                //set text field for genre 
                JTextField F_genre=new JTextField();
                F_genre.setBounds(140, 53, 200, 30);
                //set text field for price
                JTextField F_price=new JTextField();
                F_price.setBounds(140, 90, 200, 30);
                
                JTextField F_number=new JTextField();
                F_number.setBounds(140,140, 200, 30);
                 
                JButton create_but=new JButton("Submit");//creating instance of JButton to submit details  
                create_but.setBounds(140,190,80,25);//x axis, y axis, width, height 
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                    // assign the book name, genre, price
                    String bname = F_bname.getText();
                    String genre = F_genre.getText();
                    String price = F_price.getText();
                    String number= F_number.getText();
                    //convert price of integer to int
                    int price_int = Integer.parseInt(price);
                    int number_int = Integer.parseInt(number);
                     
                    Connection connection = db.connect();
                     
                    try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                     stmt.executeUpdate("INSERT INTO BOOKS(BNAME,GENRE,PRICE,NUMBER_OF_BOOKS) VALUES ('"+bname+"','"+genre+"','"+price_int+"','"+number_int+"')");
                     JOptionPane.showMessageDialog(null,"Book added!");
                     g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                                 
                    g.add(l3);
                    g.add(create_but);
                    g.add(l1);
                    g.add(l2);
                    g.add(l4);
                    g.add(F_number);
                    g.add(F_bname);
                    g.add(F_genre);
                    g.add(F_price);
                    g.setSize(450,300);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                             
    }
    });
     
     
    JButton issue_book=new JButton("Issue Book"); //creating instance of JButton to issue books
    issue_book.setBounds(450,20,120,25);  
    issue_book.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
              
        	//enter details
                JFrame g = new JFrame("Enter Details");
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //create labels
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Book ID(BID)");  // Label 1 for Book ID
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("User ID(UID)");  //Label 2 for user ID
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Period(days)");  //Label 3 for period
                l3.setBounds(30,90, 100,30); 
                 
                l4=new JLabel("Issued Date(DD-MM-YYYY)");  //Label 4 for issue date
                l4.setBounds(30,127, 150,30); 
                 
                JTextField F_bid = new JTextField();
                F_bid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_uid=new JTextField();
                F_uid.setBounds(110, 53, 200, 30);
                 
                JTextField F_period=new JTextField();
                F_period.setBounds(110, 90, 200, 30);
                 
                JTextField F_issue=new JTextField();
                F_issue.setBounds(180, 130, 130, 30);   
                
               
                 
                JButton create_but=new JButton("Submit");//creating instance of JButton  
                create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                     
                    String uid = F_uid.getText();
                    String bid = F_bid.getText();
                    String period = F_period.getText();
                    String issued_date = F_issue.getText();
 
                    int period_int = Integer.parseInt(period);
                     
                    Connection connection = db.connect();
                   
                     
                    try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                     String check_uid="SELECT UID FROM users WHERE UID="+uid+"";
                     String check_bid="SELECT BID FROM books WHERE BID="+bid+"";
                     stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs_check_uid=stmt.executeQuery(check_uid);
                     stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs_chesk_bid=stmt.executeQuery(check_bid);
                     if(rs_check_uid.first() && rs_chesk_bid.first()) {
                     stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs=stmt.executeQuery("select BID,NUMBER_OF_BOOKS from BOOKS");
                    
                    
                     while(rs.next()) {
                    	 String number_of_books=rs.getString("BID");
                    	 if(number_of_books.equals(bid)) {
                    			 stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        		 ResultSet check_book_available=stmt.executeQuery("SELECT `NUMBER_OF_BOOKS` FROM `books` WHERE BID="+number_of_books+"");
                        		 while(check_book_available.next()) {
                        			 String number1=check_book_available.getString("NUMBER_OF_BOOKS");
                            		 int i=Integer.parseInt(number1);
                            		 if(i!=0) {
                            		 i=i-1;
                            		 Statement stmt_update_books = connection.createStatement();
                            		 String sql= " UPDATE books SET NUMBER_OF_BOOKS = "+i+" WHERE BID = "+number_of_books+"";
                            		 
                            		 stmt_update_books.executeUpdate(sql);
                            		 Statement stmt_insert_issued = connection.createStatement();
                            		 
                            		 stmt_insert_issued.executeUpdate("INSERT INTO ISSUED(UID,BID,ISSUED_DATE,PERIOD) VALUES ('"+uid+"','"+bid+"','"+issued_date+"',"+period_int+")");
                            		 JOptionPane.showMessageDialog(null,"Book Issued!");
                                     g.dispose();
                            		 }
                            		 else {
                            			 JOptionPane.showMessageDialog(null,"Book not Available!");
                            			 g.dispose();
                            		 }
                        		 }
                    		 }
                    		
                     }
                    }else {
                     g.dispose();
                     JOptionPane.showMessageDialog(null,"Book invalid number or invalid users");
                     }
                    }
                     
                    catch (SQLException e1) {
                    	
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                     
                 
                    g.add(l3);
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(l2);
                    g.add(F_uid);
                    g.add(F_bid);
                    g.add(F_period);
                    g.add(F_issue);
                    g.setSize(350,250);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                 
                 
    }
    });
     
     
    JButton return_book=new JButton("Return Book"); //creating instance of JButton to return books
    return_book.setBounds(280,60,160,25);  
    return_book.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
              
        	
                JFrame g = new JFrame("Enter Details");
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set labels 
                JLabel l1,l4;  
                l1=new JLabel("Issue ID(IID)");  //Label 1 for Issue ID
                l1.setBounds(30,15, 100,30); 
                
                 
                l4=new JLabel("Return Date(DD-MM-YYYY)");  
                l4.setBounds(30,50, 150,30); 
                 
                JTextField F_iid = new JTextField();
                F_iid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_return=new JTextField();
                F_return.setBounds(180, 50, 130, 30);
             
 
                JButton create_but=new JButton("Return");//creating instance of JButton to mention return date and calculcate fine
                create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){                 
                     
                    String iid = F_iid.getText();
                    String return_date = F_return.getText();
                     
                    Connection connection = db.connect();
                     
                    try {
                    Statement stmt = connection.createStatement();
                     stmt.executeUpdate("USE LIBRARY");
                     //Intialize date1 with NULL value
                     String date1=null;
                     String date2=return_date; //Intialize date2 with return date
                     
                     //select issue date
                     ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE FROM ISSUED WHERE IID="+iid);
                     while (rs.next()) {
                         date1 = rs.getString(1);
                          
                       }
                      
                     try {
                            Date date_1=new SimpleDateFormat("dd-MM-yyyy").parse(date1);
                            Date date_2=new SimpleDateFormat("dd-MM-yyyy").parse(date2);
                            //subtract the dates and store in diff
                            long diff = date_2.getTime() - date_1.getTime();
                            //Convert diff from milliseconds to days
                            ex.days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                             
                             
                        } catch (ParseException e1) {
                            
                            e1.printStackTrace();
                        }
                      
                     
                     //update return date
                     stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE IID="+iid);
                     g.dispose();
                      
 
                     Connection connection1 = db.connect();
                     Statement stmt1 = connection1.createStatement();
                     stmt1.executeUpdate("USE LIBRARY");                
                    ResultSet rs1 = stmt1.executeQuery("SELECT PERIOD FROM ISSUED WHERE IID="+iid); //set period
                    String diff=null; 
                    while (rs1.next()) {
                         diff = rs1.getString(1);
                          
                       }
                    int diff_int = Integer.parseInt(diff);
                    if(ex.days > diff_int) { //If number of days are more than the period then calculcate fine
                         
                        //System.out.println(ex.days);
                    	int f1= ex.days-diff_int;
                        int fine =f1*10; //fine for every day after the period is Rs 10.
                        //update fine in the system
                        stmt1.executeUpdate("UPDATE ISSUED SET FINE="+fine+" WHERE IID="+iid);  
                        String fine_str = ("Fine: Rs. "+fine);
                        JOptionPane.showMessageDialog(null,fine_str);
                         
                    }
 
                     JOptionPane.showMessageDialog(null,"Book Returned!");
                      
                    }
                             
                     
                    catch (SQLException e1) {
                        
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                }); 
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(F_iid);
                    g.add(F_return);
                    g.setSize(350,250);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);              
    }
    });
     
    JButton logout=new JButton("Log out"); //creating instance of JButton to return books
    logout.setBounds(20,100,120,25);  
    logout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
              login_menu.login();
              main.dispose();
                       
    }
    });
    
    JButton delete_book=new JButton("Delete Book");
    delete_book.setBounds(150, 100, 120, 25);
    delete_book.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFrame g = new JFrame("Enter Delete  Details");
            g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //set labels 
            JLabel l1;  
            l1=new JLabel("Book or User ID");  //Label 1 for Issue ID
            l1.setBounds(30,15, 100,30); 
             
            JTextField F_iid = new JTextField();
            F_iid.setBounds(130, 15, 200, 30);
            
            JRadioButton a1 = new JRadioButton("Book");
            a1.setBounds(55, 80, 200,30);
            //set radio button for user
            JRadioButton a2 = new JRadioButton("User");
            a2.setBounds(130, 80, 200,30);
            //add radio buttons
            ButtonGroup bg=new ButtonGroup();    
            bg.add(a1);
            bg.add(a2);
           
            JButton create_but=new JButton("Delete");//creating instance of JButton to mention return date and calculcate fine
            create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
            create_but.addActionListener(new ActionListener() {
                 
                public void actionPerformed(ActionEvent e){                 
                 
                String iid = F_iid.getText();
                
                 
                Connection connection = db.connect();
                 
                try {
                Statement stmt = connection.createStatement();
                 stmt.executeUpdate("USE LIBRARY");
                 
                 if(a1.isSelected()) {
                  
                 //select issue date
                  stmt.executeUpdate("DELETE FROM `books` WHERE BID="+iid);
                 }
                 if(a2.isSelected()) {
                	//select issue date
                     stmt.executeUpdate("DELETE FROM `users` WHERE UID="+iid);
                 }
                 
                 g.dispose();
                

                 JOptionPane.showMessageDialog(null,"Book Deleted!");
                  
                }
                         
                 
                catch (SQLException e1) {
                    
                     JOptionPane.showMessageDialog(null, e1);
                }   
                 
                }
                 
            }); 
                
                g.add(create_but);
                g.add(l1);
                g.add(F_iid);
                g.add(a2);
                g.add(a1);
               
                g.setSize(350,250);//400 width and 500 height  
                g.setLayout(null);//using no layout managers  
                g.setVisible(true);//making the frame visible 
                g.setLocationRelativeTo(null);              

		}
	});
    
   
    main.add(delete_book);
    main.add(logout);
    main.add(create_but);
    main.add(return_book);
    main.add(issue_book);
    main.add(add_book);
    main.add(issued_but);
    main.add(users_but);
    main.add(view_but);
    main.add(add_user);
    main.setSize(600,200);//400 width and 500 height  
    main.setLayout(null);//using no layout managers  
    main.setVisible(true);//making the frame visible 
    main.setLocationRelativeTo(null);
    
     
    }



}

