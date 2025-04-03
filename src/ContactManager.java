import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ContactManager {
    ArrayList<Contact> list = new ArrayList<>();
    private final String FILE_PATH = "contacts.csv"; // Đường dẫn file


    public ContactManager() {
        loadContactsFromFile();
    }


    private void loadContactsFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Số điện thoại")) {
                        String numberPhone = line.split(":")[1].trim();
                        String name = reader.readLine().split(":")[1].trim();
                        String gender = reader.readLine().split(":")[1].trim();
                        String address = reader.readLine().split(":")[1].trim();
                        Contact contact = new Contact(numberPhone, name, gender, address);
                        list.add(contact);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dữ liệu từ file!");
            e.printStackTrace();
        }
    }


    private void rewriteFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Contact contact : list) {
                bufferedWriter.write("Số điện thoại: " + contact.getNumberPhone() + "\n" +
                        "Họ tên: " + contact.getName() + "\n" +
                        "Giới tính: " + contact.getGender() + "\n" +
                        "Địa chỉ: " + contact.getAddress() + "\n");
            }
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi! Không ghi được file");
            e.printStackTrace();
        }
    }

    public void addList(Contact contact) {
        list.add(contact);
        rewriteFile();
    }


    public void displayContacts() {
        if (list.isEmpty()) {
            System.out.println("Không có thông tin liên hệ, vui lòng thêm mới!!");
            return;
        }
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }

    public void searchContact(String keyword) {
        boolean found = false;
        for (Contact contact : list) {
            if (contact.getNumberPhone().contains(keyword) || contact.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Liên hệ được tìm thấy:\n" + contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy liên hệ nào phù hợp.");
        }
    }

    public void editContact(String phoneNumber) {
        for (Contact contact : list) {
            if (contact.getNumberPhone().equals(phoneNumber)) {
                System.out.println("Tìm thấy liên hệ:\n" + contact);
                return;
            }
        }
        System.out.println("Không tìm thấy liên hệ để sửa.");
    }


    public boolean deleteContact(String phoneNumber) {
        for (Contact contact : list) {
            if (contact.getNumberPhone().equals(phoneNumber)) {
                list.remove(contact);
                rewriteFile();
                return true;
            }
        }
        return false;
    }


    public void sortContacts() {
        Collections.sort(list, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                String name1 = c1.getName();
                String name2 = c2.getName();
                return name1.compareToIgnoreCase(name2);
            }
        });
        rewriteFile();
    }
}
