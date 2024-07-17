package sellphone.forum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.forum.model.CommentRepository;
import sellphone.forum.model.Post;
import sellphone.forum.model.PostRepository;
import sellphone.forum.model.Tag;
import sellphone.forum.model.TagRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    private CommentRepository commentRepo;

    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    public Post findPostById(Integer postId) {
        Optional<Post> optional = postRepo.findById(postId);
        return optional.orElse(null);
    }

    public Page<Post> findPostsByTitle(String title, Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 5, Sort.Direction.DESC, "postCreatedTime");
        return postRepo.findByTitleContainingIgnoreCase(title, pgb);
    }

    public void deletePostById(Integer postId) {
        postRepo.deleteById(postId);
    }

    public Post findLatestPost() {
        Pageable pgb = PageRequest.of(0, 1, Sort.Direction.DESC, "postCreatedTime");
        Page<Post> page = postRepo.findLatest(pgb);
        return page.getContent().get(0);
    }

    public Page<Post> findByPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "postCreatedTime");
        return postRepo.findAll(pgb);
    }

    public Post savePostWithTags(Post post, List<String> tagNames) {
        List<Tag> tags = tagNames.stream()
                                  .map(name -> tagRepo.findByName(name))
                                  .collect(Collectors.toList());
        post.setTags(tags);
        return postRepo.save(post);
    }

    public List<Post> findPostsByTag(String tagName) {
        Tag tag = tagRepo.findByName(tagName);
        return tag != null ? tag.getPosts() : Collections.emptyList();
    }
    
}