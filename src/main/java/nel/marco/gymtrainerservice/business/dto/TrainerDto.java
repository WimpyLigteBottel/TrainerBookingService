package nel.marco.gymtrainerservice.business.dto;

public class TrainerDto {

    private long id;
    private GymDto gymDto;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
