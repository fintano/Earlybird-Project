package kr.co.lguplus.last;

import java.util.LinkedList;

/**
 * Created by we on 2015-06-01.
 */
class UserInfo {
    public static final int FROM_PAY = 1;
    public static final int FROM_LOGIN = 0;
    public static LinkedList<loginStruct> user= new LinkedList();
    private static ReadingRoomStruct currentUser; // null�ϰ��?
    public static void setUserInfo(loginStruct ls) {
        currentUser = new ReadingRoomStruct(ls.ID,ls.PW,ls.name,ls.ID,ls.gender,ls.type);
    }
    public static ReadingRoomStruct  getUserInfo(){
        return currentUser;


    }

    /*public static void setReadingRoomInfo(ReadingRoomStruct rr) {
        currentUserReadingRoom = new ReadingRoomStruct(rr.name,rr.fromDate, rr.toDate, rr.seatNo);
    }
    public static ReadingRoomStruct getCurrentUserReadingRoomInfo(){
        return currentUserReadingRoom;
    }
    */
}
