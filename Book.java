import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Book {
	static Scanner input = new Scanner(System.in);
	static String filePath = "c:\\test\\book.txt";

	public static void main(String[] args) throws IOException {

		printMenu();

		int choice = -1;

		while (choice != 0) {
			choice = inputChoice();
			switch (choice) {
			case 1:
				printbooks();
				break;
			case 2:
				searchbook();
				break;
			case 3:
				insertbook();
				break;
			case 4:
				deletebook();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}

	private static int inputChoice() {
		int choice = -1;

		System.out.println("메뉴를 선택하세요 : ");

		choice = input.nextInt();

		System.out.println(" 선택한 메뉴 : " + choice);
		return choice;
	}

	private static void printMenu() {
		System.out.println("도서관리 프로그램 입니다.");
		System.out.println("1.전체 도서 출력");
		System.out.println("2.도서 검색");
		System.out.println("3.도서 추가");
		System.out.println("4.도서 삭제");
		System.out.println("0.프로그램 종료");
	}

	private static void deletebook() throws IOException {

		String tmpFilePath = filePath + ".tmp";
		int count = 1;
		System.out.println("삭제하실 책 번호를 입력해주세요 : ");
		int booknumber = input.nextInt();

		System.out.println("입력하신 책 번호 : " + booknumber);

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));

		String str = "";

		while ((str = br.readLine()) != null) {
			if (count != booknumber) {
				bw.write(str);
				bw.write("\r\n");
			}
			count++;
		}
		bw.close();
		br.close();

		FileInputStream fis = new FileInputStream(tmpFilePath);
		FileOutputStream fos = new FileOutputStream(filePath);

		int data = 0;
		while ((data = fis.read()) != -1) {
			fos.write(data);
		}
		fis.close();
		fos.close();

		File f = new File(tmpFilePath);
		f.deleteOnExit();
	}

	private static void insertbook() throws IOException {

		System.out.println("추가할 책 정보를 입력하세요");
		input.nextLine();
		System.out.println("책 제목 : ");
		String name = input.nextLine();

		System.out.println("저자 : ");
		String author = input.nextLine();

		System.out.println("출판사 : ");
		String publisher = input.nextLine();

		System.out.println("가격 : ");
		String cost = input.nextLine();

		System.out.println("입력한 책 정보");
		System.out.println("책 제목 : " + name);
		System.out.println("저자: " + author);
		System.out.println("출판사 : " + publisher);
		System.out.println("가격: " + cost);

		System.out.println("입력하신 정보가 맞습니까? ");
		System.out.println("1 : yes 2 : no");
		int cho = input.nextInt();
		if(cho == 1){
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));

		bw.write(name + "\t" + author + "\t" + publisher + "\t" + cost);
		bw.newLine();
		bw.close();
		System.out.println("추가 완료되었습니다. ");
		}else{
			System.out.println("다시 한번 입력해주세요.");
			insertbook();
		}
		
	}

	private static void searchbook() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		int count = 0;
		int menu = 0;
		int bookLine = 1;
		String str = "";
		String bookname;
		String author;
		String publisher;
		String keyword;
		
		System.out.println("무엇으로 검색 하실 건가요 ? ");
		System.out.println("1. 책 이름  2. 저자 3. 출판사  4.통합");
		menu = input.nextInt();

		switch (menu) {
		case 1: {
			System.out.println("책 이름을 입력하세요 : ");
			input.nextLine();
			bookname = input.nextLine();

			try {
				while ((str = br.readLine()) != null) {
					if (str.contains(bookname)) {
						String strArr[] = str.split("\t");
						String keyname = strArr[0];
						if (keyname.equals(bookname)) {
							System.out.println(bookLine + " : " + str);
							count++;
							bookLine++;
						}
					}
				}

				System.out.println("검색하신 책의 총 권수는 : " + count + "권 입니다.");
				br.close();
			} catch (IOException e) {
				System.out.println("책 정보를 읽어 올 수 없습니다.");
				e.printStackTrace();
			}
			break;
		}
		case 2: {
			System.out.println("저자 이름을 입력하세요 : ");
			input.nextLine();
			author = input.nextLine();

			try {
				while ((str = br.readLine()) != null) {
					if (str.contains(author)) {
						String strArr[] = str.split("\t");
						String keyname = strArr[1];
						if (keyname.equals(author)) {
							System.out.println(bookLine + " : " + str);
							count++;
							bookLine++;
						}
					}
				}

				System.out.println("검색하신 책의 총 권수는 : " + count + "권 입니다.");
				br.close();
			} catch (IOException e) {
				System.out.println("책 정보를 읽어 올 수 없습니다.");
				e.printStackTrace();
			}
			break;
		}
		case 3: {
			System.out.println("출판사를 입력하세요 : ");
			input.nextLine();
			publisher = input.nextLine();

			try {
				while ((str = br.readLine()) != null) {
					if (str.contains(publisher)) {
						String strArr[] = str.split("\t");
						String keyname = strArr[2];
						if (keyname.equals(publisher)) {
							System.out.println(bookLine + " : " + str);
							count++;
							bookLine++;
						}
					}
				}
				
			

				System.out.println("검색하신 책의 총 권수는 : " + count + "권 입니다.");
				br.close();
			} catch (IOException e) {
				System.out.println("책 정보를 읽어 올 수 없습니다.");
				e.printStackTrace();
			}
			break;
		}
		case 4:
		{
			System.out.println("통합 검색에 사용하실 키워드를 입력해주세요.");
			keyword = input.nextLine();
			try {
				while((str = br.readLine()) != null){
				if(str.contains(keyword)){
					System.out.println(bookLine+" : "+str);
					count++;
					bookLine++;
				}

				}
				System.out.println("검색하신 책의 총 권수는 : "+count+"권 입니다.");
				br.close();
			} catch (IOException e) {
				System.out.println("책 정보를 읽어 올 수 없습니다.");
				e.printStackTrace();
			}
		}
		default: {
			System.out.println("잘 못 입력하셨습니다 .");
			System.out.println("메인메뉴로 돌아갑니다.");
			}
		}

	}

	private static void printbooks() throws FileNotFoundException {
		System.out.println("책 출력");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String str = "";
		int count = 0;
		int bookLine = 1;
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(bookLine + " : " + str);
				count++;
				bookLine++;
			}
			System.out.println("책의 총 권수는 : " + count + "권 입니다.");
		} catch (IOException e) {
			System.out.println("책 정보를 읽어 올 수 없습니다.");
			e.printStackTrace();
		}
	}
}