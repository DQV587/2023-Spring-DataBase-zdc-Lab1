package dq.database;

import dq.database.entity.Course;
import dq.database.entity.SC;
import dq.database.entity.Student;
import dq.database.mapper.CourseMapper;
import dq.database.mapper.SCMapper;
import dq.database.mapper.StudentMapper;
import dq.database.gui.DataBaseGui;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {
    private static String resource = "mybatis-config.xml";
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(reader,"development");

        new DataBaseGui(factory);

//        generateStudent(factory);
//        generateCourse(factory);
//        generateSC(factory);
    }

    public static void generateSC(SqlSessionFactory factory) throws IOException{
        SqlSession session=factory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        CourseMapper courseMapper=session.getMapper(CourseMapper.class);
        SCMapper scMapper=session.getMapper(SCMapper.class);

        List<Student> allStudent=studentMapper.getAllStudent();
        List<Course> allCourse=courseMapper.getAllCourses();
        Random random=new Random();
        for(Student student:allStudent){
            int courseNum =(4-(Integer.parseInt(student.getS().substring(0,4))-2020))*16;
            String S=student.getS();
            for(int i=0;i<courseNum;i++){
                int k=random.nextInt(allCourse.size());
                String C=allCourse.get(k).getC();
                float score=random.nextFloat()*100;
                scMapper.insertSC(new SC(S,C,score));
            }
        }
        session.commit();
        session.close();
    }

    public static void generateCourse(SqlSessionFactory factory) throws IOException{
        SqlSession session=factory.openSession();
        CourseMapper courseMapper=session.getMapper(CourseMapper.class);
        Random random=new Random();
        for(int i=0;i<=1000;i++){
            String C=String.format("%04d",i);
            String name="JSXB"+String.format("%04d",i);
            int hour=(random.nextInt(24)+1)*8;
            float credit=((float)hour)/16;
            courseMapper.insertCourse(new Course(C,name,credit,hour));
        }
        session.commit();
        session.close();
    }
    public static void generateStudent(SqlSessionFactory factory) throws IOException {
        SqlSession session=factory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        Random random = new Random();
        for(int i=0;i<4;i++){
            int year=2020+i;
            int counter=0;
            int classNum=0;
            for(int j=0;j<2600;j++){
                String id=String.format("%04d",j);
                String S=year+id;
                int k = random.nextInt(3);
                String sex=k==1?"男":"女";
                int age=random.nextInt(21)+15;
                String Sclass=String.format("%02d",year-2000)+String.format("%04d",classNum);
                counter++;
                if(counter==20){
                    classNum++;
                    counter=0;
                }
                String name=generateName(k);
                studentMapper.insertStudent(new Student(S,sex,age,Sclass,name));
                }
            }
        session.commit();
        session.close();
        }
    private static String generateName(int k){
        String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
        String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
        String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
        Random random=new Random();
        int index = random.nextInt(Surname.length - 1);
        String name = Surname[index]; //获得一个随机的姓氏
        if(k==2){
            int l = random.nextInt(girl.length()-2);
            if (l % 2 == 0) {
                name =name + girl.substring(l, l + 2);
            } else {
                name =name + girl.charAt(l);
            }

        }
        else{
            int l = random.nextInt(boy.length()-2);
            if (l % 2 == 0) {
                name =name + boy.substring(l, l + 2);
            } else {
                name =name + boy.charAt(l);
            }
        }
        return name;
    }
}