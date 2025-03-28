package model.vo;

public class Member {

	private int userNo;
	private String userName;
	private String userId;
	private String address;
	
	// 생성자
	public Member() {
		super();
	}
	
	public Member(int userNo, String userName, String userId, String address) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.address = address;
	}
	
	
	// getter() / setter()
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userName=" + userName + ", userId=" + userId + ", address=" + address
				+ "]";
	}
	
	
	
}
