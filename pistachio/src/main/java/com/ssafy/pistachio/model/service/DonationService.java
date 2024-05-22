package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.request.AddDonateProjectRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddMembershipRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddPistaRequest;
import com.ssafy.pistachio.model.dto.donate.request.DonationRequest;

import java.util.List;

public interface DonationService {

    /* 기부 */
    // 피스타 추가하기
    public int rechargePista(AddPistaRequest addPistaRequest);

    // 해당 유저의 기부 전체 조회
    public List<Donation> getDonationsByUser(Long userId);

    // 기부 상세 조회
    public Donation getDonationById(Long donationId);

    // 기부하기
    public int donation(DonationRequest donationRequest);

    /* 단체 */
    // 단체 생성
    public Long makeMembership(Long userId, AddMembershipRequest addMembershipRequest);

    /* 프로젝트 */

    // 단체의 프로젝트 생성
    public int makeDonateProject(Long userId, AddDonateProjectRequest addDonateProjectRequest);

    // 특정 단체의 프로젝트 전체 조회
    public List<DonateProject> getDonateProjectsByAgencyId(Long agencyId);

    // 특정 단체의 프로젝트 상세 조회
    public DonateProject getDonateProjectById(Long donateProjectId);
}
