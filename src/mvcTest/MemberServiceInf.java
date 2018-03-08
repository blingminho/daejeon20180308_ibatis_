package mvcTest;


import java.util.List;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 실행한 후 결과를 받아오고, 받아온 자료를 Controller에 보내주는 역할을 수행한다
 * 보통 DAO의 메서드와 같은 구조를 갖는다.
 * @author PC17
 *
 */
public interface MemberServiceInf {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1이상, 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 하나의 MemberVO를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1이상, 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 mymember테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 매개변수로 주어진 회원ID의 회원정보가 있는지 여부를 나타내는 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당 회원이 있으면 1, 없으면 0을 반환한다
	 */
	public int getCountMember(String memId);
	
	/**
	 * DB의 mymember테이블의 검색조건에 부합하는 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @param 검색조건들을 담은 MemberVO객체
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> searchMember(MemberVO memVo);
}
