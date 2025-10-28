package library.service;

import library.dao.MemberLayer;
import library.models.Member;

import java.sql.SQLException;
import java.util.Scanner;

public class MemberService {
    Scanner sc = new Scanner(System.in);
    MemberLayer memberLayer = new MemberLayer();
    int choice = 0;
    public MemberService() throws SQLException {
        System.out.println("""
                1.Add New Member
                2.Update Member Details
                3.Delete Member by Id
                4.View All Members
                5.Exit
                """);
        while (choice!=5){
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 ->{
                    Member member = new Member();
                    System.out.print("Enter UserName: ");
                    member.setName(sc.nextLine());

                    System.out.print("Enter College Email: ");
                    member.setEmail(sc.nextLine());

                    System.out.print("Enter Mobile Number: ");
                    member.setPhone(sc.nextInt());

                    memberLayer.addMember(member);
                }
                case 2 ->{
                    Member member = new Member();
                    System.out.print("Enter the Userid to Update: ");
                    member.setId(sc.nextInt());
                    System.out.print("Enter new UserName: ");
                    member.setName(sc.nextLine());
                    System.out.print("Enter new College Email: ");
                    member.setEmail(sc.nextLine());
                    System.out.print("Enter new Mobile Number: ");
                    member.setPhone(sc.nextInt());
                    memberLayer.updateMember(member);
                }
                case 3 ->{
                    System.out.println("Enter id to delete: ");
                    int id = sc.nextInt();
                    memberLayer.deleteMember(id);
                }
                case 4 ->
                    memberLayer.getAllMembers();
                case 5 ->
                    System.out.println("Exiting");

            }
        }
    }
}
