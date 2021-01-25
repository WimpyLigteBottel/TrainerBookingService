package nel.marco.gymtrainerservice.business.dto;

import java.util.Date;

public class GymDto {

  private long id;
  private String name;
  private Date inserted;
  private Date updated;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
