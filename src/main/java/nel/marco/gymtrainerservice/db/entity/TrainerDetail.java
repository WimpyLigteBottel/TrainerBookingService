package nel.marco.gymtrainerservice.db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TrainerDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne private Trainer trainer;

  @Column(unique = false, length = 100, nullable = true)
  private String description;

  @Column(unique = false, length = 20, nullable = true)
  private String contactNumber;

  @Column(unique = false, length = 20, nullable = false)
  private String email;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date inserted;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public Trainer getTrainer() {
    return trainer;
  }

  public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
  }

  public Date getInserted() {
    return inserted;
  }

  public void setInserted(Date inserted) {
    this.inserted = inserted;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
