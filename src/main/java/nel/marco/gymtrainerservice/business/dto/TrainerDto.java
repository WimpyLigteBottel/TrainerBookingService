package nel.marco.gymtrainerservice.business.dto;

public class TrainerDto {

  private long id;
  private long detailId;
  private GymDto gymDto;
  private String name;
  private String contactNumber;
  private String email;
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDetailId() {
    return detailId;
  }

  public void setDetailId(long detailId) {
    this.detailId = detailId;
  }

  public GymDto getGymDto() {
    return gymDto;
  }

  public void setGymDto(GymDto gymDto) {
    this.gymDto = gymDto;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
