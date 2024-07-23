package sellphone.forum.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import sellphone.dashboard.user.model.Users;
import sellphone.forum.model.Comment;
import sellphone.forum.model.Post;
import sellphone.forum.model.Tag;
import sellphone.forum.service.CommentService;
import sellphone.forum.service.PostService;
import sellphone.forum.service.TagService;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;
    
    @Autowired
    private CommentService commentService;

    @GetMapping("/post/add")
    public String addposts(Model model) {
        Post latestPost = postService.findLatestPost();
        model.addAttribute("latestPost", latestPost);
        return "post/addPostPage";
    }

    @PostMapping("/post/addPost")
    public String addPost(@RequestParam("title") String title, @RequestParam("content") String content,
                          @RequestParam("tags") List<String> tags, Model model) {
        Post post = new Post();
        post.setTitle(title);
        post.setPostContent(content);
        postService.savePostWithTags(post, tags);
        
//        if (!image.isEmpty()) {
//            try {
//                byte[] bytes = image.getBytes();
//                Path path = Paths.get(UPLOAD_DIR + "/" + image.getOriginalFilename());
//                Files.write(path, bytes);
//                post.setImage(path.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        Post latestPost = postService.findLatestPost();
        model.addAttribute("latestPost", latestPost);

        return "redirect:/post/add";
    }

    @GetMapping("/post/page")
    public String findByPage(@RequestParam(value = "p", defaultValue = "1") Integer pageNum, 
                             @RequestParam(value = "keyword", required=false) String keyword, Model model) {
        Page<Post> page = postService.findByPage(pageNum);
        Post latestPost = postService.findLatestPost();
        List<Tag> allTags = tagService.findAllTags();  
        model.addAttribute("latestPost", latestPost);
        model.addAttribute("page", page);
        model.addAttribute("allTags", allTags);  
        return "post/listPostPage";
    }

    @GetMapping("/post/edit")
    public String editPost(@RequestParam Integer id, @RequestParam(value = "p", defaultValue = "1") Integer pageNum, Model model) {
        Post post = postService.findPostById(id);
        List<Tag> allTags = tagService.findAllTags();
        model.addAttribute("post", post);
        model.addAttribute("allTags", allTags);
        model.addAttribute("pageNum", pageNum);
        return "post/editPostPage";
    }

    @PostMapping("/post/editPost")
    public String editPostContent(@ModelAttribute Post post, @RequestParam("pageNum") Integer pageNum) {
        postService.savePost(post);
        return "redirect:/post/page?p=" + pageNum;
    }

    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("id") Integer postId, @RequestParam("p") Integer pageNum) {
        postService.deletePostById(postId);
        return "redirect:/post/page?p=" + pageNum;
    }

    @GetMapping("/post/find")
    public String selectPost(@RequestParam(value = "p", defaultValue = "1") Integer pageNum, Model model, @RequestParam Integer id) {
        Post post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "redirect:/post/page";
    }

    @GetMapping("/GetPostByTitle")
    public String getPostsByTitle(@RequestParam String title, @RequestParam(value = "p", defaultValue = "1") Integer pageNum, Model model) {
        Page<Post> postsPage = postService.findPostsByTitle(title, pageNum);
        model.addAttribute("posts", postsPage.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "post/searchResult";
    }

    @GetMapping("/post/byTag")
    public String getPostsByTag(@RequestParam String tagName, Model model) {
        List<Post> posts = postService.findPostsByTag(tagName);
        model.addAttribute("posts", posts);
        model.addAttribute("tagName", tagName);
        return "post/searchResult";
    }
    @GetMapping("/post/frontPage")
    public String findByPageFront(@RequestParam(value = "p", defaultValue = "1") Integer pageNum, 
                             @RequestParam(value = "keyword", required=false) String keyword, Model model) {
        Page<Post> page = postService.findByPage(pageNum);
        Post latestPost = postService.findLatestPost();
        List<Tag> allTags = tagService.findAllTags();  
        model.addAttribute("latestPost", latestPost);
        model.addAttribute("page", page);
        model.addAttribute("allTags", allTags);  
        return "post/postFrontPage";
    }
    @GetMapping("/sellphone/comments")
    @ResponseBody
    public ResponseEntity<List<Comment>> getComments(@RequestParam Integer postId) {
    	Post post = postService.findPostById(postId);
        List<Comment> comments = commentService.findCommentsByPostId(post);
        return ResponseEntity.ok(comments);
    }
    
    @GetMapping("/post/{id}")
    public String getPostDetails(@PathVariable("id")Integer postId, Model model,HttpSession session) {
        Post post = postService.findPostById(postId);
        Users user = post.getUser();
        List<Comment> comments = commentService.findCommentsByPostId(post);
        String currentUserId = (String) session.getAttribute("userId");
        boolean hasLiked = false;
        if (currentUserId != null) {
            hasLiked = postService.hasUserLikedPost(postId, currentUserId);
        }
        model.addAttribute("post", post);  // 傳遞整個post對象
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postContent", post.getPostContent());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("postTime", post.getPostCreatedTime());
        model.addAttribute("postId", post.getPostId());
        model.addAttribute("postName", post.getPostId());
        model.addAttribute("comments", comments); // 添加评论数据到模型中
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("hasLiked", hasLiked);  // 添加 hasLiked 到模型中
        return "post/frontPageDetail";
    }
    @PostMapping("/post/like/toggle")
    @ResponseBody
    public Map<String, Object> togglePostLike(@RequestParam("postId") Integer postId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("User is not logged in");
        }
        boolean hasLiked = postService.toggleLike(postId, userId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("hasLiked", hasLiked);
        response.put("likeCount", postService.findPostById(postId).getLikeCount());
        return response;
    }

}