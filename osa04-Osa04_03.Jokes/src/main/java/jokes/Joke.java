package jokes;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Joke {

    private Long id;
    private String joke;

    public Long getId() {
        return id;
    }

    public void setId() {
        Long randomLongUuid = generateRandomLongUuid();
        this.id = randomLongUuid;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
    
    private long generateRandomLongUuid() {
        UUID uuid = UUID.randomUUID();

        // Extract the most significant bits and concatenate them with the least significant bits
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        return (mostSignificantBits << 32) | (leastSignificantBits & 0xFFFFFFFFL);
    }

}
