package dq.database.gui;

import dq.database.entity.Course;
import dq.database.entity.SC;
import dq.database.entity.Student;
import dq.database.mapper.CourseMapper;
import dq.database.mapper.SCMapper;
import dq.database.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;


public class DataBaseGui extends JFrame {
    private final SqlSessionFactory factory;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/lab1-sct?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    static final String username="root";
    static final String password="20010804";


    public DataBaseGui(SqlSessionFactory sqlSessionFactory) throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(DB_URL, username, password);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        String sqlStmt="SELECT * FROM student where Sname like '张_' and Ssex='男' and  Sage > 23 and `S#` like '2020%' limit 10";
        //stmt.executeQuery("SELECT * FROM student where Sname like '张_' and Ssex='男' and  Sage > 23 and `S#` like '2001%'limit 10");
        factory=sqlSessionFactory;
        setTitle("LAB1-数据库管理系统");    //设置显示窗口标题
        setSize(1000,600);    //设置窗口显示尺寸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
        JPanel contentPanel=new JPanel();
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        contentPanel.setLayout(null);

        JPanel panel1=new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
        panel1.add(new JLabel("表一："),BorderLayout.WEST);
        panel1.add(new JLabel("Student"),BorderLayout.CENTER);
        JButton button1=new JButton("装载");
        JTable table1=new JTable();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlSession session=factory.openSession();
                StudentMapper mapper=session.getMapper(StudentMapper.class);
                List<Student> allStudent=mapper.getAllStudent();
                DefaultTableModel tableModel=(DefaultTableModel)table1.getModel();
                tableModel.setRowCount(0);
                String[] name={"学号","性别","年龄","班级","姓名"};
                tableModel.setColumnIdentifiers(name);
                for(Student student:allStudent){
                    tableModel.addRow(new Object[]{student.getS(),student.getSex(),student.getSage(),student.getSClass(),student.getSname()});
                }
                table1.setModel(tableModel);
                session.close();
            }
        });
        panel1.add(button1,BorderLayout.EAST);
        table1.setEnabled(false);
        panel1.add(new JScrollPane(table1),BorderLayout.SOUTH);
        contentPanel.add(panel1);
        panel1.setBounds(0,0,500,200);

        JPanel panel2=new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
        panel2.add(new JLabel("表二："),BorderLayout.WEST);
        panel2.add(new JLabel("Course"),BorderLayout.CENTER);
        JButton button2=new JButton("装载");
        JTable table2=new JTable();
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlSession session=factory.openSession();
                CourseMapper mapper=session.getMapper(CourseMapper.class);
                List<Course> allCourses=mapper.getAllCourses();
                DefaultTableModel tableModel=(DefaultTableModel)table2.getModel();
                tableModel.setRowCount(0);
                String[] name={"课程号","课名","学分","学时"};
                tableModel.setColumnIdentifiers(name);
                for(Course course:allCourses){
                    tableModel.addRow(new Object[]{course.getC(),course.getCname(),course.getCredit(),course.getChours()});
                }
                table2.setModel(tableModel);
                session.close();
            }
        });
        panel2.add(button2,BorderLayout.EAST);
        table2.setEnabled(false);
        panel2.add(new JScrollPane(table2),BorderLayout.SOUTH);
        contentPanel.add(panel2);
        panel2.setBounds(0,200,500,200);

        JPanel panel3=new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEADING,5,5));
        panel3.add(new JLabel("表一："),BorderLayout.WEST);
        panel3.add(new JLabel("Student"),BorderLayout.CENTER);
        JButton button3=new JButton("装载");
        JTable table3=new JTable();
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlSession session=factory.openSession();
                SCMapper mapper=session.getMapper(SCMapper.class);
                List<SC> allSC=mapper.getAllSC();
                DefaultTableModel tableModel=(DefaultTableModel)table3.getModel();
                tableModel.setRowCount(0);
                String[] name={"学号","课程号","成绩"};
                tableModel.setColumnIdentifiers(name);
                for(SC sc:allSC){
                    tableModel.addRow(new Object[]{sc.getS(),sc.getC(),sc.getScore()});
                }
                table3.setModel(tableModel);
                session.close();
            }
        });
        table3.setEnabled(false);
        panel3.add(button3,BorderLayout.EAST);
        panel3.add(new JScrollPane(table3),BorderLayout.SOUTH);
        contentPanel.add(panel3);
        panel3.setBounds(0,400,500,200);

        JPanel panel4=new JPanel();
        panel4.setLayout(new GridLayout(3,2,10,10));

        JPanel panel41=new JPanel();
        JCheckBox checkBox1=new JCheckBox("学号");
        panel41.add(checkBox1);
        JTextField field1=new JTextField(10);
        panel41.add(field1);

        JPanel panel42=new JPanel();
        ButtonGroup buttonGroup=new ButtonGroup();
        JCheckBox checkBox2=new JCheckBox("性别");
        panel42.add(checkBox2);
        JRadioButton male=new JRadioButton("男");
        JRadioButton female=new JRadioButton("女");
        buttonGroup.add(male);
        buttonGroup.add(female);
        panel42.add(male);
        panel42.add(female);

        JPanel panel43=new JPanel();
        JCheckBox checkBox3=new JCheckBox("年龄");
        panel43.add(checkBox3);
        JTextField field3=new JTextField(10);
        panel43.add(field3);

        JPanel panel44=new JPanel();
        JCheckBox checkBox4=new JCheckBox("班级");
        panel44.add(checkBox4);
        JTextField field4=new JTextField(10);
        panel44.add(field4);

        JPanel panel45=new JPanel();
        JCheckBox checkBox5=new JCheckBox("姓名");
        panel45.add(checkBox5);
        JTextField field5=new JTextField(10);
        panel45.add(field5);

        JPanel panel46=new JPanel();
        JButton button4=new JButton("生成SQL语句");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String query="select * from ";
            }
        });
        panel46.add(button4);

        panel4.add(panel41);
        panel4.add(panel42);
        panel4.add(panel43);
        panel4.add(panel44);
        panel4.add(panel45);
        panel4.add(panel46);
        contentPanel.add(panel4);
        panel4.setBounds(500,0,480,200);

        JPanel panel5=new JPanel();
        panel5.setLayout(new BorderLayout());
        panel5.add(new JLabel("SQL语句"),BorderLayout.NORTH);
        JTextArea textArea=new JTextArea();
        textArea.setSize(400,80);
        textArea.setMinimumSize(new Dimension(400,80));
        textArea.setEditable(false);
        textArea.setText(sqlStmt);
        textArea.setLineWrap(true);        //激活自动换行功能
        textArea.setWrapStyleWord(true);            // 激活断行不断字功能
        panel5.add(textArea,BorderLayout.CENTER);
        JButton button5=new JButton("执行");
        panel5.add(button5,BorderLayout.SOUTH);
        contentPanel.add(panel5);
        panel5.setBounds(500,200,480,100);

        JPanel panel6=new JPanel();
        JTable table4=new JTable(8,5);
        table4.setEnabled(false);
        panel6.add(new JLabel("检索结果"),BorderLayout.NORTH);
        panel6.add(new JScrollPane(table4));
        contentPanel.add(panel6);
        panel6.setBounds(500,300,480,250);

        String[] name={"学号","性别","年龄","班级","姓名"};
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textArea.setText(sqlStmt);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel tableModel=(DefaultTableModel)table4.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnIdentifiers(name);
                    ResultSet rs=stmt.executeQuery(sqlStmt);
                    while(rs.next()){
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5));
                        tableModel.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5)});
                    }
                    table4.setModel(tableModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(contentPanel);
        setVisible(true);
    }
}