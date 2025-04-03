public class Contact {
    private String numberPhone;
    private String name;
    private String gender;
    private String address;

    public Contact() {
    }

    public Contact(String numberPhone, String name, String gender, String address) {
        this.numberPhone = numberPhone;
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Số điện thoại: " + numberPhone + "\n" +
                "Họ tên: " + name + "\n" +
                "Giới tính: " + gender + "\n" +
                "Địa chỉ: " + address + "\n";
    }
}
