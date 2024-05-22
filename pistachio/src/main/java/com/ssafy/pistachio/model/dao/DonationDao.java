package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.Membership;
import com.ssafy.pistachio.model.dto.donate.request.AddDonateProjectRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddMembershipRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddPistaRequest;
import com.ssafy.pistachio.model.dto.donate.request.DonationRequest;

import java.util.List;

public interface DonationDao {
    /* 기부 */
    // 피스타 추가하기
    public int updatePista(AddPistaRequest addPistaRequest);

    // 해당 유저의 기부 전체 조회
    public List<Donation> selectAllByUserId(Long userId);

    // 기부 상세 조회
    public Donation selectByDonationId(Long donationId);

    // 기부하기
    public int createDonation(DonationRequest donationRequest);


    /* 단체 */
    // 단체 생성
    public Long createMembership(AddMembershipRequest addMembershipRequest);

    /* 프로젝트 */

    // 단체의 프로젝트 생성
    public int createDonateProject(AddDonateProjectRequest addDonateProjectRequest);

    // 특정 단체의 프로젝트 전체 조회
    public List<DonateProject> selectAllDonateProjectByAgencyId(Long agencyId);

    // 특정 단체의 프로젝트 상세 조회
    public DonateProject selectOneDonateProjectById(Long id);

}
