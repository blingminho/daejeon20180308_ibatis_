package basic;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

// ibatis를 이용하여 DB자료를 처리하는 예제
public class MemberIbatisTest {
	public static void main(String[] args) {
		// 처리순서
		// 1. ibatis의 환경설정파일(sqlMapConfig.xml)을 읽어와 실행하기
		try {
			// 1-1. xml문서 읽어오기
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체를 생성한다
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다
			
			// 2-1. insert작업 연습
			System.out.println("insert작업 시작...");
			
			// 1) 저장할 데이터를 VO에 담는다
			MemberVO mv = new MemberVO();
			mv.setMem_id("d001");
			mv.setMem_name("홍길동");
			mv.setMem_tel("010-3344-5566");
			mv.setMem_addr("경상남도");
			
			// 2) SQLMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다
			//	형식) smc.insert("namespace값.id속성값", 파라미터클래스);
			// 		반환값 : insert가 성공하면 null을 반환한다
//			Object obj = smc.insert("mymember.memInsert", mv);
//			if (obj == null) {
//				System.out.println("insert작업 성공!!");
//			} else {
//				System.out.println("insert작업 실패!!");
//			}
//			System.out.println("-------------------------------------------------------------");
			
			
			// 2-2 update작업 연습
			System.out.println("update작업 시작...");
			// 1) update할 데이터를 VO에 담는다
			MemberVO mv2 = new MemberVO();
			mv2.setMem_id("d001");
			mv2.setMem_name("일지매");
			mv2.setMem_tel("010-8888-7777");
			mv2.setMem_addr("대전시 동구 대동");
//			
//			// 2) update()메서드를 이용하여 쿼리문을 호출해서 처리한다
//			//		형식) smc.update("namespace값.id속성값", 파라미터클래스)
//			//			반환값 : update가 성공하면 레코드 수가 반환된다
//			int cnt = smc.update("mymember.memUpdate", mv2);
//			if(cnt > 0){
//				System.out.println("update작업 성공!!");
//			} else {
//				System.out.println("update작업 실패!!");
//			}
//			System.out.println("-------------------------------------------------------------");
			
			
			// 2-3. delect연습
			System.out.println("delete작업 시작...");
			
			// delete메서드의 반환값 : 성공한 레코드 수
//			int cnt2 = smc.delete("mymember.memDelete", "d001");
//			if (cnt2 > 0) {
//				System.out.println("delete작업 성공~~");
//			} else {
//				System.out.println("delete작업 실패~~");
//			}
//			System.out.println("-------------------------------------------------------------");
			
			
			// 2-4. select연습
			// 1) select처리 결과가 여러개인 경우
			System.out.println("select연습 -> 응답 결과가 여러개일 경우...");
//			ArrayList<MemberVO> memList;
//			
//			// 응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다.
//			// 이 메서드는 여러개의 레코드를 각각 VO에 담은 후 이 VO객체를 List에 추가해주는 작업을 자동으로 수행한다
//			memList = (ArrayList<MemberVO>)smc.queryForList("mymember.getMemberAll");
//			
//			System.out.println("= 회원 목록 =");
//			System.out.println("---------------------------");
//			for (MemberVO memVO : memList) {
//				System.out.println("ID : " + memVO.getMem_id());
//				System.out.println("이름 : " + memVO.getMem_name());
//				System.out.println("전화 : " + memVO.getMem_tel());
//				System.out.println("주소 : " + memVO.getMem_addr());
//				System.out.println("---------------------------");
//			}
//			System.out.println("출력 끝 ....");
			
			
			// 2) select의 처리결과가 1개일 경우
			System.out.println("select연습 -> 응답 결과가 1개일 경우...");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다
			MemberVO mv3 = (MemberVO)smc.queryForObject("mymember.getMember", "a001");
			System.out.println("ID : " + mv3.getMem_id());
			System.out.println("이름 : " + mv3.getMem_name());
			System.out.println("전화 : " + mv3.getMem_tel());
			System.out.println("주소 : " + mv3.getMem_addr());
			System.out.println("출력 끝 ....");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
