package cropx.election.db.entity;

import javax.persistence.*;


@Entity
@Table(name = "CampaignCandidate")
public class UserCandidateInCampaign
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
	@JoinColumn(name = "campaign",referencedColumnName = "id")
	private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "choosingUser",referencedColumnName = "id")
    private  User choosingUser;

    @ManyToOne
    @JoinColumn(name = "chosenCandidate",referencedColumnName = "id")
    private User chosenCandidate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public User getChoosingUser() {
        return choosingUser;
    }

    public void setChoosingUser(User choosingUser) {
        this.choosingUser = choosingUser;
    }

    public User getChosenCandidate() {
        return chosenCandidate;
    }

    public void setChosenCandidate(User chosenCandidate) {
        this.chosenCandidate = chosenCandidate;
    }
}
