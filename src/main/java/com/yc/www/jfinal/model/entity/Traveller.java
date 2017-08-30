package com.yc.www.jfinal.model.entity;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Nick on 2017/4/18.
 */
public class Traveller extends Model<Traveller> {
    public static final User dao = new User().dao();

    private int tlrId;
    private String tlrName;
    private String tlrIdCard;
    private String tlrPhoneNumber;
    private String tlrEmail;
    private String tlrSchool;
    private String tlrSchoolLocation;
    private String tlrMajor;
    private String tlrGrade;
    private String tlrIdCardLink;



    public int getTlrId() {
        return tlrId;
    }

    public void setTlrId(int tlrId) {
        this.tlrId = tlrId;
    }

    public String getTlrName() {
        return tlrName;
    }

    public void setTlrName(String tlrName) {
        this.tlrName = tlrName;
    }

    public String getTlrIdCard() {
        return tlrIdCard;
    }

    public void setTlrIdCard(String tlrIdCard) {
        this.tlrIdCard = tlrIdCard;
    }

    public String getTlrPhoneNumber() {
        return tlrPhoneNumber;
    }

    public void setTlrPhoneNumber(String tlrPhoneNumber) {
        this.tlrPhoneNumber = tlrPhoneNumber;
    }

    public String getTlrEmail() {
        return tlrEmail;
    }

    public void setTlrEmail(String tlrEmail) {
        this.tlrEmail = tlrEmail;
    }

    public String getTlrSchool() {
        return tlrSchool;
    }

    public void setTlrSchool(String tlrSchool) {
        this.tlrSchool = tlrSchool;
    }

    public String getTlrSchoolLocation() {
        return tlrSchoolLocation;
    }

    public void setTlrSchoolLocation(String tlrSchoolLocation) {
        this.tlrSchoolLocation = tlrSchoolLocation;
    }

    public String getTlrMajor() {
        return tlrMajor;
    }

    public void setTlrMajor(String tlrMajor) {
        this.tlrMajor = tlrMajor;
    }

    public String getTlrGrade() {
        return tlrGrade;
    }

    public void setTlrGrade(String tlrGrade) {
        this.tlrGrade = tlrGrade;
    }

    public String getTlrIdCardLink() {
        return tlrIdCardLink;
    }

    public void setTlrIdCardLink(String tlrIdCardLink) {
        this.tlrIdCardLink = tlrIdCardLink;
    }
}
