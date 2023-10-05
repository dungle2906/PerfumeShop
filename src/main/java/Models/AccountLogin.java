/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author User
 */
public class AccountLogin {

    String Account_ID;
    String Fullname;
    String Username;
    String Password;
    String Mobile_Number;
    String Email;
    String Address;
    String IsAdmin;
    String Gender;
    String Dob;

    public AccountLogin() {
    }

    public AccountLogin(String Account_ID, String Fullname, String Username, String Password, String Mobile_Number, String Email, String Address, String IsAdmin, String Gender, String Dob) {
        this.Account_ID = Account_ID;
        this.Fullname = Fullname;
        this.Username = Username;
        this.Password = Password;
        this.Mobile_Number = Mobile_Number;
        this.Email = Email;
        this.Address = Address;
        this.IsAdmin = IsAdmin;
        this.Gender = Gender;
        this.Dob = Dob;
    }

    public String getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(String Account_ID) {
        this.Account_ID = Account_ID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String Mobile_Number) {
        this.Mobile_Number = Mobile_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

}
