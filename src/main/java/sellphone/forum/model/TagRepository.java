package sellphone.forum.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    Tag findByName(String name);
}
