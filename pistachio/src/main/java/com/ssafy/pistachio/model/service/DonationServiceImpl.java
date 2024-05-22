package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.DonationDao;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.request.*;
import com.ssafy.pistachio.model.dto.donate.response.DonationResponse;
import com.ssafy.pistachio.model.dto.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    private DonationDao donationDao;
    private UserDao userDao;

    private final long AGENCY_ROLE = 3;
    private final long PISTACHIO_ROLE = 2;
    private final long NORMAL_MEMBERSHIP_ID = 1;


    public DonationServiceImpl(DonationDao donationDao,
                               UserDao userDao) {
        this.donationDao = donationDao;
        this.userDao = userDao;
    }

    @Override
    public int rechargePista(AddPistaRequest addPistaRequest) {
        return donationDao.updatePista(addPistaRequest);
    }

    @Override
    public List<DonationResponse> getDonationsByUser(Long userId) {
        return donationDao.selectAllByUserId(userId);
    }

    @Transactional
    @Override
    public int donation(Long userId, DonationRequest donationRequest) throws IllegalArgumentException {
        User user = userDao.getUserById(userId);
        DonateProject donateProject = donationDao.selectOneDonateProjectById(donationRequest.getProjectId());

        if (user.getPista() < donationRequest.getAmount()) {
            throw new IllegalArgumentException("돈이 적음");
        } else if (user.getMembershipId() == donateProject.getAgencyId()) {
            throw  new IllegalArgumentException("본인이 소속된 단체에는 기부할 수 없습니다.");
        }

        donationRequest.setUserId(userId);
        donationDao.createDonation(donationRequest);
        donationDao.updateAmountForDonateProject(donationRequest);
        donationDao.subPista(donationRequest);
        return 1;
    }

    @Transactional
    @Override
    public Long makeMembership(Long userId, AddMembershipRequest addMembershipRequest) throws IllegalArgumentException {
        int role = userDao.getRole(userId);

        if (role != AGENCY_ROLE) {
            throw new IllegalArgumentException("Invalid role");
        }

        addMembershipRequest.setAgencyProfileUrl(userDao.getUserById(userId).getUserProfile());
        donationDao.createMembership(addMembershipRequest);
        userDao.updateUserMembership(userId, addMembershipRequest.getId());
        return 1L;
    }

    @Transactional
    @Override
    public int makeDonateProject(Long userId, AddDonateProjectRequest addDonateProjectRequest) throws IllegalArgumentException {
        User user = userDao.getUserById(userId);

        if (user.getMembershipId() == NORMAL_MEMBERSHIP_ID) {
            throw new IllegalArgumentException("Invalid user");
        }

        addDonateProjectRequest.setAgencyId(user.getMembershipId());
        return donationDao.createDonateProject(addDonateProjectRequest);
    }

    @Override
    public List<DonateProject> getDonateProjectsByAgencyId(Long agencyId) {
        return donationDao.selectAllDonateProjectByAgencyId(agencyId);
    }

    @Override
    public DonateProject getDonateProjectById(Long donateProjectId) {
        return donationDao.selectOneDonateProjectById(donateProjectId);
    }

    @Transactional
    @Override
    public int signupProject(long userId,
                             AffiliationRequest affiliationRequest) throws IllegalArgumentException {
        long result = donationDao.selectAffiliationByUserId(userId);

        if (result > 0) {
            throw new IllegalArgumentException("이미 가입된 프로젝트가 있습니다.");
        } else if (userDao.getRole(userId) != PISTACHIO_ROLE) {
            throw  new IllegalArgumentException("피스타치오만 가입할 수 있습니다.");
        }

        affiliationRequest.setUserId(userId);
        DonateProject donateProject = donationDao.selectOneDonateProjectById(affiliationRequest.getProjectId());
        donationDao.insertAffiliation(affiliationRequest);
        userDao.updateUserMembership(affiliationRequest.getUserId(), donateProject.getAgencyId());
        return 1;
    }

    @Transactional
    @Override
    public int signoutProject(long userId) throws IllegalArgumentException {
        long result = donationDao.selectAffiliationByUserId(userId);

        if (result == 0) {
            throw new IllegalArgumentException("가입된 프로젝트가 없습니다.");
        } else if (userDao.getRole(userId) != PISTACHIO_ROLE) {
            throw  new IllegalArgumentException("피스타치오만 탈퇴할 수 있습니다.");
        }

        donationDao.deleteAffiliation(userId);
        userDao.updateUserMembership(userId, NORMAL_MEMBERSHIP_ID);

        return 1;
    }
}
