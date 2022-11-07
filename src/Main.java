/************************************************************************************************************************************************************
 * 데이터베이스 HOMEWORK#8
 * 작성자 : 2016039082 신효민 소프트웨어학부 , 충북대학교
 * 주제 : 본인의 주력 프로그래밍 언어를 선택하고(예: Python, Java 등) CentOS MySQL 서버의 madang 데이터베이스에 접근하여 데이터 삽입, 삭제, 검색이 가능한 간단한 프로그램을 작성하라.
 * 프로그램 설명 : 모드가 1일때 -> 데이터 삽입 , 모드가 2일때 -> 데이터 검색 , 모드가 3일때 -> 데이터 삭제
 ************************************************************************************************************************************************************/

import java.sql.*;
import java.util.Scanner; // 모드 입력을 위한 Scanner

public class Main {
    public static void main(String[] args)
    {
        int num=0; // 모드 입력을 위한 변수
        Scanner sc=new Scanner(System.in); // 모드 입력을 위한 Scanner 객체 생성
        System.out.println("모드를 입력하세요!!");
        num=sc.nextInt(); // 모드를 입력받는다.
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/madang","root",
                    "7632"); // madang 데이터베이스에 접근
            Statement stmt=con.createStatement();
            if(num==1) // 모드가 1일때 데이터를 삽입한다. ( 데이터 삽입 )
            {
                stmt.executeUpdate("INSERT INTO Book(bookid,bookname,publisher,price) VALUES(11,'챔피언스리그','신효출판사','9500')"); // 데이터 삽입
                System.out.println("성공했습니다!!");
                ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
                while(rs.next())
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                con.close();
            }
            else if(num==2) // 모드가 2일때 bookname이 '챔피언스리그'인 것을 검색해서 보여준다. ( 데이터 검색 )
            {
                ResultSet rs=stmt.executeQuery("SELECT * FROM Book WHERE bookname='챔피언스리그'"); // bookname이 '챔피언스리그'인 것을 검색
                while(rs.next())
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
                con.close();
            }
            else if(num==3) // 모드가 3일때 데이터를 삭제한다. ( 데이터 삭제 )
            {
                stmt.executeUpdate( "DELETE FROM Book WHERE bookname='챔피언스리그'"); // bookname이 '챔피언스리그'인 것을 찾아 삭제한다.
                System.out.println("성공했습니다!!");
                ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
                while(rs.next())
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                con.close();

            }
            else {
                System.out.println("잘못 입력하셨습니다!!"); // 모드가 1,2,3중에서 나오지 않을때 출력
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}