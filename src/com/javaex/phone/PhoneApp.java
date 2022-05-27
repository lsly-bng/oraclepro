package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 사전준비
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList;
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int personId;
		String name;
		String hp;
		String company;
		int count;
		String keyword;

		System.out.println("*****************************************");
		System.out.println("*	    전화번호 관리 프로그램	     	*");
		System.out.println("*****************************************");

		while (flag) {
			System.out.println(); // 공백
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료 ");
			System.out.println("----------------------------------------");
			System.out.print(">메뉴 번호: ");
			int option = sc.nextInt();
			sc.nextLine(); // 버그

			switch (option) {

			// 전화번호부 출력
			case 1:
				System.out.println("<1.리스트>");

				// SELECT 쿼리문 실행
				personList = phoneDao.getPersonList();
				printList(personList);
				break;

			// 전화번호 등록
			case 2:
				System.out.println("<2.등록>");
				// 이름
				System.out.print("이름 > ");
				name = sc.nextLine();
				// 휴대번호
				System.out.print("휴대번호 > ");
				hp = sc.nextLine();
				// 회사번호
				System.out.print("회사번호 > ");
				company = sc.nextLine();

				// INSERT 쿼리문 실행
				PersonVo insertVo = new PersonVo(name, hp, company);
				count = phoneDao.insert(insertVo);
				System.out.println("[" + count + "건 등록되었습니다.]");
				break;

			// 전화번호 수정
			case 3:
				System.out.println("<3.수정>");
				// ID 번호
				System.out.print("번호 > ");
				personId = sc.nextInt();
				sc.nextLine(); // 버그
				// 이름
				System.out.print("이름 > ");
				name = sc.nextLine();

				// 휴대번호
				System.out.print("휴대번호 > ");
				hp = sc.nextLine();

				// 회사번호
				System.out.print("회사번호 > ");
				company = sc.nextLine();

				// UPDATE 쿼리문 실행
				PersonVo updateVo = new PersonVo(name, hp, company, personId);
				count = phoneDao.update(updateVo);
				System.out.println("[" + count + "건 수정되었습니다.]");
				break;

			// 전화번호 삭제
			case 4:
				System.out.println("<4.삭제>");
				// ID 번호
				System.out.print(">번호 : ");
				personId = sc.nextInt();
				sc.nextLine(); // 버그

				// DELETE 쿼리문 실행
				count = phoneDao.delete(personId);
				System.out.println("[" + count + "건 삭제되었습니다.]");
				break;

			// 키워드 검색
			case 5:
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
				keyword = sc.nextLine();

				printList(phoneDao.search(keyword));
				break;

			// 전화번호 앱 종료
			case 6:
				flag = false;
				break;
			// 없는 옵션 선택 시
			default:
				System.out.println("[다시 입력해주세요.]");
				break;
			}
		}
		System.out.println();
		System.out.println("*****************************************");
		System.out.println("*            	감사합니다. 	        *");
		System.out.println("*****************************************");

		sc.close();
	}

//===================================================================

	// 리스트 출력 메소드
	public static void printList(List<PersonVo> personList) {
		for (int i = 0; i < personList.size(); i++) {

			PersonVo personVo = personList.get(i);

			int personId = personVo.getPersonId();
			String name = personVo.getName();
			String hp = personVo.getHp();
			String company = personVo.getCompany();

			System.out.println(personId + ".    " + name + "    " + hp + "    " + company);
		}
	}
}
