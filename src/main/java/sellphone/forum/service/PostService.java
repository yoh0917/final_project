package sellphone.forum.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.Users;
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
    
    @Autowired
    private UserRepository userRepo;

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
    
    public boolean hasUserLikedPost(Integer postId, String userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getLikedUsers().stream().anyMatch(user -> user.getUserId().equals(userId));
    }

    public boolean toggleLike(Integer postId, String userId) {
        if (postId == null || userId == null) {
            throw new IllegalArgumentException("Post ID and User ID must not be null");
        }
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (post.getLikedUsers().contains(user)) {
            post.getLikedUsers().remove(user);
            post.setLikeCount(post.getLikeCount() - 1);
            postRepo.save(post);
            return false;
        } else {
            post.getLikedUsers().add(user);
            post.setLikeCount(post.getLikeCount() + 1);
            postRepo.save(post);
            return true;
        }
    }
    
}