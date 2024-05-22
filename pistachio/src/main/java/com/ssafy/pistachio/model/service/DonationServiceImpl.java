package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.DonationDao;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.donate.DonateProject;
import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.request.AddDonateProjectRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddMembershipRequest;
import com.ssafy.pistachio.model.dto.donate.request.AddPistaRequest;
import com.ssafy.pistachio.model.dto.donate.request.DonationRequest;
import com.ssafy.pistachio.model.dto.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    private DonationDao donationDao;
    private UserDao userDao;

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
    public List<Donation> getDonationsByUser(Long userId) {
        return donationDao.selectAllByUserId(userId);
    }

    @Override
    public Donation getDonationById(Long donationId) {
        return donationDao.selectByDonationId(donationId);
    }

    @Override
    public int donation(DonationRequest donationRequest) {
        return donationDao.createDonation(donationRequest);
    }

    @Transactional
    @Override
    public Long makeMembership(Long userId, AddMembershipRequest addMembershipRequest) throws IllegalArgumentException {
        int role = userDao.getRole(userId);

        if (role != 3) {
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

        if (user.getMembershipId() == 1) {
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
}
