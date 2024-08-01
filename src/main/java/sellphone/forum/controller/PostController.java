package sellphone.forum.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sellphone.user.model.Users;
import sellphone.user.service.UserService;
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
    
    @Autowired
	private UserService userService;

    @GetMapping("/post/add")
    public String addposts(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        Post latestPost = postService.findLatestPost();
        model.addAttribute("latestPost", latestPost);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        return "post/addPostPage";
    }

    @PostMapping("/post/addPost")
    public String addPost(@RequestParam("title") String title, 
                          @RequestParam("content") String content,
                          @RequestParam("tags") List<String> tags, 
                          @RequestParam("userId") String userId,
                          @RequestParam("userName") String userName,
                          Model model) {
        Post post = new Post();
        post.setTitle(title);
        post.setPostContent(content);
        
        Users user = userService.findById(userId);
        post.setUser(user);

        postService.savePostWithTags(post, tags);

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
        //return "post/postFrontPage";
        
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
    public String editPostContent(
            @ModelAttribute Post post, 
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam(value = "tags", required = false) List<Integer> tagIds,  // 确保接收 tagIds 参数
            RedirectAttributes redirectAttributes) {
        if (post != null) {
            postService.updatePost(post, tagIds);  // 传递 tagIds 参数
            redirectAttributes.addFlashAttribute("successMessage", "貼文已更新");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "貼文不存在");
        }
        return "redirect:/post/page?p=" + pageNum;
    }
    
 // 新增前台編輯文章的GET請求
    @GetMapping("/post/editFront/{postId}")
    public String showEditFrontPostPage(@PathVariable("postId") Integer postId, Model model, HttpSession session) {
        Post post = postService.findPostById(postId);
        Users user = post.getUser();
        String currentUserId = (String) session.getAttribute("userId");
        if (currentUserId == null || !currentUserId.equals(user.getUserId())) {
            // 如果用戶未登入或不是該文章的作者，重定向到文章詳情頁
            return "redirect:/post/" + postId;
        }
        model.addAttribute("post", post);
        return "post/editFrontPostPage";
    }

    // 新增前台編輯文章的POST請求
    @PostMapping("/post/editFront")
    public String editFrontPost(@RequestParam("postId") Integer postId, 
                                @RequestParam("title") String title,
                                @RequestParam("postContent") String postContent,
                                RedirectAttributes redirectAttributes) {
        Post post = postService.findPostById(postId);
        if (post != null) {
            post.setTitle(title);
            post.setPostContent(postContent);
            postService.savePost(post);
            redirectAttributes.addFlashAttribute("successMessage", "貼文已更新");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "貼文不存在");
        }
        return "redirect:/post/" + postId;
    }

//    @PostMapping("/post/delete")
//    @ResponseBody
//    public ResponseEntity<?> deletePost(@RequestParam("id") Integer postId) {
//        try {
//            postService.deletePostById(postId);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除失敗");
//        }
//    }
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("id") Integer postId, RedirectAttributes redirectAttributes) {
        try {
            postService.deletePost(postId);
            redirectAttributes.addFlashAttribute("message", "貼文已成功刪除");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "刪除貼文失敗：" + e.getMessage());
        }
        return "redirect:/post/frontPage";
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
    public String getPostsByTag(@RequestParam String tagName, 
                                @RequestParam(defaultValue = "1") int page, 
                                Model model) {
        Page<Post> postsPage = postService.getPostsByTagName(tagName, PageRequest.of(page - 1, 10)); // 假設每頁10篇文章
        List<Tag> allTags = tagService.findAllTags();
        
        model.addAttribute("page", postsPage);
        model.addAttribute("allTags", allTags);
        model.addAttribute("selectedTag", tagName);
        
        return "post/postFrontPage"; // 返回完整的頁面
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
    
//    @GetMapping("/post/{id}")
//    public String getPostDetails(@PathVariable("id")Integer postId, Model model,HttpSession session) {
//        Post post = postService.findPostById(postId);
//        Users user = post.getUser();
//        List<Comment> comments = commentService.findCommentsByPostId(post);
//        String currentUserId = (String) session.getAttribute("userId");
//        boolean hasLiked = false;
//        boolean isFavorited = false;
//        if (currentUserId != null) {
//            hasLiked = postService.hasUserLikedPost(postId, currentUserId);
//            Users currentUser = userService.findById(currentUserId); 
//            isFavorited = post.getFavoritedUsers().contains(currentUser);
//        }
//        
//        model.addAttribute("post", post);  // 傳遞整個post對象
//        model.addAttribute("comments", comments);  // 傳遞整個comment對象
//        model.addAttribute("postTitle", post.getTitle());
//        model.addAttribute("postContent", post.getPostContent());
//        model.addAttribute("userId", user.getUserId());
//        model.addAttribute("userName", user.getUserName());
//        model.addAttribute("postTime", post.getPostCreatedTime());
//        model.addAttribute("postId", post.getPostId());
//        model.addAttribute("postName", post.getPostId());
//        model.addAttribute("currentUserId", currentUserId);
//        model.addAttribute("hasLiked", hasLiked);  // 添加 hasLiked 到模型中
//        model.addAttribute("isFavorited", isFavorited);
//        return "post/frontPageDetail";
//    }
    @GetMapping("/post/{id}")
    public String getPostDetails(@PathVariable("id") Integer postId, Model model, HttpSession session) {
        Post post = postService.findPostById(postId);
        Users user = post.getUser();
        List<Comment> comments = commentService.findCommentsByPostId(post);

        // 过滤掉回复评论，只保留主评论
        List<Comment> mainComments = comments.stream()
                                             .filter(comment -> comment.getParentComment() == null)
                                             .collect(Collectors.toList());

        String currentUserId = (String) session.getAttribute("userId");
        boolean hasLiked = false;
        boolean isFavorited = false;
        if (currentUserId != null) {
            hasLiked = postService.hasUserLikedPost(postId, currentUserId);
            Users currentUser = userService.findById(currentUserId);  // Assume there is a method to find user by ID
            isFavorited = post.getFavoritedUsers().contains(currentUser);
        }

        model.addAttribute("post", post);  // 傳遞整個post對象
        model.addAttribute("comments", mainComments);  // 傳遞過濾後的主评论对像
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postContent", post.getPostContent());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("postTime", post.getPostCreatedTime());
        model.addAttribute("postId", post.getPostId());
        model.addAttribute("postName", post.getPostId());
        model.addAttribute("currentUserId", currentUserId);
        model.addAttribute("hasLiked", hasLiked);  // 添加 hasLiked 到模型中
        model.addAttribute("isFavorited", isFavorited);
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
    @GetMapping("/user/favorites")
    public String getUserFavorites(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Users user = userService.findById(userId);
        List<Post> favorites = new ArrayList<>(user.getFavoritePosts());
        model.addAttribute("favorites", favorites);
        return "user/favorites";
    }
    
    @PostMapping("/post/favorite/toggle")
    public String toggleFavorite(@RequestParam("postId") Integer postId, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "請先登入會員");
            return "redirect:/login";
        }
        boolean isFavorited = postService.toggleFavorite(postId, userId);
        redirectAttributes.addFlashAttribute("message", isFavorited ? "已收藏" : "已取消收藏");
        return "redirect:/post/" + postId;
    }
    
    @GetMapping("/user-posts")
    public String redirectToUserPostsAndFavorites() {
        return "redirect:/user/postsAndFavorites";
    }

    @GetMapping("/user/postsAndFavorites")
    public String getUserPostsAndFavorites(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Users user = userService.findById(userId);
        List<Post> userPosts = postService.findPostsByUserId(userId);
        List<Post> favoritePosts = new ArrayList<>(user.getFavoritePosts());

        model.addAttribute("userPosts", userPosts);
        model.addAttribute("favoritePosts", favoritePosts);

        return "post/postsAndFavorites";  // 确保路径与模板位置匹配
    }

        @GetMapping("/UserPostList")
        public String userPostList(Model model, HttpServletRequest req, HttpServletResponse resp) {
            String userId = (String) req.getSession().getAttribute("userId");
            if (userId == null) {
                return "redirect:/login";
            }
            List<Post> userPosts = postService.getUserPosts(userId);
            model.addAttribute("userId", userId);
            model.addAttribute("posts", userPosts);
            return "/user/fronted/UserPost";
        }
        @GetMapping("/bookmarkedPosts")
        public String bookmarkedPosts(Model model, HttpServletRequest req) {
            String userId = (String) req.getSession().getAttribute("userId");
            if (userId == null) {
                return "redirect:/login";
            }
            List<Post> bookmarkedPosts = postService.getBookmarkedPosts(userId);
            model.addAttribute("userId", userId);
            model.addAttribute("posts", bookmarkedPosts);
            return "user/fronted/bookmarkedPosts"; 
        }
        @GetMapping("/reportPost/{postId}")
        public String reportPost(@PathVariable Integer postId, RedirectAttributes redirectAttributes) {
            Post post = postService.findPostById(postId);
            if (post != null) {
                Users user = post.getUser();
                if (user != null) {
                    user.setReportNum(user.getReportNum() + 1);
                    userService.insert(user);
                    redirectAttributes.addFlashAttribute("reportSuccess", "檢舉已成功提交");
                    return "redirect:/post/frontPage";
                }
            }
            redirectAttributes.addFlashAttribute("error", "檢舉失敗");
            return "redirect:/post/frontPage";
        }



    
}
    

