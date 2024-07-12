package sellphone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.model.Tag;
import sellphone.model.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepo;

    public List<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    public Tag findByName(String name) {
        return tagRepo.findByName(name);
    }

    public Tag save(Tag tag) {
        return tagRepo.save(tag);
    }
    
    
}