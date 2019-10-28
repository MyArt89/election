package cropx.election.db.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "campaign")
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private Date finish;

	public Campaign() {
	}

	public Campaign(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}
	public boolean isOpen()
	{
		Date currentDate = new Date();
		return finish.after(currentDate) && start.before(currentDate);
	}
}
