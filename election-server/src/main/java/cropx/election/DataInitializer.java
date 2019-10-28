package cropx.election;

import cropx.election.db.entity.Campaign;
import cropx.election.db.entity.User;
import cropx.election.db.entity.UserCandidateInCampaign;
import cropx.election.db.repository.CampaignRepository;
import cropx.election.db.repository.UserCandidateInCampaignRepository;
import cropx.election.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer
{
    int numberOfUsers =10;
    ArrayList<User> users;
    Campaign campaign;
    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCandidateInCampaignRepository userCandidateInCampaignRepository;
    Random r = new Random();

    public void Initialize()
    {
        createCampaign();
        createUsers();
        setUsersChoice();
       users.clear();

//       for(Integer id : userCandidateInCampaignRepository.topTenCandidatesInCampaign(1))
//       {
//           users.add(userRepository.findById(id).get());
//       }
//
//       System.out.println(users);
//        System.out.println(userCandidateInCampaignRepository.findUserCandidateInCampaignIdBy(2,1));

    }

    private void createCampaign()
    {
        campaign = new Campaign();
        campaign.setName("SampleCampaign");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()+1000);
        endDate.setMonth(startDate.getMonth()+1);

        campaign.setStart(startDate);
        campaign.setFinish(endDate);

        campaignRepository.save(campaign);
    }

    private void createUsers()
    {
        User tmpUser;
        users = new ArrayList<>();

        for (int i =1 ;i<=numberOfUsers;i++)
        {
            tmpUser = new User();
            tmpUser.setName("User " + i);
           users.add(tmpUser);
        }

        List<User> users = userRepository.saveAll(this.users);
    }

    private void setUsersChoice()
    {
        UserCandidateInCampaign tmpUserCandidateInCampaign;
        for (int i = 1 ; i<= numberOfUsers;i++)
        {
            tmpUserCandidateInCampaign = new UserCandidateInCampaign();
            tmpUserCandidateInCampaign.setCampaign(campaignRepository.findById(1).get());
            tmpUserCandidateInCampaign.setChoosingUser(userRepository.findById(i + 1).get());
            tmpUserCandidateInCampaign.setChosenCandidate(userRepository.findById(r.nextInt(numberOfUsers) + 2).get());
            userCandidateInCampaignRepository.save(tmpUserCandidateInCampaign);
        }
    }
}
