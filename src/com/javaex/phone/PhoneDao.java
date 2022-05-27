package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // IP주소와 포트번호
	private String id = "phonedb"; // SQL 계정 이름
	private String pw = "phonedb"; // SQL 계정 비밀번호

	// constructor

	// method - g/s

	// method - general

	// --DB연결 메소드
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ===============================================================================//

	// --자원정리 메소드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ===============================================================================//

	// --전화번호부 출력
	public List<PersonVo> getPersonList() {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String select = "";
			select += "select person_id, ";
			select += "       name, ";
			select += "       hp, ";
			select += "       company ";
			select += "from person ";
			select += "order by person_id asc ";

			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return personList;
	}

	// --전화번호 등록
	public int insert(PersonVo personVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String insert = "";
			insert += "insert into person ";
			insert += "values (seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return count;
	}

	// --전화번호 수정
	public int update(PersonVo personVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String update = "";
			update += "update person ";
			update += "set name = ?, ";
			update += "    hp = ?, ";
			update += "    company = ? ";
			update += "where person_id = ? ";

			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return count;
	}

	// --전화번호 삭제
	public int delete(int personId) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String delete = "";
			delete += "delete from person ";
			delete += "where person_id = ? ";

			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return count;
	}

	// --전화번호 검색
	public List<PersonVo> search(String keyword) {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String search = "";
			search += "select person_id, ";
			search += "       name, ";
			search += "       hp, ";
			search += "       company ";
			search += "from person ";
			search += "where person_id like '%" + keyword + "%' ";
			search += "      or name like '%" + keyword + "%' ";
			search += "      or hp like '%" + keyword + "%' ";
			search += "      or company like '%" + keyword + "%' ";

			pstmt = conn.prepareStatement(search);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return personList;
	}

}
