package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.request.*;
import com.ssafy.pistachio.model.dto.donate.response.DonateProjectResponse;
import com.ssafy.pistachio.model.dto.donate.response.DonationResponse;

import java.util.List;

public interface DonationService {

    /* 기부 */
    // 피스타 추가하기
    public int rechargePista(AddPistaRequest addPistaRequest);

    // 해당 유저의 기부 전체 조회
    public List<DonationResponse> getDonationsByUser(Long userId);

    // 기부하기
    public int donation(Long userId, DonationRequest donationRequest);

    /* 단체 */
    // 단체 생성
    public Long makeMembership(Long userId, AddMembershipRequest addMembershipRequest);

    /* 프로젝트 */

    // 단체의 프로젝트 생성
    public long makeDonateProject(Long userId, AddDonateProjectRequest addDonateProjectRequest);

    // 특정 단체의 프로젝트 전체 조회
    public List<DonateProjectResponse> getDonateProjectsByAgencyId(Long agencyId);

    // 특정 단체의 프로젝트 상세 조회
    public DonateProjectResponse getDonateProjectById(Long donateProjectId);
    
    /* 피스타치오와 단체 */

    // 피스타치오의 프로젝트 가입
    public int signupProject(long userId, AffiliationRequest affiliationRequest);

    // 피스타치오 프로젝트 탈퇴
    public int signoutProject(long userId);
}
