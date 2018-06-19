package com.duylh.hoisieukho.Model;

/**
 * Created by Admin on 18/02/2017.
 */

public class CauHoi {
    public int Id;
    public int TTCauHoi;
    public String CauHoi;
    public String DapanA;
    public String DapanB;
    public String DapanC;
    public String DapanD;
    public String DapAn;

    public CauHoi(int id, int ttCauHoi, String cauHoi, String dapanA, String dapanB, String dapanC, String dapanD, String dapAn) {
        Id = id;
        TTCauHoi = ttCauHoi;
        CauHoi = cauHoi;
        DapanA = dapanA;
        DapanB = dapanB;
        DapanC = dapanC;
        DapanD = dapanD;
        DapAn = dapAn;
    }
}
