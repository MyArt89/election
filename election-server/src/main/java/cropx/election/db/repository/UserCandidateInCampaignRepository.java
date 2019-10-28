package cropx.election.db.repository;

import cropx.election.db.entity.User;
import cropx.election.db.entity.UserCandidateInCampaign;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface UserCandidateInCampaignRepository extends JpaRepository<UserCandidateInCampaign, Integer>
{
    @Query(
            "SELECT ucic.chosenCandidate FROM UserCandidateInCampaign ucic " +
                    "WHERE ucic.choosingUser.id = (:userId) " +
                    "AND ucic.campaign.id = (:campaignId)")
    List<User> findChosenCandidateBy(@Param("userId") Integer userId, @Param("campaignId") Integer campaignId);

    @Query(
            "SELECT ucic FROM UserCandidateInCampaign ucic " +
                    "WHERE ucic.choosingUser.id = (:userId) " +
                    "AND ucic.campaign.id = (:campaignId)")
    Optional<UserCandidateInCampaign> findUserCandidateInCampaignBy( @Param("campaignId") Integer campaignId,@Param("userId") Integer userId);

    @Query(
            value = " select usercandid.id " +
                    " from campaign_candidate usercandid " +
                    " where usercandid.choosing_user= (:userId) and usercandid.campaign= (:campaignId) "
                    ,nativeQuery = true)
    Optional<Integer> findUserCandidateInCampaignIdBy(@Param("campaignId") Integer campaignId,@Param("userId") Integer userId );



    @Query(
            value = " SELECT distinct c.chosen_candidate FROM campaign_candidate c " +
                    " WHERE c.campaign = (:campaignId )" +
                    " and c.chosen_candidate in ( " +
                    " select cc.chosen_candidate from campaign_candidate cc " +
                    " where cc.campaign = (:campaignId ) " +
                    " GROUP BY cc.chosen_candidate " +
                    " ORDER BY COUNT(cc.chosen_candidate) desc " +
                    " limit 10 "+
                    ")",
            nativeQuery = true

    )
    List<Integer> topTenCandidatesInCampaign(@Param("campaignId") Integer campaignId);


    @Query(
            value = "SELECT count(c.chosen_candidate) FROM campaign_candidate c " +
                    " WHERE c.campaign = (:campaignId ) and c.chosen_candidate = (:candidateId ) "
                    ,
            nativeQuery = true

    )
    Optional<Integer> candidateScoreBy(@Param("campaignId") Integer campaignId, @Param("candidateId") Integer candidateId);
}
