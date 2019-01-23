package cropx.election.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cropx.election.db.entity.Campaign;
import cropx.election.db.repository.CampaignRepository;

@RestController
@RequestMapping(value = "/election", consumes = "application/json", produces = "application/json")
public class ElectionController {

	@Autowired
	private CampaignRepository campaignRepository;

	@RequestMapping(value = "/campaign", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Optional<Campaign>> getCampaigns() {
		return ResponseEntity.ok(campaignRepository.findAll().stream().findAny());
	}
}
