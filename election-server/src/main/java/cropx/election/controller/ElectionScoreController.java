package cropx.election.controller;

import cropx.election.db.entity.Campaign;
import cropx.election.db.entity.CandidateScoreAtCampaign;
import cropx.election.db.repository.CampaignRepository;
import cropx.election.db.repository.UserCandidateInCampaignRepository;
import cropx.election.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//http://localhost:8080/electionScore/campaign?Id=1
@RestController
@RequestMapping(value = "election/electionScore", produces = "application/json")
public class ElectionScoreController
{

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserCandidateInCampaignRepository userCandidateInCampaignRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/campaign",produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<CandidateScoreAtCampaign>> getCampaigns(@RequestParam Integer Id)
    {
        int campaignIdInt =  Id;//Integer.parseInt(Id);
        List<CandidateScoreAtCampaign> candidateScoreAtCampaignList = new ArrayList<>();

        List<Integer> UsersId = userCandidateInCampaignRepository.topTenCandidatesInCampaign(campaignIdInt);
        Campaign campaign = campaignRepository.findById(campaignIdInt).get();
        CandidateScoreAtCampaign tmpCandidateScoreAtCampaign ;
        for(Integer userId :UsersId )
        {
            tmpCandidateScoreAtCampaign = new CandidateScoreAtCampaign();
            tmpCandidateScoreAtCampaign.setCampaign(campaign);
            tmpCandidateScoreAtCampaign.setUser(userRepository.findById(userId).get());
            tmpCandidateScoreAtCampaign.setScore(userCandidateInCampaignRepository.candidateScoreBy(campaign.getId(),userId).get());
            candidateScoreAtCampaignList.add(tmpCandidateScoreAtCampaign);
        }

        return ResponseEntity.ok( candidateScoreAtCampaignList);
    }
}