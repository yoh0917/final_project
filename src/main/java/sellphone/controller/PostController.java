package sellphone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.model.Post;
import sellphone.model.Tag;
import sellphone.service.PostService;
import sellphone.service.TagService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

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
}