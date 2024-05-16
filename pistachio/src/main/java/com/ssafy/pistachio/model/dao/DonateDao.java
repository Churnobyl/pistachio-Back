package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.Membership;

import java.util.List;

public interface DonateDao {
    /* 기부 */

    // 해당 유저의 기부 전체 조회
    public List<Donation> selectAllByUserId(Long userId);

    // 기부 상세 조회
    public Donation selectByDonationId(Long donationId);

    // 기부하기
    public int createDonation(Donation donation);


    /* 단체 */

    // 단체 생성
    public int createMembership(Membership membership);

    // 단체 조회
    public List<Membership> selectAllMemberShip();

    // 단체 수정
    public int updateMembership(Membership membership);


    /* 프로젝트 */

    // 단체의 프로젝트 생성
    public int createDonateProject(DonateProject donateProject);

    // 모든 단체의 프로젝트 전체 조회
    public List<DonateProject> selectAllDonateProject();

    // 특정 단체의 프로젝트 전체 조회
    public List<DonateProject> selectAllDonateProjectByAgencyId(Long agencyId);

    // 특정 단체의 프로젝트 상세 조회
    public List<DonateProject> selectOneDonateProjectById(Long id);

    // 단체의 프로젝트 수정
    public int updateDonateProject(DonateProject donateProject);

}
