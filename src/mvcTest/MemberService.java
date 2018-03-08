package mvcTest;


import java.util.List;


public class MemberService implements MemberServiceInf {
	private MemberDaoInf memDao;
	
	// 자기 참조 변수
	private static MemberService service = new MemberService();
	
	// 생성자
	private MemberService() {
		// Service객체는 DAO객체를 사용해야 하기 때문에 DAO객체를 생성해야 한다.
		// 싱글턴이 적용된 DAO객체를 받아온다
		memDao = MemberDao.getInstance();
		System.out.println("Service 싱글턴 적용");
	}
	
	// 객체 반환 메서드
	public static MemberService getInstance(){
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return memDao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memDao.getAllMember();
	}

	@Override
	public int getCountMember(String memId) {
		return memDao.getCountMember(memId);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO memVo) {
		return memDao.searchMember(memVo);
	}

}
