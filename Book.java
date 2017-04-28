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
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			}
		}
	}

	private static int inputChoice() {
		int choice = -1;

		System.out.println("�޴��� �����ϼ��� : ");

		choice = input.nextInt();

		System.out.println(" ������ �޴� : " + choice);
		return choice;
	}

	private static void printMenu() {
		System.out.println("�������� ���α׷� �Դϴ�.");
		System.out.println("1.��ü ���� ���");
		System.out.println("2.���� �˻�");
		System.out.println("3.���� �߰�");
		System.out.println("4.���� ����");
		System.out.println("0.���α׷� ����");
	}

	private static void deletebook() throws IOException {

		String tmpFilePath = filePath + ".tmp";
		int count = 1;
		System.out.println("�����Ͻ� å ��ȣ�� �Է����ּ��� : ");
		int booknumber = input.nextInt();

		System.out.println("�Է��Ͻ� å ��ȣ : " + booknumber);

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

		System.out.println("�߰��� å ������ �Է��ϼ���");
		input.nextLine();
		System.out.println("å ���� : ");
		String name = input.nextLine();

		System.out.println("���� : ");
		String author = input.nextLine();

		System.out.println("���ǻ� : ");
		String publisher = input.nextLine();

		System.out.println("���� : ");
		String cost = input.nextLine();

		System.out.println("�Է��� å ����");
		System.out.println("å ���� : " + name);
		System.out.println("����: " + author);
		System.out.println("���ǻ� : " + publisher);
		System.out.println("����: " + cost);

		System.out.println("�Է��Ͻ� ������ �½��ϱ�? ");
		System.out.println("1 : yes 2 : no");
		int cho = input.nextInt();
		if(cho == 1){
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));

		bw.write(name + "\t" + author + "\t" + publisher + "\t" + cost);
		bw.newLine();
		bw.close();
		System.out.println("�߰� �Ϸ�Ǿ����ϴ�. ");
		}else{
			System.out.println("�ٽ� �ѹ� �Է����ּ���.");
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
		
		System.out.println("�������� �˻� �Ͻ� �ǰ��� ? ");
		System.out.println("1. å �̸�  2. ���� 3. ���ǻ�  4.����");
		menu = input.nextInt();

		switch (menu) {
		case 1: {
			System.out.println("å �̸��� �Է��ϼ��� : ");
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

				System.out.println("�˻��Ͻ� å�� �� �Ǽ��� : " + count + "�� �Դϴ�.");
				br.close();
			} catch (IOException e) {
				System.out.println("å ������ �о� �� �� �����ϴ�.");
				e.printStackTrace();
			}
			break;
		}
		case 2: {
			System.out.println("���� �̸��� �Է��ϼ��� : ");
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

				System.out.println("�˻��Ͻ� å�� �� �Ǽ��� : " + count + "�� �Դϴ�.");
				br.close();
			} catch (IOException e) {
				System.out.println("å ������ �о� �� �� �����ϴ�.");
				e.printStackTrace();
			}
			break;
		}
		case 3: {
			System.out.println("���ǻ縦 �Է��ϼ��� : ");
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
				
			

				System.out.println("�˻��Ͻ� å�� �� �Ǽ��� : " + count + "�� �Դϴ�.");
				br.close();
			} catch (IOException e) {
				System.out.println("å ������ �о� �� �� �����ϴ�.");
				e.printStackTrace();
			}
			break;
		}
		case 4:
		{
			System.out.println("���� �˻��� ����Ͻ� Ű���带 �Է����ּ���.");
			keyword = input.nextLine();
			try {
				while((str = br.readLine()) != null){
				if(str.contains(keyword)){
					System.out.println(bookLine+" : "+str);
					count++;
					bookLine++;
				}

				}
				System.out.println("�˻��Ͻ� å�� �� �Ǽ��� : "+count+"�� �Դϴ�.");
				br.close();
			} catch (IOException e) {
				System.out.println("å ������ �о� �� �� �����ϴ�.");
				e.printStackTrace();
			}
		}
		default: {
			System.out.println("�� �� �Է��ϼ̽��ϴ� .");
			System.out.println("���θ޴��� ���ư��ϴ�.");
			}
		}

	}

	private static void printbooks() throws FileNotFoundException {
		System.out.println("å ���");
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
			System.out.println("å�� �� �Ǽ��� : " + count + "�� �Դϴ�.");
		} catch (IOException e) {
			System.out.println("å ������ �о� �� �� �����ϴ�.");
			e.printStackTrace();
		}
	}
}