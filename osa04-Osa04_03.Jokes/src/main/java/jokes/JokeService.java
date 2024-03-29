package jokes;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    @Autowired
    private RestTemplate restTemplate;

    public Joke getRandomJoke() {
        JsonNode node = this.restTemplate.getForObject("https://api.chucknorris.io/jokes/random", JsonNode.class);
        return jokeFromNode(node);
    }

    public Joke findJokeById(Long id) {
        JsonNode node = this.restTemplate.getForObject("https://api.chucknorris.io/jokes/" + id, JsonNode.class);
        return jokeFromNode(node);
    }

    private Joke jokeFromNode(JsonNode node) {

        Joke joke = new Joke();
        joke.setId();
        joke.setJoke(node.get("value").asText());

        return joke;
    }
}
