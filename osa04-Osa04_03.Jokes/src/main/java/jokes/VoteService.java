package jokes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepo;

    public List<Vote> list() {
        return voteRepo.findAll();
    }

    @Transactional
    public Vote findVoteByJokeId(Long jokeId) {
        if (this.voteRepo.findByJokeId(jokeId) == null) {
            Vote v = new Vote(jokeId, 0, 0);
            this.voteRepo.save(v);
        }
        return voteRepo.findByJokeId(jokeId);
    }

    @Transactional
    public void vote(Long id, String value) {
        Vote vote = this.findVoteByJokeId(id);

        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
    }
}
