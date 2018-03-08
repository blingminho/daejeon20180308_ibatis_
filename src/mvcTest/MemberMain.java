package mvcTest;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * 이 클래스는 View와 Controller역할을 같이 한다.
 * @author SEM
 */


public class MemberMain {
	Scanner scan = new Scanner(System.in);
	MemberServiceInf service;
	
	public MemberMain() {
		// Controller에서는 Service객체를 사용해야 하므로
		// Service객체를 생성한다.
		//service = new MemberService();
		service = MemberService.getInstance();
	} 
	
	
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("---------------------");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 수정");
		System.out.println("  3. 자료 삭제");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  0. 작업 끝.");
		System.out.println("---------------------");
		System.out.print("작업 선택 >> ");
	}	
	
	// 작업을 시작하는 메서드
	public void startMember(){
		int choice;
		
		do{
			displayMenu();
			choice = scan.nextInt();
			switch(choice){
				case 1 :  // 입력
					insertMember();
					break;
				case 2 :  // 수정
					updateMember();
					break;
				case 3 :  // 삭제
					deleteMember();
					break;
				case 4 :  // 전체 출력
					displayMember();
					break;
				case 5 :  // 자료검색
					searchMember();
					break;
				case 0 :  // 작업 끝.
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}while(choice!=0);
	}
	
	
	public void searchMember(){
	/*
		- 검색할 회원ID, 회원이름, 전화번호, 주소등을 입력하면
		  입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		- 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		- 주소는 입력한 값이 포함만 되어도 검색되도록 한다.		
	*/	
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.println();
		System.out.println("검색할 내용을 입력하세요.");
		
		System.out.print("검색할 회원ID >> ");
		String memId = scan.nextLine().trim();
		
		System.out.print("검색할 회원이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.print("검색할 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.print("검색할 주소 >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mvo = new MemberVO();
		mvo.setMem_id(memId);
		mvo.setMem_name(memName);
		mvo.setMem_tel(memTel);
		mvo.setMem_addr(memAddr);
		
		ArrayList<MemberVO> memList = 
				(ArrayList<MemberVO>) service.searchMember(mvo);
		
		System.out.println();
		System.out.println("       회원 정보 검색 결과 ");
		System.out.println("------------------------------------");
		System.out.println(" ID    이름     전화번호     주소");
		System.out.println("------------------------------------");
		if(memList==null || memList.size()==0){
			System.out.println("  검색된 회원이 하나도 없습니다.");
		}else{
			for(MemberVO mvo2 : memList){
				System.out.println( 
							mvo2.getMem_id() + "    " +
							mvo2.getMem_name() + "    " +
							mvo2.getMem_tel() + "    " +
							mvo2.getMem_addr()
						);
			}
		}
		
		System.out.println("------------------------------------");
		
	}
	
	
	// 전체 회원 정보를 출력하는 메서드
	public void displayMember() {
		ArrayList<MemberVO> memList = null;
		
		memList = (ArrayList<MemberVO>) service.getAllMember();
		
		System.out.println();
		System.out.println("       회원 정보 리스트 ");
		System.out.println("------------------------------------");
		System.out.println(" ID    이름     전화번호     주소");
		System.out.println("------------------------------------");
		if(memList==null || memList.size()==0){
			System.out.println("  출력할 회원이 하나도 없습니다.");
		}else{
			for(MemberVO mvo : memList){
				System.out.println( 
							mvo.getMem_id() + "    " +
							mvo.getMem_name() + "    " +
							mvo.getMem_tel() + "    " +
							mvo.getMem_addr()
						);
			}
		}
		
		System.out.println("------------------------------------");
		
		
	}

	// 회원 정보를 삭제하는 메서드
	public void deleteMember() {
		System.out.println();
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		
		int cnt = service.getCountMember(memId);
		if(cnt==0){
			System.out.println(memId + " 회원 정보는 없습니다.");
			return;
		}
		
		int result = service.deleteMember(memId);
		if(result>0){
			System.out.println(memId + " 회원 정보 삭제 성공!!");
		}else{
			System.out.println(memId + " 회원 정보 삭제 실패!!");
		}
	}

	// 회원 정보를 수정하는 메서드
	public void updateMember() {
		System.out.println();
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int cnt = service.getCountMember(memId);
		
		if(cnt==0){
			System.out.println(memId + " 회원은 없는 회원입니다.");
			return;
		}
		
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 회원전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력한 회원 정보를 VO객체에 저장한다.
		MemberVO mvo = new MemberVO();
		mvo.setMem_id(memId);
		mvo.setMem_name(memName);
		mvo.setMem_tel(memTel);
		mvo.setMem_addr(memAddr);
		
		int result = service.updateMember(mvo);
		
		if(result>0){
			System.out.println(memId + " 회원 정보 수정 완료!!");
		}else{
			System.out.println(memId + " 회원 정보 수정 실패!!");
		}
		
	}

	// 자료 추가하기
	public void insertMember(){
		int cnt = 0;
		String memId = null;
		do{
			System.out.println();
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			cnt = service.getCountMember(memId);
			
			if(cnt>0){
				System.out.println(memId + " 회원은 이미 있는 회원입니다.");
				System.out.println("회원ID를 다시 입력하세요");
			}
		}while(cnt>0);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력한 값들을 VO객체에 담는다.
		MemberVO mvo = new MemberVO();
		
		mvo.setMem_id(memId);
		mvo.setMem_name(memName);
		mvo.setMem_tel(memTel);
		mvo.setMem_addr(memAddr);
		
		int result = service.insertMember(mvo);
		if(result>0){
			System.out.println(memId + " 회원 추가 성공!!");
		}else{
			System.out.println(memId + " 회원 추가 실패!!");
		}
		
	}
	
	public static void main(String[] args) {
		new MemberMain().startMember();
	}

}
