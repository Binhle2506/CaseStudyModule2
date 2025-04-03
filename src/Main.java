import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager contactManager = new ContactManager(); // Đọc từ file khi chương trình khởi động
        while (true) {
            menu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("---Thông tin liên hệ---");
                    contactManager.displayContacts(); // Hiển thị từ danh bạ đã được lưu trong file
                    break;
                case 2:
                    System.out.println("---Thêm mới thông tin liên hệ---");
                    String numberPhone, name, gender, address;

                    do {
                        System.out.print("Hãy nhập số điện thoại: ");
                        numberPhone = sc.nextLine();
                    } while (!Pattern.matches("0[0-9]{9,10}", numberPhone)); // Kiểm tra số điện thoại

                    System.out.print("Hãy nhập họ và tên: ");
                    name = sc.nextLine();

                    do {
                        System.out.print("Hãy nhập giới tính (nam/nữ): ");
                        gender = sc.nextLine().toLowerCase();
                    } while (!gender.equals("nam") && !gender.equals("nữ") && !gender.equals("nu"));

                    System.out.print("Hãy nhập địa chỉ: ");
                    address = sc.nextLine();

                    Contact contact = new Contact(numberPhone, name, gender, address);
                    contactManager.addList(contact);
                    System.out.println("Thêm liên hệ thành công!");
                    break;
                case 3:
                    System.out.println("---Sửa thông tin liên hệ---");
                    System.out.print("Nhập số điện thoại cần sửa: ");
                    String phoneToEdit = sc.nextLine();
                    contactManager.editContact(phoneToEdit);
                    boolean found = false;
                    for (Contact contact1 : contactManager.list) {
                        if (contact1.getNumberPhone().equals(phoneToEdit)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        Contact editedContact = new Contact();
                        editedContact.setNumberPhone(phoneToEdit);
                        System.out.print("Nhập họ tên mới: ");
                        editedContact.setName(sc.nextLine());
                        System.out.print("Nhập giới tính mới (nam/nữ): ");
                        editedContact.setGender(sc.nextLine());
                        System.out.print("Nhập địa chỉ mới: ");
                        editedContact.setAddress(sc.nextLine());
                        contactManager.deleteContact(phoneToEdit);
                        contactManager.addList(editedContact);
                        System.out.println("Sửa thành công!");
                    }
                    break;
                case 4:
                    System.out.println("---Xóa liên hệ---");
                    System.out.print("Nhập số điện thoại cần xóa: ");
                    String phoneToDelete = sc.nextLine();
                    if (contactManager.deleteContact(phoneToDelete)) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không tìm thấy liên hệ để xóa.");
                    }
                    break;
                case 5:
                    System.out.println("---Tìm kiếm liên hệ---");
                    System.out.print("Nhập số điện thoại hoặc tên cần tìm: ");
                    String keyword = sc.nextLine();
                    contactManager.searchContact(keyword);
                    break;
                case 6:
                    System.out.println("---Sắp xếp danh sách---");
                    contactManager.sortContacts();
                    System.out.println("Đã sắp xếp theo tên!");
                    contactManager.displayContacts();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("-----Lựa chọn không hợp lệ, vui lòng chọn lại!!!------");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("---Quản lý danh bạ---");
        System.out.println("1. Thông tin liên hệ");
        System.out.println("2. Thêm mới");
        System.out.println("3. Sửa");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Sắp xếp");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
    }
}

