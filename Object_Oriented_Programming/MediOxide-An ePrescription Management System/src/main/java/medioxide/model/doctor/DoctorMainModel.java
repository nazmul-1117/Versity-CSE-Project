package medioxide.model.doctor;

public class DoctorMainModel{
    private int id;
    private String name;
    private String surname;
    private String gender;
    private String phone;
    private String email;
    private int nid;
    private String licence;
    private String hospital;
    private String dept;
    private String speciality;
    private String roomNo;
    private String degree;
    private int experience;

    public DoctorMainModel(int id, String name, String surname, String gender, String phone, String email, int nid, String licence,
                           String hospital, String dept, String speciality, String roomNo, String degree, int experience) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.nid = nid;
        this.licence = licence;
        this.hospital = hospital;
        this.dept = dept;
        this.speciality = speciality;
        this.roomNo = roomNo;
        this.degree = degree;
        this.experience = experience;
    }

    public DoctorMainModel(String name, String surname, String gender, String phone, String email, int nid, String licence,
                           String hospital, String dept, String speciality, String roomNo, String degree, int experience) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.nid = nid;
        this.licence = licence;
        this.hospital = hospital;
        this.dept = dept;
        this.speciality = speciality;
        this.roomNo = roomNo;
        this.degree = degree;
        this.experience = experience;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
