package kr.co.itcen.mysite.vo;

//번호
//제목
//글쓴이
//조회수
//작성일
/**
 * @author BIT
 *
 */
public class BoardUserListVo {
	private Long no;
	private String title;
	private String name;
	private Long hit;
	private String reg_date;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}
