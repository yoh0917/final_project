package sellphone.forum.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;
import sellphone.user.model.Users;
import sellphone.user.service.UserService;
import sellphone.forum.model.Comment;
import sellphone.forum.model.CommentRepository;
import sellphone.forum.model.Post;
import sellphone.forum.model.PostRepository;
import sellphone.forum.service.CommentService;
import sellphone.forum.service.PostService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private PostRepository postRepository;

	

	@PostMapping("/comment/add")
	public String addComment(
	    @RequestParam(value = "commentContent", required = false) String commentContent,
	    @RequestParam("postId") Integer postId,
	    @RequestParam("userId") String userId,
	    @RequestParam(value = "parentCommentId", required = false) Integer parentCommentId,
	    @RequestParam(value = "action", required = false) String action,
	    RedirectAttributes redirectAttributes) {

	    // 檢查是否為一鍵輸入
	    if ("autoFill".equals(action)) {
	        commentContent = "感謝分享這篇文章，內容很有意思！";
	    }

	    // 檢查評論內容是否為空
	    if (commentContent == null || commentContent.trim().isEmpty()) {
	        redirectAttributes.addFlashAttribute("errorMessage", "留言內容不能為空");
	        return "redirect:/post/" + postId;
	    }
	    //檢查使用者狀態
	    Users user = userService.findById(userId);
	    if (user.getStatus() == -3) {
	    	redirectAttributes.addFlashAttribute("restrictionMessage", "您的帳戶已被禁用，無法新增留言。");
	        return "redirect:/post/" + postId;
	    }

	    Post post = postService.findPostById(postId);
//	    Users user = userService.findById(userId);

	    if (post != null && user != null) {
	        Comment comment = new Comment();
	        comment.setCommentContent(commentContent);
	        comment.setPost(post);
	        comment.setUsers(user);

	        if (parentCommentId != null) {
	            Comment parentComment = commentService.findCommentById(parentCommentId);
	            comment.setParentComment(parentComment);
	        }

	        commentService.saveComment(comment);
	        redirectAttributes.addFlashAttribute("successMessage", "留言已新增");
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "文章或用戶不存在");
	    }

	    return "redirect:/post/" + postId;
	}
	

	@PostMapping("/comment/edit")
	@ResponseBody
	public ResponseEntity<?> editComment(
	        @RequestParam("commentId") Integer commentId,
	        @RequestParam("commentContent") String commentContent,
	        @RequestParam(value = "postId", required = false) Integer postId) {
	    
//	    logger.info("Received edit request - commentId: {}, content: {}, postId: {}", commentId, commentContent, postId);
	    
	    Optional<Comment> commentOptional = commentRepository.findById(commentId);
	    if (commentOptional.isPresent()) {
	        Comment comment = commentOptional.get();
	        comment.setCommentContent(commentContent);
	        commentRepository.save(comment);
	        return ResponseEntity.ok().body(Map.of("success", true, "message", "Comment updated successfully"));
	    } else {
	        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Comment not found"));
	    }
	}
	
	@PostMapping("/comment/delete")
	public String deleteComment(@RequestParam("commentId") Integer commentId,
	                            @RequestParam("postId") Integer postId,
	                            Model model) {
	    commentRepository.deleteById(commentId);
	    return "redirect:/post/" + postId; 
	}
	
	@PostMapping("/comment/like/toggle")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> toggleCommentLike(@RequestParam("commentId") Integer commentId, HttpSession session) {
	    String userId = (String) session.getAttribute("userId");
	    if (userId == null) {
	        throw new RuntimeException("User is not logged in");
	    }
	    boolean hasLiked = commentService.toggleLike(commentId, userId);

	    Map<String, Object> response = new HashMap<>();
	    response.put("hasLiked", hasLiked);
	    response.put("likeCount", commentService.findCommentById(commentId).getLikeCount());
	    return ResponseEntity.ok(response);
	}
	@GetMapping("/reportComment/{commentId}/{postId}")
    public String reportComment(@PathVariable Integer commentId, @PathVariable Integer postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentService.findCommentById(commentId);
        if (comment != null) {
            Users user = comment.getUsers();
            if (user != null) {
                user.setReportNum(user.getReportNum() + 1);
                userService.insert(user);
                redirectAttributes.addFlashAttribute("message", "檢舉成功 將由管理員進行審核!");
                return "redirect:/post/" + postId;
            }
        }
        redirectAttributes.addFlashAttribute("error", "檢舉失敗");
        return "redirect:/post/" + postId;
    }
	
	
}
