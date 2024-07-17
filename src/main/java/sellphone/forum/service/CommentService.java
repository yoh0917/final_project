package sellphone.forum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.forum.model.Comment;
import sellphone.forum.model.CommentRepository;
import sellphone.forum.model.Post;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

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

    
}