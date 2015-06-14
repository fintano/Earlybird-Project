package kr.co.lguplus.last;

import java.io.Serializable;

/**
 * Created by we on 2015-05-29.
 */
public class loginStruct implements Serializable {
    String ID,PW,age,name;
    int gender,type;
    loginStruct(String ID, String PW, String name,String age, int gender, int type){
        this.ID = ID;
        this.age = age;
        this.gender =gender;
        this.name = name;
        this.PW =PW;
        this.type =type;
    }
}

class ReadingRoomStruct extends loginStruct{
    String RRname, fromDate, toDate, seatNo;

    ReadingRoomStruct(String ID, String PW, String name,String age, int gender, int type){
        super(ID,PW,name,age,gender,type);
    }
    public void setName(String name){
        this.RRname = name;
    }
    public void setFromDate(String fromDate){
        this.fromDate = fromDate;
    }
    public void setToDaTe(String toDaTe){
        this.toDate = toDaTe;
    }
    public void setSeatNo(String seatNo){
        this.seatNo = seatNo;
    }
}
