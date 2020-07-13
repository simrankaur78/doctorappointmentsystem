package com.nexogichealthcare.DaoInter;

import com.nexogichealthcare.models.Otp;

public interface OtpDao {
public int insertOtp(Otp otp);
public Otp getOtpByEmail(String email);

}
