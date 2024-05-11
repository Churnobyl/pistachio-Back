package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.donate.Donation;

import java.util.List;

public interface DonateDao {

    // 해당 유저의 기부 전체 조회
    public List<Donation> selectAllByUserId(int userId);

    // 기부 상세 조회
    public Donation selectByDonationId(int donationId);

    // 기부하기
    public int createDonation(Donation donation);
}
