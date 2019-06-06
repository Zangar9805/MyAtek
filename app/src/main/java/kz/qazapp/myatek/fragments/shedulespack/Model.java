package kz.qazapp.myatek.fragments.shedulespack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("kurs")
    @Expose
    private int kurs;
    @SerializedName("group_id")
    @Expose
    private int groupId;
    @SerializedName("day")
    @Expose
    private int day;
    @SerializedName("sub1")
    @Expose
    private String sub1;
    @SerializedName("sub1_teach")
    @Expose
    private String sub1Teach;
    @SerializedName("sub1_lec")
    @Expose
    private String sub1Lec;
    @SerializedName("sub2")
    @Expose
    private String sub2;
    @SerializedName("sub2_teach")
    @Expose
    private String sub2Teach;
    @SerializedName("sub2_lec")
    @Expose
    private String sub2Lec;
    @SerializedName("sub3")
    @Expose
    private String sub3;
    @SerializedName("sub3_teach")
    @Expose
    private String sub3Teach;
    @SerializedName("sub3_lec")
    @Expose
    private String sub3Lec;
    @SerializedName("sub4")
    @Expose
    private String sub4;
    @SerializedName("sub4_teach")
    @Expose
    private String sub4Teach;
    @SerializedName("sub4_lec")
    @Expose
    private String sub4Lec;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String getSub1Teach() {
        return sub1Teach;
    }

    public void setSub1Teach(String sub1Teach) {
        this.sub1Teach = sub1Teach;
    }

    public String getSub1Lec() {
        return sub1Lec;
    }

    public void setSub1Lec(String sub1Lec) {
        this.sub1Lec = sub1Lec;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getSub2Teach() {
        return sub2Teach;
    }

    public void setSub2Teach(String sub2Teach) {
        this.sub2Teach = sub2Teach;
    }

    public String getSub2Lec() {
        return sub2Lec;
    }

    public void setSub2Lec(String sub2Lec) {
        this.sub2Lec = sub2Lec;
    }

    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }

    public String getSub3Teach() {
        return sub3Teach;
    }

    public void setSub3Teach(String sub3Teach) {
        this.sub3Teach = sub3Teach;
    }

    public String getSub3Lec() {
        return sub3Lec;
    }

    public void setSub3Lec(String sub3Lec) {
        this.sub3Lec = sub3Lec;
    }

    public String getSub4() {
        return sub4;
    }

    public void setSub4(String sub4) {
        this.sub4 = sub4;
    }

    public String getSub4Teach() {
        return sub4Teach;
    }

    public void setSub4Teach(String sub4Teach) {
        this.sub4Teach = sub4Teach;
    }

    public String getSub4Lec() {
        return sub4Lec;
    }

    public void setSub4Lec(String sub4Lec) {
        this.sub4Lec = sub4Lec;
    }

}
