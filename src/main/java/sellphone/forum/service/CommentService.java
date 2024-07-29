package sellphone.forum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.Users;
import sellphone.forum.model.Comment;
import sellphone.forum.model.CommentRepository;
import sellphone.forum.model.Post;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;
    
    @Autowired
    private UserRepository userRepo;

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public Comment findCommentById(Integer commentId) {
        Optional<Comment> optional = commentRepo.findById(commentId);
        return optional.orElse(null);
    }

    public List<Comment> findCommentsByPostId(Post post) {
        return commentRepo.findByPost(post);
    }

    public void deleteCommentById(Integer commentId) {
        commentRepo.deleteById(commentId);
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