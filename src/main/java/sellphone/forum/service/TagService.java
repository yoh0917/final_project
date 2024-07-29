package sellphone.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sellphone.forum.model.PostTagRepository;
import sellphone.forum.model.Tag;
import sellphone.forum.model.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    private PostTagRepository postTagRepo;

    public List<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    public Tag findByName(String name) {
        return tagRepo.findByName(name);
    }

    public Tag save(Tag tag) {
        return tagRepo.save(tag);
    }
    @Transactional
    public void deleteTagById(Integer tagId) {
        // 删除关联的 POST_TAG 记录
        postTagRepo.deleteByTagId(tagId);
        // 删除标签
        tagRepo.deleteById(tagId);
    }
    
}