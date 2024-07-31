package sellphone.forum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sellphone.user.model.UserRepository;
import sellphone.user.model.Users;
import sellphone.forum.model.Comment;
import sellphone.forum.model.CommentRepository;
import sellphone.forum.model.Post;
import sellphone.forum.model.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;
    
    @Autowired
    private PostRepository postRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Transactional
    public Comment saveComment(Comment comment) {
        Comment savedComment = commentRepo.save(comment);
        Post post = savedComment.getPost();
        post.setCommentCount(post.getCommentCount() + 1);
        postRepo.save(post);
        return savedComment;
    }

    public Comment findCommentById(Integer commentId) {
        Optional<Comment> optional = commentRepo.findById(commentId);
        return optional.orElse(null);
    }

    public List<Comment> findCommentsByPostId(Post post) {
        return commentRepo.findByPost(post);
    }

    @Transactional
    public void deleteCommentById(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        Post post = comment.getPost();
        commentRepo.deleteById(commentId);
        post.setCommentCount(post.getCommentCount() - 1);
        postRepo.save(post);
    }
    
    @Transactional
    public boolean toggleLike(Integer commentId, String userId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (comment.getLikedUsers().contains(user)) {
            comment.getLikedUsers().remove(user);
            comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepo.save(comment);
            return false;
        } else {
            comment.getLikedUsers().add(user);
            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepo.save(comment);
            return true;
        }
    }
    
}