package cropx.election.db.repository;

import cropx.election.db.entity.Campaign;
import cropx.election.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
