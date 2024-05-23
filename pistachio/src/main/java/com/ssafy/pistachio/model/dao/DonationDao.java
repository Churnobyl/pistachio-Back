package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.Membership;
import com.ssafy.pistachio.model.dto.donate.request.*;
import com.ssafy.pistachio.model.dto.donate.response.DonateProjectResponse;
import com.ssafy.pistachio.model.dto.donate.response.DonationResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;

import java.util.List;

public interface DonationDao {
    /* 기부 */
    // 피스타 추가하기
    public int updatePista(AddPistaRequest addPistaRequest);

    // 피스타 차감하기
    public int subPista(DonationRequest donationRequest);

    // 해당 유저의 기부 전체 조회
    public List<DonationResponse> selectAllByUserId(Long userId);

    // 기부하기
    public int createDonation(DonationRequest donationRequest);

    // 자랑 여부 업데이트
    public int updateBoast(FeedRequest feedRequest);


    /* 단체 */
    // 단체 생성
    public Long createMembership(AddMembershipRequest addMembershipRequest);

    /* 프로젝트 */

    // 단체의 프로젝트 생성
    public long createDonateProject(AddDonateProjectRequest addDonateProjectRequest);

    // 특정 단체의 프로젝트 전체 조회
    public List<DonateProjectResponse> selectAllDonateProjectByAgencyId(Long agencyId);

    // 특정 단체의 프로젝트 상세 조회
    public DonateProjectResponse selectOneDonateProjectById(Long projectId);

    // 프로젝트에 기부금 추가
    public int updateAmountForDonateProject(DonationRequest donationRequest);

    // 프로젝트 참여자 추가
    public int updateProjectParticipant(long projectId);

    /* 피스타치오와 단체 */
    // 피스타치오 프로젝트 가입
    public int insertAffiliation(AffiliationRequest affiliationRequest);

    // 해당 유저 가입 정보 확인
    public long selectAffiliationByUserId(long userId);

    // 피스타치오 프로젝트 탈퇴
    public int deleteAffiliation(long userId);
}
