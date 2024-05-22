package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.donate.Donation;
import com.ssafy.pistachio.model.dto.donate.request.*;
import com.ssafy.pistachio.model.dto.donate.response.DonationResponse;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.service.DonationService;
import com.ssafy.pistachio.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donate")
public class DonateController {

    private final UserService userService;
    private final DonationService donationService;

    public DonateController(UserService userService,
                            DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @PostMapping("")
    public ResponseEntity<?> createDonation(HttpSession session, @RequestBody DonationRequest donationRequest) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            donationService.donation(dbUser.getId(), donationRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDonation(HttpSession session) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<DonationResponse> donations = donationService.getDonationsByUser(dbUser.getId());
        return ResponseEntity.ok(donations);
    }


    @PostMapping("/recharge")
    public ResponseEntity<?> recharge(HttpSession session,
                                      @RequestBody AddPistaRequest addPistaRequest
    ) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        addPistaRequest.setUserId(dbUser.getId());
        donationService.rechargePista(addPistaRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/membership")
    public ResponseEntity<?> createMembership(HttpSession session,
                                              @RequestBody AddMembershipRequest addMembershipRequest
    ) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (dbUser.getMembershipId() != 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            donationService.makeMembership(dbUser.getId(), addMembershipRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/project")
    public ResponseEntity<?> createDonateProject(HttpSession session,
                                                 @RequestBody AddDonateProjectRequest addDonateProjectRequest) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (dbUser.getMembershipId() == 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            donationService.makeDonateProject(dbUser.getId(), addDonateProjectRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.toString());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/affiliate")
    public ResponseEntity<?> signupProjectForPistachio(HttpSession session,
                                                       @RequestBody AffiliationRequest affiliationRequest) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
        donationService.signupProject(dbUser.getId(), affiliationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.toString());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/affiliate")
    public ResponseEntity<?> signoutProjectForPistachio(HttpSession session) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            donationService.signoutProject(dbUser.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.toString());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
