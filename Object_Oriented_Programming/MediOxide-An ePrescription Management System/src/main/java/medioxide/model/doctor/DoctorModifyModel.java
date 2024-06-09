package medioxide.model.doctor;

public class DoctorModifyModel {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String dept;

    private String speciality;
    private String roomNo;
    private String degree;

    public DoctorModifyModel(int id, String name, String phone, String email, String dept, String speciality, String roomNo, String degree) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
        this.speciality = speciality;
        this.roomNo = roomNo;
        this.degree = degree;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DoctorModifyModel(String name, String phone, String email, String dept, String speciality, String roomNo, String degree) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
        this.speciality = speciality;
        this.roomNo = roomNo;
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
