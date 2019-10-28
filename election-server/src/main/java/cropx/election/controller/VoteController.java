package cropx.election.controller;

import cropx.election.db.entity.Campaign;
import cropx.election.db.entity.User;
import cropx.election.db.entity.UserCandidateInCampaign;
import cropx.election.db.repository.CampaignRepository;
import cropx.election.db.repository.UserCandidateInCampaignRepository;
import cropx.election.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//http://localhost:8080/election/vote?campaign=1&user=2&candidate=9
@RestController
public class VoteController
{

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCandidateInCampaignRepository userCandidateInCampaignRepository;

    @RequestMapping(
            value = "/election/vote",
            params = { "campaign", "user","candidate" },
            method = RequestMethod.GET,
            produces = "application/json" )
    @ResponseBody
    public ResponseEntity<User> getBarBySimplePathWithExplicitRequestParams(
            @RequestParam("campaign") Integer campaignId , @RequestParam("user") int userID, @RequestParam("candidate") int candidateId) throws Exception {

        Optional<Campaign> optionalCampaign = campaignRepository.findById(campaignId);

        if (optionalCampaign.isPresent() && optionalCampaign.get().isOpen())
        {

//            Optional<UserCandidateInCampaign> userCandidateInCampaign = userCandidateInCampaignRepository.findById(userCandidateInCampaignRepository.findUserCandidateInCampaignIdBy(campaignId, userID).get());
            Optional<UserCandidateInCampaign> userCandidateInCampaign = userCandidateInCampaignRepository.findUserCandidateInCampaignBy(campaignId, userID);

            if (userCandidateInCampaign.isPresent())
            {
                updateCandidate(candidateId, userCandidateInCampaign.get());
            } else {
                UserCandidateInCampaign tmp = createUserCandidateInCampaign(optionalCampaign, userID, candidateId);

                userCandidateInCampaignRepository.save(tmp);
            }

            return ResponseEntity.ok(userRepository.findById(candidateId).get());
        }
        else
        {
            throw new Exception("Campaign is Closed");
        }
    }

    private UserCandidateInCampaign createUserCandidateInCampaign(Optional<Campaign> optionalCampaign, int userIDInt, int candidateIdInt) throws Exception
    {
        Optional<User> currentUser = userRepository.findById(userIDInt);
        if(!currentUser.isPresent())
            throw new Exception("Current user does no exist");

        if(!optionalCampaign.isPresent())
            throw new Exception("Campaign does no exist");

        UserCandidateInCampaign tmp = new UserCandidateInCampaign();

        tmp.setCampaign(optionalCampaign.get());
        tmp.setChoosingUser(currentUser.get());
        updateCandidate(candidateIdInt, tmp);
        return tmp;
    }

    private void updateCandidate(int candidateIdInt, UserCandidateInCampaign userCandidateInCampaign) throws Exception
    {
        Optional<User> optionalUser = userRepository.findById(candidateIdInt);
        if(optionalUser.isPresent())
        {
            userCandidateInCampaign.setChosenCandidate(optionalUser.get());
            userCandidateInCampaignRepository.save(userCandidateInCampaign);
        }

        else
            throw new Exception("Candidate Does Not Exist");
    }

}
